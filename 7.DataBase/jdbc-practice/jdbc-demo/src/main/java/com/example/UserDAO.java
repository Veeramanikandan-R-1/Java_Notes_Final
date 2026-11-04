package com.example;

import java.sql.*;

public class UserDAO {

    // CREATE
    public void createUser(String name, String email) {

        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);

            int rows = ps.executeUpdate();

            System.out.println(rows + " user inserted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public void getUsers() {

        String sql = "SELECT * FROM users";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updateUser(int id, String name) {

        String sql = "UPDATE users SET name = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            System.out.println(rows + " user updated");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteUser(int id) {

        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            System.out.println(rows + " user deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}