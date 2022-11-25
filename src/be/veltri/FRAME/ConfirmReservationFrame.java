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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ConfirmReservationFrame extends JFrame {

	private static final long serialVersionUID = -720606444795905402L;
	private JPanel contentPane;
	private JTextField txtWeeks;

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
		lblSentence1.setBounds(76, 121, 633, 44);
		contentPane.add(lblSentence1);

		JLabel lblSentence2 = new JLabel();
		lblSentence2.setText("moment, do you want to reserve it ? ");
		lblSentence2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSentence2.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblSentence2.setBounds(76, 158, 633, 44);
		contentPane.add(lblSentence2);
		
		JLabel lblNbrWeeks = new JLabel();
		lblNbrWeeks.setText("How many weeks ?");
		lblNbrWeeks.setHorizontalAlignment(SwingConstants.CENTER);
		lblNbrWeeks.setFont(new Font("Stencil", Font.ITALIC, 20));
		lblNbrWeeks.setBounds(216, 303, 212, 38);
		contentPane.add(lblNbrWeeks);
		
		txtWeeks = new JTextField();
		txtWeeks.setText("1");
		txtWeeks.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int nbr = e.getKeyChar();
				if (!Character.isDigit(nbr)) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				int nbr = Integer.parseInt(txtWeeks.getText());
				if (nbr<1) {
					txtWeeks.setText("1");
				}else if (nbr>9) {
					txtWeeks.setText("9");
				}
			}
		});
		txtWeeks.setHorizontalAlignment(SwingConstants.CENTER);
		txtWeeks.setFont(new Font("Stencil", Font.PLAIN, 20));
		txtWeeks.setColumns(10);
		txtWeeks.setBounds(446, 304, 80, 38);
		contentPane.add(txtWeeks);

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
		btnNo.setBounds(301, 372, 50, 47);
		contentPane.add(btnNo);

		JButton btnYes = new JButton("");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int weeks = Integer.parseInt(txtWeeks.getText());
				Reservation reserv = new Reservation(LocalDate.now(), ReservationStatus.InProgress.toString(), weeks, player,
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
		btnYes.setBounds(416, 372, 50, 47);
		contentPane.add(btnYes);

		JLabel lblRentTheGame = new JLabel();
		lblRentTheGame.setText("Name of the game : ");
		lblRentTheGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblRentTheGame.setFont(new Font("Stencil", Font.ITALIC, 20));
		lblRentTheGame.setBounds(287, 201, 212, 38);
		contentPane.add(lblRentTheGame);
		
		JLabel lblGameName = new JLabel();
		lblGameName.setText(game.getNameGame() + " for " + game.getNameVersion());
		lblGameName.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameName.setFont(new Font("Stencil", Font.ITALIC, 20));
		lblGameName.setBounds(68, 233, 649, 38);
		contentPane.add(lblGameName);
		
		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
		
		
	}
}
