package org.academiadecodigo.loginmvcmaven.service.user;

import org.academiadecodigo.loginmvcmaven.Persistence.jdbc.ConnectionManager;
import org.academiadecodigo.loginmvcmaven.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by codecadet on 28/11/16.
 */
public class JdbcUserService extends AbstractUserService {
    private Connection dbConnection;
    private ConnectionManager manager;
    private Statement statement;

    public JdbcUserService(ConnectionManager manager) {
        this.manager = manager;
        dbConnection = manager.getConnection();
    }

    @Override
    public boolean authenticate(String name, String password) {
        boolean result = false;

        try {
            checkConnection();
            dbConnection.setAutoCommit(false);

            statement = dbConnection.createStatement();
            String query = "SELECT username, password FROM users WHERE username = \"" + name + "\" AND password = \"" + password + "\"";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println("Bem vindo(a) " + name);
                result = true;
            }
/*            if (statement != null) {
                statement.close();
            }*/

            dbConnection.commit();

        } catch (SQLException e) {
            try {
                dbConnection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

    @Override
    public void addUser(User user) {
        try {
            checkConnection();

            Statement statement = dbConnection.createStatement();
            String query = "INSERT INTO users (username, password, email) VALUES (\"" + user.getUsername() + "\",\"" + user.getPassword() + "\",\"" + user.getEmail() + "\");";
            int resultSet = statement.executeUpdate(query);

            if (resultSet == 1) {
                System.out.println("User Registered with Success " + user.getUsername());
            }

            if (statement != null) {
                statement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByName(String name) {
        User user;

        try {
            checkConnection();

            Statement statement = dbConnection.createStatement();
            String query = "SELECT username, password, email FROM users WHERE username = \"" + name + "\";";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                System.out.println("Find the user");
                return user;
            }

            if (statement != null) {
                statement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public int count() {
        int result = 0;

        try {
            checkConnection();

            Statement statement = dbConnection.createStatement();
            String query = "SELECT COUNT(username) AS contagem FROM users ;";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println("Number of people registed: " + resultSet.getString(1));
                result = resultSet.getInt(1);
            }

            if (statement != null) {
                statement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public boolean userExist(String name) {
        boolean toReturn = false;

        try {
            checkConnection();

            Statement statement = dbConnection.createStatement();
            String query = "SELECT username FROM users WHERE username = \"" + name + "\"";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println("User Exist " + name);
                toReturn = true;
            }

            if (statement != null) {
                statement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return toReturn;
    }


    private void checkConnection() {
        try {
            if (dbConnection == null || dbConnection.isClosed()) {
                dbConnection = manager.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (dbConnection == null) {
            throw new IllegalStateException("Error connection to database!");
        }
    }

}
