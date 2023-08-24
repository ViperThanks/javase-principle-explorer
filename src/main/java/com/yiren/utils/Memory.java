package com.yiren.utils;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 7/4/2023 下午 3:42
 * desc     : 内存单位 B KB MB GB TB
 */
public enum Memory {


    B {
        public Long toB() {
            return c0;
        }

        public Long toKB() {
            return c0 / c1;
        }

        public Long toMB() {
            return c0 / c2;
        }

        public Long toGB() {
            return c0 / c3;
        }

        public Long toTB() {
            return c0 / c4;
        }
    },
    KB {
        public Long toB() {
            return c0 * c1;
        }

        public Long toKB() {
            return c0;
        }

        public Long toMB() {
            return c0 / c1;
        }

        public Long toGB() {
            return c0 / c2;
        }

        public Long toTB() {
            return c0 / c3;
        }
    },
    MB {
        public Long toB() {
            return c0 * c2;
        }

        public Long toKB() {
            return c0 * c1;
        }

        public Long toMB() {
            return c0;
        }

        public Long toGB() {
            return c0 / c1;
        }

        public Long toTB() {
            return c0 / c2;
        }
    },
    GB {
        public Long toB() {
            return c0 * c3;
        }

        public Long toKB() {
            return c0 * c2;
        }

        public Long toMB() {
            return c0 * c1;
        }

        public Long toGB() {
            return c0;
        }

        public Long toTB() {
            return c0 / c1;
        }
    },
    @Deprecated
    TB {
        public Long toB() {
            return c0 * c4;
        }

        public Long toKB() {
            return c0 * c3;
        }

        public Long toMB() {
            return c0 * c2;
        }

        public Long toGB() {
            return c0 * c1;
        }

        public Long toTB() {
            return c0;
        }
    };

    private static final Long c0 = 1L;
    private static final Long c1 = 1024 * c0;
    private static final Long c2 = 1024 * c1;
    private static final Long c3 = 1024 * c2;
    private static final Long c4 = 1024 * c3;

    /**
     * 将Memory对象转为B
     *
     * @return B
     */
    public abstract Long toB();

    /**
     * 将Memory对象转为KB
     *
     * @return KB
     */
    public abstract Long toKB();

    /**
     * 将Memory对象转为MB
     *
     * @return MB
     */
    public abstract Long toMB();

    /**
     * 将Memory对象转为GB
     *
     * @return GB
     */
    public abstract Long toGB();

    /**
     * 将Memory对象转为TB
     *
     * @return TB
     */
    public abstract Long toTB();


}
