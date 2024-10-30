package com.yiren.enums;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndexDescTypeHandler<E extends Enum<E> & IndexDesc> extends BaseTypeHandler<E> {

    private final Class<E> type;
    private final Field indexField;
    private final Field descField;

    public IndexDescTypeHandler(Class<E> type) throws NoSuchFieldException {
        this.type = type;
        this.indexField = type.getDeclaredField("index");
        this.descField = type.getDeclaredField("desc");
        this.indexField.setAccessible(true);
        this.descField.setAccessible(true);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setObject(i, indexField.get(parameter));
        } catch (IllegalAccessException e) {
            throw new SQLException("Failed to get value from enum field", e);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object value = rs.getObject(columnName);
        return value == null ? null : valueOfEnum(value);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object value = rs.getObject(columnIndex);
        return value == null ? null : valueOfEnum(value);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object value = cs.getObject(columnIndex);
        return value == null ? null : valueOfEnum(value);
    }

    private E valueOfEnum(Object value) throws SQLException {
        return IndexDescEnumUtil.Finder.findByIndexOrThrow(type, (Integer) value,true);
    }
}
