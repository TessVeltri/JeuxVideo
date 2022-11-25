package be.veltri.FRAME;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.veltri.POJO.Player;
import be.veltri.POJO.Rating;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
		
		JLabel lblBalance = new JLabel();
		if (player.getBalance()<=0) {
			lblBalance.setForeground(Color.red);
			lblBalance.setText("0 units");
		} else {
			lblBalance.setText(player.getBalance() + " units");
		}
		
		lblBalance.setHorizontalAlignment(SwingConstants.LEFT);
		lblBalance.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblBalance.setBounds(35, 28, 135, 38);
		contentPane.add(lblBalance);
		
		JLabel lblYourAccount = new JLabel("Your Account");
		lblYourAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourAccount.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblYourAccount.setBounds(253, 29, 279, 79);
		contentPane.add(lblYourAccount);
		
		JButton btnMessages = new JButton("My messages");
		btnMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerMessagesFrame frame = new PlayerMessagesFrame(player);
				frame.setVisible(true);
				dispose();
			}
		});
		btnMessages.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnMessages.setBounds(231, 119, 324, 58);
		contentPane.add(btnMessages);
		
		JButton btnGames = new JButton("My games");
		btnGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerGamesFrame frame = new PlayerGamesFrame(player);
				frame.setVisible(true);
				dispose();
			}
		});
		btnGames.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnGames.setBounds(231, 187, 324, 58);
		contentPane.add(btnGames);
		
		JButton btnRentals = new JButton("My rentals");
		btnRentals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerRentalsFrame frame = new PlayerRentalsFrame(player);
				frame.setVisible(true);
				dispose();
			}
		});
		btnRentals.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnRentals.setBounds(231, 256, 324, 58);
		contentPane.add(btnRentals);
		
		JButton btnReservations = new JButton("My reservations");
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerReservationsFrame frame = new PlayerReservationsFrame(player);
				frame.setVisible(true);
				dispose();
			}
		});
		btnReservations.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnReservations.setBounds(231, 325, 324, 58);
		contentPane.add(btnReservations);
		
		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePlayerFrame frame = new HomePlayerFrame(player);
				frame.setVisible(true);
				dispose();
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

		JLabel lblRating = new JLabel();
		int averageRating = Rating.getAverage(player);
		lblRating.setText(averageRating + "/5 stars");
		lblRating.setHorizontalAlignment(SwingConstants.LEFT);
		lblRating.setFont(new Font("Stencil", Font.PLAIN, 15));
		lblRating.setBounds(35, 77, 135, 27);
		contentPane.add(lblRating);	
		
		JLabel lblNbrRating = new JLabel();
		ArrayList<Rating> allRate = Rating.getAll(player);
		if (allRate.size()==0)
			lblNbrRating.setText("no rating");
		else 
			lblNbrRating.setText(allRate.size() + " ratings");
		
		lblNbrRating.setHorizontalAlignment(SwingConstants.LEFT);
		lblNbrRating.setFont(new Font("Stencil", Font.PLAIN, 12));
		lblNbrRating.setBounds(35, 100, 135, 27);
		contentPane.add(lblNbrRating);
		
		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);

		
	}
}
