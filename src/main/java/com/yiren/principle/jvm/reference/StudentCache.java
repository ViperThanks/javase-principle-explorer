package com.yiren.principle.jvm.reference;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 13/4/2024
 */
public class StudentCache {
    private static final Logger log = LoggerFactory.getLogger(StudentCache.class);
    private final Map<Long, StudentRef> context;
    private final ReferenceQueue<? super Student> q;

    public Map<Long, StudentRef> getContext() {
        return context;
    }

    @Getter
    private static class StudentRef extends SoftReference<Student> {
        private final Long key;

        public StudentRef(Student referent, ReferenceQueue<? super Student> q) {
            super(referent, q);
            key = referent.getId();
        }
    }

    private StudentCache() {
        this.context = new HashMap<>();
        this.q = new ReferenceQueue<>();
    }

    public static StudentCache getInstance() {
        return new StudentCache();
    }

    public void put(Student student) {
        checkNotNull(student);
        cleanCache();
        StudentRef studentRef = new StudentRef(student, this.q);
        context.put(student.getId(), studentRef);
        log.info(String.valueOf(context.size()));
    }

    public Student get(Long id) {
        checkNotNull(id);
        StudentRef studentRef = context.get(id);
        return studentRef.get();
    }

    private void cleanCache() {
        StudentRef studentRef;
        while ((studentRef = (StudentRef) this.q.poll()) != null) {
            context.remove(studentRef.getKey());
            log.info("clean one key : {}", studentRef.getKey());
        }
    }

    @Override
    public String toString() {
        return "StudentCache{" +
                "context=" + context +
                '}';
    }
}
