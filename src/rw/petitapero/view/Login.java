package rw.petitapero.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userEmail;
	private JTextField userPassword;
	private JPanel panel;
	private JLabel label;
	private JLabel lblSignUp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setUndecorated(true);
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
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(85, 216, 58, 17);
		contentPane.add(lblPassword);
		lblPassword.setFont(new Font("Cantarell", Font.BOLD, 12));
		
		userPassword = new JTextField();
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
		
		panel = new JPanel();
		panel.setBounds(398, 0, 426, 472);
		contentPane.add(panel);
		panel.setLayout(null);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/rw/petitapero/images/CoffeeFront(2).jpg")));
		label.setBounds(0, -38, 440, 498);
		panel.add(label);
		
		lblSignUp = new JLabel("Sign Up");
		lblSignUp.setForeground(new Color(152, 106, 68));
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setFont(new Font("Cantarell", Font.BOLD, 12));
		lblSignUp.setBounds(286, 316, 65, 36);
		contentPane.add(lblSignUp);
	}
}
