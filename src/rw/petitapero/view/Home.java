package rw.petitapero.view;

import rw.petitapero.dao.CoffeeDao;
import rw.petitapero.dao.OrderDao;
import rw.petitapero.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Vector;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
public class Home extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable ordersTable;
    private JTable coffeeTable;
	int xx,xy;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home frame = new Home();
                    frame.setUndecorated(true);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void fetchAndDisplayCoffees(List<Coffee> coffeeList) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Description");
        model.addColumn("Category");
        model.addColumn("Price");

        for (Coffee coffee : coffeeList) {
            Vector<Object> row = new Vector<>();
            row.add(coffee.getId());
            row.add(coffee.getName());
            row.add(coffee.getDescription());
            row.add(coffee.getCategory());
            row.add(coffee.getPrice());
            model.addRow(row);
        }

        coffeeTable.setModel(model);
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
   
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				 xx = e.getX();
			     xy = e.getY();
			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				int x = arg0.getXOnScreen();
	            int y = arg0.getYOnScreen();
	            Home.this.setLocation(x - xx, y - xy);  
			}
		});
        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.WHITE);
        contentPane.add(tablePanel, BorderLayout.CENTER);
        tablePanel.setLayout(null);

        // Table
        ordersTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        scrollPane.setBounds(0, 401, 790, 193);
        tablePanel.add(scrollPane);
        int borderRadius = 15; // Adjust the radius as per your preference
        
        JLabel lblOrdersStatus = new JLabel("Recent orders");
        lblOrdersStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrdersStatus.setForeground(Color.BLACK);
        lblOrdersStatus.setFont(new Font("Carlito", Font.BOLD, 18));
        lblOrdersStatus.setBounds(0, 356, 167, 33);
        tablePanel.add(lblOrdersStatus);
        @SuppressWarnings("unused")
		Border border = BorderFactory.createEmptyBorder(borderRadius, borderRadius, borderRadius, borderRadius);
        
        JScrollPane scrollPane_1 = new JScrollPane((Component) null);
        scrollPane_1.setBounds(0, 151, 790, 193);
        tablePanel.add(scrollPane_1);
        
        coffeeTable = new JTable();
        scrollPane_1.setViewportView(coffeeTable);
        CoffeeDao coffeeDao = new CoffeeDao();
        List<Coffee> coffeeList = coffeeDao.retrieveAllCoffees();
        fetchAndDisplayCoffees(coffeeList);
        
        JPanel addCoffee = new JPanel();
        addCoffee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addCoffee.setLayout(null);
        addCoffee.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        addCoffee.setBackground(new Color(181, 131, 90));
        addCoffee.setBounds(648, 99, 116, 33);
        addCoffee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	AddCoffee AddCoffeeFrame = new AddCoffee();
            	AddCoffeeFrame.setUndecorated(true);
            	AddCoffeeFrame.setVisible(true);
                // Close the current login frame
                dispose();
            }
        });
        tablePanel.add(addCoffee);
        
        
        JLabel lblAdd_1 = new JLabel("Add New");
        lblAdd_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblAdd_1.setForeground(Color.WHITE);
        lblAdd_1.setBounds(12, 0, 64, 33);
        addCoffee.add(lblAdd_1);
        
        JLabel label_1 = new JLabel("");
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setIcon(new ImageIcon(Home.class.getResource("/rw/petitapero/images/Create.png")));
        label_1.setBounds(64, 0, 52, 33);
        addCoffee.add(label_1);
        
        JPanel addOrder = new JPanel();
        addOrder.setLayout(null);
        addOrder.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        addOrder.setBackground(new Color(181, 131, 90));
        addOrder.setBounds(648, 356, 116, 33);
        tablePanel.add(addOrder);
        
        JLabel lblAdd_1_1 = new JLabel("Add New");
        lblAdd_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblAdd_1_1.setForeground(Color.WHITE);
        lblAdd_1_1.setBounds(12, 0, 64, 33);
        addOrder.add(lblAdd_1_1);
        
        JLabel label_1_1 = new JLabel("");
        label_1_1.setIcon(new ImageIcon(Home.class.getResource("/rw/petitapero/images/Create.png")));
        label_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1_1.setBounds(64, 0, 52, 33);
        addOrder.add(label_1_1);
        
        JLabel lblCoffeeMenue = new JLabel("Coffee menue");
        lblCoffeeMenue.setHorizontalAlignment(SwingConstants.CENTER);
        lblCoffeeMenue.setForeground(Color.BLACK);
        lblCoffeeMenue.setFont(new Font("Carlito", Font.BOLD, 18));
        lblCoffeeMenue.setBounds(0, 99, 161, 33);
        tablePanel.add(lblCoffeeMenue);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(181, 131, 90));
        panel.setBounds(0, -12, 863, 87);
        tablePanel.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("LE PETIT APERO COFFEE-SHOP");
        lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Montserrat SemiBold", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel.setBounds(29, 30, 391, 33);
        panel.add(lblNewLabel);
        
        JLabel leave = new JLabel("");
        leave.setIcon(new ImageIcon(Home.class.getResource("/rw/petitapero/images/Logout.png")));
        leave.setBounds(701, 30, 30, 33);
        leave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Login LoginFrame = new Login();
                LoginFrame.setUndecorated(true);	
                LoginFrame.setVisible(true);
                dispose();
            }
        });
        panel.add(leave);
        
        JLabel lbl_close = new JLabel("");
        lbl_close.setIcon(new ImageIcon(Home.class.getResource("/rw/petitapero/images/Exit.png")));
        lbl_close.setBounds(743, 30, 30, 33);
        lbl_close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl_close.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		System.exit(0);
        	}
        });
        panel.add(lbl_close);
        
        JPanel addCoffee_1 = new JPanel();
        addCoffee_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addCoffee_1.setLayout(null);
        addCoffee_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        addCoffee_1.setBackground(new Color(181, 131, 90));
        addCoffee_1.setBounds(495, 99, 116, 33);
        tablePanel.add(addCoffee_1);
        
        JLabel lblAdd_1_2 = new JLabel("Find One");
        lblAdd_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblAdd_1_2.setForeground(Color.WHITE);
        lblAdd_1_2.setBounds(12, 0, 64, 33);
        addCoffee_1.add(lblAdd_1_2);
        
        JLabel label_1_2 = new JLabel("");
        label_1_2.setIcon(new ImageIcon(Home.class.getResource("/rw/petitapero/images/Find.png")));
        label_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_1_2.setBounds(64, 0, 52, 33);
        label_1_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	FindCoffee FindCoffeeFrame = new FindCoffee();
            	FindCoffeeFrame.setUndecorated(true);	
            	FindCoffeeFrame.setVisible(true);
                dispose();
            }
        });
        addCoffee_1.add(label_1_2);
        
        JPanel addCoffee_1_1 = new JPanel();
        addCoffee_1_1.setLayout(null);
        addCoffee_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        addCoffee_1_1.setBackground(new Color(181, 131, 90));
        addCoffee_1_1.setBounds(495, 356, 116, 33);
        tablePanel.add(addCoffee_1_1);
        
        JLabel lblAdd_1_2_1 = new JLabel("Find One");
        lblAdd_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblAdd_1_2_1.setForeground(Color.WHITE);
        lblAdd_1_2_1.setBounds(12, 0, 64, 33);
        addCoffee_1_1.add(lblAdd_1_2_1);
        
        JLabel label_1_2_1 = new JLabel("");
        label_1_2_1.setIcon(new ImageIcon(Home.class.getResource("/rw/petitapero/images/Find.png")));
        label_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1_2_1.setBounds(64, 0, 52, 33);
        addCoffee_1_1.add(label_1_2_1);

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

        ordersTable.setModel(model);
    }
}
