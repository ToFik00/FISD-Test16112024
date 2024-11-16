package org.piva.fisd.database;

import lombok.AllArgsConstructor;
import org.piva.fisd.database.mapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class JdbcTemplate {

    private DataSource dataSource;

    public <T> List<T> execute(String sql, RowMapper<T> rowMapper, Object... objects) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                List<T> resultList = new ArrayList<>();
                if (resultSet != null) {
                    while (resultSet.next()) {
                        resultList.add(rowMapper.mapRow(resultSet));
                    }
                }
                return resultList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
