package Core.Infrastructure.Infrastructure;
import Core.Infrastructure.Persistence.DbConnection;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class BaseRepository<T> {

    protected abstract String getTableName();

    public CompletableFuture<List<T>> findAllAsync() {
        return CompletableFuture.supplyAsync(() -> {
            List<T> entities = new ArrayList<>();
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = DbConnection.getConnection();
                String sql = "SELECT * FROM " + getTableName();
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    T entity = mapResultSetToEntity(resultSet);
                    entities.add(entity);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } finally {
                DbConnection.close(resultSet, preparedStatement, connection);
            }

            return entities;
        });
    }

    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException, NoSuchAlgorithmException;
}
