package Supermercado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class CargaGral<T> {

    protected Connection connection;
    private Class<T> clazz;

    public CargaGral(Class<T> clazz, Connection connection) {
        this.clazz = clazz;
    	this.connection = connection;
    }

    protected abstract T mapToObject(ResultSet resultSet) throws SQLException;

    public void insert(String query, Object... parameters) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int index = 1;
            for (Object parameter : parameters) {
                statement.setObject(index++, parameter);
            }
            statement.executeUpdate();
        }
    }

    public void update(String query, Object... parameters) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int index = 1;
            for (Object parameter : parameters) {
                statement.setObject(index++, parameter);
            }
            statement.executeUpdate();
        }
    }

    public void delete(String query, Object... parameters) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int index = 1;
            for (Object parameter : parameters) {
                statement.setObject(index++, parameter);
            }
            statement.executeUpdate();
        }
    }

    public List<T> select(String query, Object... parameters) throws SQLException {
        List<T> results = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int index = 1;
            for (Object parameter : parameters) {
                statement.setObject(index++, parameter);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    T object = mapToObject(resultSet);
                    results.add(object);
                }
            }
        }
        return results;
    }

}
