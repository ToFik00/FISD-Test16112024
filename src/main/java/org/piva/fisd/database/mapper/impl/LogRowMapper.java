package org.piva.fisd.database.mapper.impl;

import org.piva.fisd.database.entity.Log;
import org.piva.fisd.database.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogRowMapper implements RowMapper<Log> {
    @Override
    public Log mapRow(ResultSet resultSet) throws SQLException {
        return Log.builder()
                .id(resultSet.getLong("id"))
                .date(resultSet.getTimestamp("date"))
                .login(resultSet.getString("login"))
                .isSucceed(resultSet.getBoolean("isSucceed"))
                .build();
    }
}
