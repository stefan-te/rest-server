package de.internetx.restserver;

public interface Constants {

    String INSERT_USER_QUERY = "INSERT INTO user (login, password, fname, lname, email) VALUES (?, ?, ?, ?, " +
            "?);";
    String UPDATE_USER_QUERY = "UPDATE user SET login = ?, password = ?, fname = ?, lname = ?, email = ?" +
            "WHERE user.id = ?;";
    String GET_USER_QUERY = "SELECT * FROM user WHERE user.id = ?;";
    String DELETE_USER_QUERY = "DELETE FROM user WHERE user.id = ?;";
}
