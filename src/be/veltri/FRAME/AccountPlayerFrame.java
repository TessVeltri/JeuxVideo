package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.veltri.POJO.Player;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountPlayerFrame extends JFrame {
	
	private static final long serialVersionUID = -338153299718113406L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionFrame frame = new ConnectionFrame();
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
	public AccountPlayerFrame(Player player) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYourAccount = new JLabel("Your Account");
		lblYourAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourAccount.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblYourAccount.setBounds(253, 29, 279, 79);
		contentPane.add(lblYourAccount);
		
		JButton btnPut = new JButton("Put a new game on rent");
		btnPut.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnPut.setBounds(231, 119, 324, 58);
		contentPane.add(btnPut);
		
		JButton btnPut_1 = new JButton("Put a new game on rent");
		btnPut_1.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnPut_1.setBounds(231, 187, 324, 58);
		contentPane.add(btnPut_1);
		
		JButton btnPut_2 = new JButton("Put a new game on rent");
		btnPut_2.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnPut_2.setBounds(231, 256, 324, 58);
		contentPane.add(btnPut_2);
		
		JButton btnPut_3 = new JButton("Put a new game on rent");
		btnPut_3.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnPut_3.setBounds(231, 325, 324, 58);
		contentPane.add(btnPut_3);
		
		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Image imgBack = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/back.png")).getImage();
		btnBack.setIcon(new ImageIcon(imgBack));
		btnBack.setOpaque(false);
		btnBack.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(41, 385, 50, 47);
		contentPane.add(btnBack);
		
		JButton btnDisconnect = new JButton("");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionFrame conn = new ConnectionFrame();
				conn.setVisible(true);
				dispose();
			}
		});
		Image imgDisconnect = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/logOut.png")).getImage();
		btnDisconnect.setIcon(new ImageIcon(imgDisconnect));
		btnDisconnect.setOpaque(false);
		btnDisconnect.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnDisconnect.setContentAreaFilled(false);
		btnDisconnect.setBorderPainted(false);
		btnDisconnect.setBounds(726, 11, 50, 50);
		contentPane.add(btnDisconnect);
	}
}
