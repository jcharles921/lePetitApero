package rw.petitapero.dao;
import rw.petitapero.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoffeeDao {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/lepetitapero?allowPublicKeyRetrieval=true&useSSL=false";
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
    	        String sql = "UPDATE coffees SET name=?, description=?, category=?, price=? WHERE name=?";
    	        PreparedStatement pst = con.prepareStatement(sql);
    	        pst.setString(1, coffee.getName());
    	        pst.setString(2, coffee.getDescription());
    	        pst.setString(3, coffee.getCategory());
    	        pst.setDouble(4, coffee.getPrice());
    	        pst.setString(5, coffee.getName());
    	        int rowAffected = pst.executeUpdate();
    	        System.out.println("Want to update " + coffee.getName()+ coffee.getDescription());
    	        con.close();
    	        return rowAffected;
    	    } catch (SQLException ex) {
    	        System.err.println("SQL Exception occurred:");
    	        ex.printStackTrace();
    	        return null;
    	    } catch (Exception ex) {	
    	        System.err.println("Exception occurred:");
    	        ex.printStackTrace();
    	        return null;
    	    }
    }

    public Integer deleteCoffee(String name) {
        Connection con = null; // Declare connection outside try block
        try {
            con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            con.setAutoCommit(false); // Start transaction

            // First, delete associated orders
            String deleteOrdersSql = "DELETE FROM orders WHERE coffee_id IN (SELECT id FROM coffees WHERE name=?)";
            PreparedStatement deleteOrdersPst = con.prepareStatement(deleteOrdersSql);
            deleteOrdersPst.setString(1, name);
            @SuppressWarnings("unused")
			int ordersDeleted = deleteOrdersPst.executeUpdate();

            // Then, delete the coffee
            String deleteCoffeeSql = "DELETE FROM coffees WHERE name=?";
            PreparedStatement deleteCoffeePst = con.prepareStatement(deleteCoffeeSql);
            deleteCoffeePst.setString(1, name);
            int coffeeDeleted = deleteCoffeePst.executeUpdate();

            // Commit transaction
            con.commit();
            con.setAutoCommit(true);

            return coffeeDeleted;
        } catch (SQLException ex) {
            // Rollback transaction in case of error
            try {
                if (con != null) {
                    con.rollback();
                    con.setAutoCommit(true);
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            // Close connection in finally block to ensure it gets closed even if an exception occurs
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
        return null;
    }


    public Coffee findCoffeeByName(String name) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "SELECT * FROM coffees WHERE name=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
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