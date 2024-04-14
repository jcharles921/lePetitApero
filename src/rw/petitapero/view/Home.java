package rw.petitapero.view;

import rw.petitapero.dao.OrderDao;
import rw.petitapero.model.Order;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;
import java.awt.Color;

public class Home extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home frame = new Home();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Home() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Navigation Panel (Assuming the logout button is added here)

        // Table Panel
        JPanel tablePanel = new JPanel();
        contentPane.add(tablePanel, BorderLayout.CENTER);
        tablePanel.setLayout(null);

        // Table
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 790, 590);
        tablePanel.add(scrollPane);

        // Fetch and display orders
        OrderDao orderDao = new OrderDao(); // Assuming you have an OrderDao class
        List<Order> orderList = orderDao.retrieveAllOrders();
        fetchAndDisplayOrders(orderList);
    }

    private void fetchAndDisplayOrders(List<Order> orderList) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Coffee ID");
        model.addColumn("User ID");
        model.addColumn("Quantity");
        model.addColumn("Status");

        for (Order order : orderList) {
            Vector<Object> row = new Vector<>();
            row.add(order.getId());
            row.add(order.getCoffeeId());
            row.add(order.getUserId());
            row.add(order.getQuantity());
            row.add(order.getStatus());
            model.addRow(row);
        }

        table.setModel(model);
    }
}
