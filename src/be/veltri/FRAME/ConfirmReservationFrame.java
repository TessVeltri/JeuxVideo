package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.veltri.POJO.Game;
import be.veltri.POJO.Player;
import be.veltri.POJO.Reservation;
import be.veltri.POJO.ReservationStatus;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class ConfirmReservationFrame extends JFrame {

	private static final long serialVersionUID = -720606444795905402L;
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
	public ConfirmReservationFrame(Game game, Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Reservation");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblTitle.setBounds(145, 31, 495, 79);
		contentPane.add(lblTitle);

		JLabel lblSentence1 = new JLabel();
		lblSentence1.setText("The game you want to rent is not available at the");
		lblSentence1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSentence1.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblSentence1.setBounds(76, 153, 633, 44);
		contentPane.add(lblSentence1);

		JLabel lblSentence2 = new JLabel();
		lblSentence2.setText("moment, do you want to reserve it ? ");
		lblSentence2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSentence2.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblSentence2.setBounds(76, 196, 633, 44);
		contentPane.add(lblSentence2);

		JButton btnNo = new JButton("");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeAllGamesFrame frame = new SeeAllGamesFrame(player);
				frame.setVisible(true);
				dispose();
			}
		});
		Image imgCross = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/cross.png")).getImage();
		btnNo.setIcon(new ImageIcon(imgCross));
		btnNo.setOpaque(false);
		btnNo.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnNo.setContentAreaFilled(false);
		btnNo.setBorderPainted(false);
		btnNo.setBounds(301, 340, 50, 47);
		contentPane.add(btnNo);

		JButton btnYes = new JButton("");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reservation reserv = new Reservation(LocalDate.now(), ReservationStatus.InProgress.toString(), player,
						game);
				boolean createReserv = reserv.create();
				if (createReserv) {
					JOptionPane.showMessageDialog(null, "You add a new reservation for "+ game.getNameGame() + " !");
					SeeAllGamesFrame frame = new SeeAllGamesFrame(player);
					frame.setVisible(true);
					dispose();
				}
			}
		});
		Image imgValid = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/valid.png")).getImage();
		btnYes.setIcon(new ImageIcon(imgValid));
		btnYes.setOpaque(false);
		btnYes.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnYes.setContentAreaFilled(false);
		btnYes.setBorderPainted(false);
		btnYes.setBounds(416, 340, 50, 47);
		contentPane.add(btnYes);

		JLabel lblRentTheGame = new JLabel();
		lblRentTheGame.setText("Name of the game : ");
		lblRentTheGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblRentTheGame.setFont(new Font("Stencil", Font.ITALIC, 20));
		lblRentTheGame.setBounds(287, 259, 212, 38);
		contentPane.add(lblRentTheGame);
		
		JLabel lblGameName = new JLabel();
		lblGameName.setText(game.getNameGame() + " for " + game.getNameVersion());
		lblGameName.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameName.setFont(new Font("Stencil", Font.ITALIC, 20));
		lblGameName.setBounds(68, 291, 649, 38);
		contentPane.add(lblGameName);
		
		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
		
	}

}
