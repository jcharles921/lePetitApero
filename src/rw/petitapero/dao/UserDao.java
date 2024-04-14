package rw.petitapero.dao;
import rw.petitapero.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/lepetitapero?allowPublicKeyRetrieval=true&useSSL=false";
    private String username = "carlos";
    private String dbPassword = "bouBoune#123";

    @SuppressWarnings("unused")
	public Integer registerUser(User user) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            Integer role = user.getRole();
            if (role == null) {
                role = 2;
            }
            pst.setInt(4, role);
            int rowAffected = pst.executeUpdate();
            con.close();
            return rowAffected;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Integer updateUser(User user) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "UPDATE users SET name=?, email=?, password=?, role=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setInt(4, user.getRole());
            pst.setInt(5, user.getId());
            int rowAffected = pst.executeUpdate();
            con.close();
            return rowAffected;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Integer deleteUser(int userId) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userId);
            int rowAffected = pst.executeUpdate();
            con.close();
            return rowAffected;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public User findUserById(int userId) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "SELECT * FROM users WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
            }
            con.close();
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<User> retrieveAllUsers() {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "SELECT * FROM users";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                userList.add(user);
            }
            con.close();
            return userList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}