package rw.petitapero.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import rw.petitapero.dao.UserDao;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userEmail;
	private JPasswordField userPassword;
	private JPanel panel;
	private JLabel label;
	private JLabel lblSignUp;
	int xx,xy;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setResizable(false);
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 472);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Button login = new Button("Login");
		login.setName("login");
		login.setBounds(85, 316, 111, 36);
		login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		login.setActionCommand("Sign_up");
		login.setFont(new Font("Cantarell Light", Font.BOLD, 12));
		login.setBackground(new Color(152, 106, 68));
		contentPane.add(login);
		login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Validate email format
                String email = userEmail.getText();
                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(Login.this, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Check if email and password fields are empty
                String password = new String(userPassword.getPassword());
                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Login.this, "Email and password are required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                UserDao userDao = new UserDao();
                int loginSuccess = userDao.loginUser(email, password);
                if (loginSuccess == 1) {
                    // Open the home frame
                    Home homeFrame = new Home();
                    homeFrame.setVisible(true);
                    // Close the current login frame
                    dispose();
                } else if (loginSuccess == 0) {
                    JOptionPane.showMessageDialog(Login.this, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Login.this, "An error occurred while logging in.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(85, 216, 58, 17);
		contentPane.add(lblPassword);
		lblPassword.setFont(new Font("Cantarell", Font.BOLD, 12));
		
		userPassword = new JPasswordField();
		userPassword.setBounds(85, 245, 272, 30);
		contentPane.add(userPassword);
		userPassword.setColumns(10);
		
		userEmail = new JTextField();
		userEmail.setBounds(85, 174, 272, 30);
		contentPane.add(userEmail);
		userEmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(85, 145, 58, 17);
		contentPane.add(lblEmail);
		lblEmail.setFont(new Font("Cantarell", Font.BOLD, 12));
		
		JLabel lbl_close = new JLabel("");
		lbl_close.setIcon(new ImageIcon(Login.class.getResource("/rw/petitapero/images/Exit.png")));
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
		lbl_close.setBounds(0, 22, 58, 30);
		contentPane.add(lbl_close);
		
		panel = new JPanel();
		panel.setBounds(398, 0, 426, 472);
		contentPane.add(panel);
		panel.setLayout(null);
		
		label = new JLabel("");
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
	            Login.this.setLocation(x - xx, y - xy);  
			}
		});
		label.setIcon(new ImageIcon(Login.class.getResource("/rw/petitapero/images/CoffeeFront(2).jpg")));
		label.setBounds(0, -38, 440, 498);
		panel.add(label);
		
		lblSignUp = new JLabel("Sign Up");
		lblSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSignUp.setForeground(new Color(152, 106, 68));
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setFont(new Font("Cantarell", Font.BOLD, 12));
		lblSignUp.setBounds(286, 316, 65, 36);
		contentPane.add(lblSignUp);
		 lblSignUp.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                SignUp signUpFrame = new SignUp();
	                signUpFrame.setUndecorated(true);
	                signUpFrame.setVisible(true);
	                // Close the current login frame
	                dispose();
	            }
	        });
	}
    private boolean isValidEmail(String email) {
        // Use a simple regex pattern to validate email format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
