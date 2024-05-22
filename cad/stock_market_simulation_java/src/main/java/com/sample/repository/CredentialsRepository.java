package com.sample.repository;

import com.sample.models.Credentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialsRepository {
    private static CredentialsRepository credentialsRepository;

    private CredentialsRepository() {

    }

    public static CredentialsRepository getInstance() {
        if (credentialsRepository == null) {
            credentialsRepository = new CredentialsRepository();
        }
        return credentialsRepository;
    }


    public Credentials getCredentialByUsername(String username) {
        Connection conn = JDBCConnection.getInstance().getConnection();
        Credentials cred = null;
        try {
            String query = "SELECT * FROM credentials WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                cred = new Credentials(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cred;
    }
}
