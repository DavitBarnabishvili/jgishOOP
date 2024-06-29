package Core.Infrastructure.Infrastructure;

import Core.Domain.Entities.User;
import Core.Infrastructure.Persistence.DbConnection;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class UserRepository extends BaseRepository<User> {

    @Override
    protected String getTableName() {
        return "users";
    }

    @Override
    protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException, NoSuchAlgorithmException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setPictureUrl(resultSet.getString("picture_url"));
        return user;
    }

    public CompletableFuture<User> findByIdAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            User user = null;
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = DbConnection.getConnection();
                String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    user = mapResultSetToEntity(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } finally {
                DbConnection.close(resultSet, preparedStatement, connection);
            }

            return user;
        });
    }

    public CompletableFuture<Void> createAsync(User user) {
        return CompletableFuture.runAsync(() -> {
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = DbConnection.getConnection();
                String sql = "INSERT INTO " + getTableName() + " (username, password, picture_url) VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPasswordHash());
                preparedStatement.setString(3, user.getPictureUrl());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DbConnection.close(preparedStatement, connection);
            }
        });
    }

    public CompletableFuture<Void> updateAsync(User user) {
        return CompletableFuture.runAsync(() -> {
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = DbConnection.getConnection();
                String sql = "UPDATE " + getTableName() + " SET username = ?, password = ?, picture_url = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPasswordHash());
                preparedStatement.setString(3, user.getPictureUrl());
                preparedStatement.setInt(4, user.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DbConnection.close(preparedStatement, connection);
            }
        });
    }

    public CompletableFuture<Void> deleteByIdAsync(int id) {
        return CompletableFuture.runAsync(() -> {
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = DbConnection.getConnection();
                String sql = "DELETE FROM " + getTableName() + " WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DbConnection.close(preparedStatement, connection);
            }
        });
    }
}

