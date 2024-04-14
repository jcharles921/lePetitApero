package rw.petitapero.dao;
import rw.petitapero.model.Coffee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CoffeeDao {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/schoolstudent?allowPublicKeyRetrieval=true&useSSL=false";
    private String username = "carlos";
    private String dbPassword = "bouBoune#123";

    public Integer addCoffee(Coffee coffee) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "INSERT INTO coffees (name, description, category, price) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, coffee.getName());
            pst.setString(2, coffee.getDescription());
            pst.setString(3, coffee.getCategory());
            pst.setDouble(4, coffee.getPrice());
            int rowAffected = pst.executeUpdate();
            con.close();
            return rowAffected;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Integer updateCoffee(Coffee coffee) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "UPDATE coffees SET name=?, description=?, category=?, price=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, coffee.getName());
            pst.setString(2, coffee.getDescription());
            pst.setString(3, coffee.getCategory());
            pst.setDouble(4, coffee.getPrice());
            pst.setInt(5, coffee.getId());
            int rowAffected = pst.executeUpdate();
            con.close();
            return rowAffected;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Integer deleteCoffee(int coffeeId) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "DELETE FROM coffees WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, coffeeId);
            int rowAffected = pst.executeUpdate();
            con.close();
            return rowAffected;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Coffee findCoffeeById(int coffeeId) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "SELECT * FROM coffees WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, coffeeId);
            ResultSet rs = pst.executeQuery();
            Coffee coffee = null;
            if (rs.next()) {
                coffee = new Coffee();
                coffee.setId(rs.getInt("id"));
                coffee.setName(rs.getString("name"));
                coffee.setDescription(rs.getString("description"));
                coffee.setCategory(rs.getString("category"));
                coffee.setPrice(rs.getDouble("price"));
            }
            con.close();
            return coffee;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Coffee> retrieveAllCoffees() {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "SELECT * FROM coffees";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List<Coffee> coffeeList = new ArrayList<>();
            while (rs.next()) {
                Coffee coffee = new Coffee();
                coffee.setId(rs.getInt("id"));
                coffee.setName(rs.getString("name"));
                coffee.setDescription(rs.getString("description"));
                coffee.setCategory(rs.getString("category"));
                coffee.setPrice(rs.getDouble("price"));
                coffeeList.add(coffee);
            }
            con.close();
            return coffeeList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}