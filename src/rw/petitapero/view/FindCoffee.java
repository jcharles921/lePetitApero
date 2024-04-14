package rw.petitapero.view;
import rw.petitapero.dao.CoffeeDao;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import java.awt.Cursor;


public class FindCoffee extends JFrame {

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
					FindCoffee frame = new FindCoffee();
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
	public FindCoffee() {
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
	            FindCoffee.this.setLocation(x - xx, y - xy);  
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
		
		
		
		Button findOneCoffee = new Button("Find one");
		findOneCoffee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		findOneCoffee.setForeground(Color.WHITE);
		findOneCoffee.setBounds(223, 448, 77, 36);
		findOneCoffee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve coffee details from text fields
            	try {
                    // Retrieve coffee details from text fields
                    String name = nameField.getText(); // Get name from nameField
                    CoffeeDao coffeeDao = new CoffeeDao();
                    Coffee foundCoffee = coffeeDao.findCoffeeByName(name);

                    if (foundCoffee != null) {
                        JOptionPane.showMessageDialog(FindCoffee.this, "Coffee Found successfully!");
                        // Set retrieved coffee details to respective fields
                        descriptionField.setText(foundCoffee.getDescription());
                        nameField.setText(foundCoffee.getName());
                        categoryField.setText(foundCoffee.getCategory());
                        priceField.setText(String.valueOf(foundCoffee.getPrice())); // Convert price to string
                    } else {
                        JOptionPane.showMessageDialog(FindCoffee.this, "Failed to Find Coffee");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FindCoffee.this, ex);
                    ex.printStackTrace();
                }
            }
        });
		contentPane.add(findOneCoffee);
		findOneCoffee.setName("findOne");
		findOneCoffee.setFont(new Font("Cantarell Light", Font.BOLD, 12));
		findOneCoffee.setBackground(new Color(152, 106, 68));
		findOneCoffee.setActionCommand("add_Coffee");
		
		descriptionField = new JTextField();
		descriptionField.setBounds(223, 258, 310, 30);
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBackground(Color.WHITE);
		nameField.setBounds(223, 187, 310, 30);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		categoryField = new JTextField();
		categoryField.setBounds(223, 325, 310, 30);
		contentPane.add(categoryField);
		categoryField.setColumns(10);
		
		priceField = new JTextField();
		priceField.setBounds(223, 387, 310, 30);
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
		
		JLabel Title = new JLabel("FIND COFFEE");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setForeground(Color.WHITE);
		Title.setFont(new Font("Cantarell", Font.BOLD, 22));
		Title.setBounds(245, 95, 263, 36);
		contentPane.add(Title);
		
		Button updateCoffee = new Button("Update");
		updateCoffee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateCoffee.setForeground(Color.WHITE);
		updateCoffee.setName("singUp");
		updateCoffee.setFont(new Font("Cantarell Light", Font.BOLD, 12));
		updateCoffee.setBackground(new Color(152, 106, 68));
		updateCoffee.setActionCommand("updateCoffee");
		updateCoffee.setBounds(343, 448, 77, 36);
		updateCoffee.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String name = nameField.getText();
		            String description = descriptionField.getText();
		            String category = categoryField.getText();
		            double price = Double.parseDouble(priceField.getText());
		            Coffee coffee = new Coffee(name, description, category, price);
		            CoffeeDao coffeeDao = new CoffeeDao();
		            Integer updated = coffeeDao.updateCoffee(coffee);

		            if (updated.intValue() >0) {
		                JOptionPane.showMessageDialog(FindCoffee.this, "Coffee updated successfully!");
		                Home homeFrame = new Home();
	                    homeFrame.setVisible(true);
	                    dispose();
		            } else {
		                JOptionPane.showMessageDialog(FindCoffee.this, "Failed to update Coffee");
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(FindCoffee.this, ex.getMessage());
		            ex.printStackTrace();
		        }
		    }
		});
		contentPane.add(updateCoffee);
		
		Button delete = new Button("Delete ");
		delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		delete.setForeground(Color.WHITE);
		delete.setName("");
		delete.setFont(new Font("Cantarell Light", Font.BOLD, 12));
		delete.setBackground(Color.RED);
		delete.setActionCommand("delete_coffee");
		delete.setBounds(456, 448, 77, 36);
		delete.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String name = nameField.getText();
		            CoffeeDao coffeeDao = new CoffeeDao();
		            Integer deleted = coffeeDao.deleteCoffee(name);

		            if (deleted!=null) {
		            	  if (deleted.intValue() > 0) {
		            		  JOptionPane.showMessageDialog(FindCoffee.this, "Coffee deleted successfully!");
		            		    Home homeFrame = new Home();
			                    homeFrame.setVisible(true);
			                    dispose();
		            	  }
		            	  else {
		            		  JOptionPane.showMessageDialog(FindCoffee.this, "Error Not Found");
		            	  }
		              
		            } else {
		                JOptionPane.showMessageDialog(FindCoffee.this, "Failed to delete Coffee");
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(FindCoffee.this, ex.getMessage());
		            ex.printStackTrace();
		        }
		    }
		});
		contentPane.add(delete);
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(AddCoffee.class.getResource("/rw/petitapero/images/background.png")));
		background.setBounds(0, 80, 755, 466);
		contentPane.add(background);
	}

}
