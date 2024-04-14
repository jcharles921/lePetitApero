package rw.petitapero.dao;

import rw.petitapero.model.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/lepetitapero?allowPublicKeyRetrieval=true&useSSL=false";
    private String username = "carlos";
    private String dbPassword = "bouBoune#123";


    public Integer addOrder(Order order) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "INSERT INTO orders (coffee_id, coffee_name, user_id, quantity, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, order.getCoffeeId());
            pst.setString(2, order.getCoffeeName());
            pst.setInt(3, order.getUserId());
            pst.setInt(4, order.getQuantity());
            pst.setString(5, order.getStatus());
            int rowAffected = pst.executeUpdate();
            con.close();
            return rowAffected;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Integer updateOrder(Order order) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "UPDATE orders SET coffee_id=?, coffee_name=?, user_id=?, quantity=?, status=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, order.getCoffeeId());
            pst.setString(2, order.getCoffeeName());
            pst.setInt(3, order.getUserId());
            pst.setInt(4, order.getQuantity());
            pst.setString(5, order.getStatus());
            pst.setInt(6, order.getId());
            int rowAffected = pst.executeUpdate();
            con.close();
            return rowAffected;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Other methods remain unchanged...

    public Order findOrderById(int orderId) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "SELECT * FROM orders WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();
            Order order = null;
            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setCoffeeId(rs.getInt("coffee_id"));
                order.setCoffeeName(rs.getString("coffee_name"));
                order.setUserId(rs.getInt("user_id"));
                order.setQuantity(rs.getInt("quantity"));
                order.setStatus(rs.getString("status"));
            }
            con.close();
            return order;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Order> retrieveAllOrders() {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, dbPassword);
            String sql = "SELECT * FROM orders";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List<Order> orderList = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCoffeeId(rs.getInt("coffee_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setQuantity(rs.getInt("quantity"));
                order.setStatus(rs.getString("status"));
                orderList.add(order);
            }
            con.close();
            return orderList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
