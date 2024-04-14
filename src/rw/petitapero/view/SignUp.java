package rw.petitapero.view;

import rw.petitapero.model.User;
import rw.petitapero.dao.UserDao;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userEmail;
	private JPasswordField userPassword;
	private JPasswordField userConfirmPassword;
	private JTextField userName;
	int xx,xy;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public SignUp() {
		setResizable(false);
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 472);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 459, 472);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				 xx = e.getX();
			     xy = e.getY();
			}
		});
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				int x = arg0.getXOnScreen();
	            int y = arg0.getYOnScreen();
	            SignUp.this.setLocation(x - xx, y - xy);  
			}
		});
		label.setIcon(new ImageIcon(SignUp.class.getResource("/rw/petitapero/images/espressoitem2.jpeg")));
		label.setBounds(-42, -91, 501, 563);
		panel.add(label);
		
		Button singUp = new Button("Sign Up");
        singUp.addActionListener(new java.awt.event.ActionListener()  {
            public void actionPerformed(ActionEvent e) {
                // Perform validation checks on text fields
                String name = userName.getText();
                String email = userEmail.getText();
                String password = userPassword.getText();
                String confirmPassword = userConfirmPassword.getText();
                // Check if any field is empty
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                	JOptionPane.showMessageDialog(SignUp.this, "Missing Data in Fields");
                    return;
                }

                // Check if passwords match
                if (!password.equals(confirmPassword)) {
                	JOptionPane.showMessageDialog(SignUp.this, "Passowrd doesn't match !!!");
                    return;
                }
                // Call UserDao to register user
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(confirmPassword);
                user.setRole(2);
                UserDao userDao = new UserDao();
                Integer success = userDao.registerUser(user);
                if (success != null) {
                    if (success.intValue() > 0) {
                        JOptionPane.showMessageDialog(SignUp.this, "Data Saved!");
                        Login LoginFrame = new Login();
                        LoginFrame.setUndecorated(true);	
                        LoginFrame.setVisible(true);
                        
                        // Close the current sign Up frame
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(SignUp.this, "Data Not Saved!");
                    }
                } else {
                    JOptionPane.showMessageDialog(SignUp.this, "Error occurred while registering user!");
                }
            }
        });
		singUp.setName("singUp");
		singUp.setBounds(495, 344, 120, 36);
		singUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		singUp.setActionCommand("Sign_up");
		singUp.setFont(new Font("Cantarell Light", Font.BOLD, 12));
		singUp.setBackground(new Color(152, 106, 68));
		contentPane.add(singUp);
		
		userEmail = new JTextField();
		userEmail.setBounds(495, 144, 272, 30);
		contentPane.add(userEmail);
		userEmail.setColumns(10);
		
		userPassword = new JPasswordField();
		userPassword.setBounds(495, 207, 272, 30);
		userPassword.setColumns(10);
		contentPane.add(userPassword);
		
		userConfirmPassword =new JPasswordField();
		userConfirmPassword.setBounds(495, 270, 272, 30);
		userConfirmPassword.setColumns(10);
		contentPane.add(userConfirmPassword);
		
		userName = new JTextField();
		userName.setBounds(495, 80, 272, 30);
		userName.setColumns(10);
		contentPane.add(userName);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(495, 57, 58, 17);
		lblName.setFont(new Font("Cantarell", Font.BOLD, 12));
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(495, 122, 58, 17);
		lblEmail.setFont(new Font("Cantarell", Font.BOLD, 12));
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(495, 186, 58, 17);
		lblPassword.setFont(new Font("Cantarell", Font.BOLD, 12));
		contentPane.add(lblPassword);
		
		JLabel lblConfirm = new JLabel("Confirm Password");
		lblConfirm.setBounds(495, 249, 144, 17);
		lblConfirm.setFont(new Font("Cantarell", Font.BOLD, 12));
		contentPane.add(lblConfirm);
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lbl_close.setForeground(new Color(237, 51, 59));
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setFont(new Font("Cantarell", Font.BOLD, 28));
		lbl_close.setBounds(754, 23, 58, 30);
		contentPane.add(lbl_close);
		
	}
}
