package rw.petitapero.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import rw.petitapero.dao.CoffeeDao;
import rw.petitapero.model.Coffee;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddCoffee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField descriptionField;
	private JTextField nameField;
	private JTextField categoryField;
	private JTextField priceField;
	int xx,xy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCoffee frame = new AddCoffee();
					frame.setUndecorated(true);
	                frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddCoffee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 518);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
	            AddCoffee.this.setLocation(x - xx, y - xy);  
			}
		});

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 863, 83);
		panel.setBackground(new Color(181, 131, 90));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LE PETIT APERO COFFEE-SHOP");
		lblNewLabel.setBounds(29, 30, 391, 33);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Montserrat SemiBold", Font.BOLD | Font.ITALIC, 22));
		panel.add(lblNewLabel);
		
		JLabel leave = new JLabel("");
		leave.setIcon(new ImageIcon(AddCoffee.class.getResource("/rw/petitapero/images/Logout.png")));
		leave.setBounds(604, 30, 30, 33);
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
		lbl_close.setIcon(new ImageIcon(AddCoffee.class.getResource("/rw/petitapero/images/Exit.png")));
		lbl_close.setBounds(666, 30, 30, 33);
		 lbl_close.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent arg0) {
	        		System.exit(0);
	        	}
	        });
		panel.add(lbl_close);
		
		
		
		Button addCoffee = new Button("Add New");
		addCoffee.setBounds(223, 448, 120, 36);
		addCoffee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve coffee details from text fields
                String description = descriptionField.getText();
                String name = nameField.getText();
                String category = categoryField.getText();
                double price = Double.parseDouble(priceField.getText());

                // Create a Coffee object
                Coffee coffee = new Coffee();
                coffee.setName(name);
                coffee.setDescription(description);
                coffee.setCategory(category);
                coffee.setPrice(price);

                // Call CoffeeDao to add the coffee
                CoffeeDao coffeeDao = new CoffeeDao();
                int success = coffeeDao.addCoffee(coffee);
                if (success > 0) {
                    JOptionPane.showMessageDialog(AddCoffee.this, "Coffee added successfully!");
                    // Open the home frame
                    Home homeFrame = new Home();
                    homeFrame.setVisible(true);
                    // Close the current login frame
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AddCoffee.this, "Failed to add coffee. Please try again.");
                }
            }
        });
		contentPane.add(addCoffee);
		addCoffee.setName("singUp");
		addCoffee.setFont(new Font("Cantarell Light", Font.BOLD, 12));
		addCoffee.setBackground(new Color(152, 106, 68));
		addCoffee.setActionCommand("add_Coffee");
		
		descriptionField = new JTextField();
		descriptionField.setBounds(223, 258, 272, 30);
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBackground(Color.WHITE);
		nameField.setBounds(223, 187, 272, 30);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		categoryField = new JTextField();
		categoryField.setBounds(223, 325, 272, 30);
		contentPane.add(categoryField);
		categoryField.setColumns(10);
		
		priceField = new JTextField();
		priceField.setBounds(223, 387, 272, 30);
		contentPane.add(priceField);
		priceField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(223, 158, 58, 17);
		contentPane.add(lblName);
		lblName.setFont(new Font("Cantarell", Font.BOLD, 12));
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setBounds(223, 229, 86, 17);
		contentPane.add(lblDescription);
		lblDescription.setFont(new Font("Cantarell", Font.BOLD, 12));
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setBounds(223, 368, 58, 17);
		contentPane.add(lblPrice);
		lblPrice.setFont(new Font("Cantarell", Font.BOLD, 12));
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setBounds(223, 300, 144, 17);
		contentPane.add(lblCategory);
		lblCategory.setFont(new Font("Cantarell", Font.BOLD, 12));
		
		JLabel Title = new JLabel("ADD NEW COFFEE");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setForeground(Color.WHITE);
		Title.setFont(new Font("Cantarell", Font.BOLD, 22));
		Title.setBounds(223, 95, 263, 36);
		contentPane.add(Title);
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(AddCoffee.class.getResource("/rw/petitapero/images/background.png")));
		background.setBounds(0, 80, 755, 466);
		contentPane.add(background);
	}

}
