package de.internetx.restserver;

public interface Constants {

    String INSERT_USER_QUERY = "INSERT INTO user (login, password, fname, lname, email) VALUES (?, ?, ?, ?, " +
            "?);";
    String UPDATE_USER_QUERY = "UPDATE user SET login = ?, password = ?, fname = ?, lname = ?, email = ?" +
            "WHERE user.id = ?;";
    String GET_USER_BY_ID_QUERY = "SELECT * FROM user WHERE user.id = ?;";
    String DELETE_USER_QUERY = "DELETE FROM user WHERE user.id = ?;";
    String GET_USER_BY_LOGIN_QUERY = "SELECT id, login, password FROM user WHERE user.login = ?;";
    String GET_ROLES_BY_ID_QUERY = "SELECT role_admin, role_develop, role_cctld, role_gtld, role_billing, role_registry," +
            " role_purchase_read, role_purchase_write, role_sale_write, role_sql FROM role WHERE user_id = ?;";
    String INSERT_ROLE__QUERY = "INSERT INTO role (user_id, role_admin, role_develop, role_cctld, role_gtld, role_billing, " +
            "role_registry, role_purchase_read, role_purchase_write, role_sale_write, role_sql) VALUES (?, ?, ?. ?, ?, ?, ?, ?, ?. ?, ?);";
    String[] ROLES = new String[]{"role_admin", "role_develop", "role_cctld", "role_gtld", "role_billing",
            "role_registry", "role_purchase_read", "role_purchase_write", "role_sale_write", "role_sql"};

    long EXPIRATION_DATE = (long) 3 * 1000 * 60 * 60 * 24; // 3 days
    String SECRET = "PnT8Jvm6GP6kjUHOf1evz8znFQ97aFNHRlgW6YiJyU57fx4PYDKGtOIRbBBcGO3";
    String HEADER_STRING = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
}
