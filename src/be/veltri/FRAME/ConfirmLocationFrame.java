package be.veltri.FRAME;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.veltri.POJO.Copy;
import be.veltri.POJO.Location;
import be.veltri.POJO.Player;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmLocationFrame extends JFrame {

	private static final long serialVersionUID = -1982117367885869397L;
	private JPanel contentPane;
	private JTextField txtWeeks;
	private JLabel lblNbrUnits;
	private JLabel lblDateTo;

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
	public ConfirmLocationFrame(Copy copy, Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConfirmTheLocation = new JLabel("Confirm the location");
		lblConfirmTheLocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmTheLocation.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblConfirmTheLocation.setBounds(141, 29, 495, 79);
		contentPane.add(lblConfirmTheLocation);
		
		JLabel lblWeeks = new JLabel();
		lblWeeks.setText("How many weeks ?");
		lblWeeks.setHorizontalAlignment(SwingConstants.LEFT);
		lblWeeks.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblWeeks.setBounds(103, 143, 212, 38);
		contentPane.add(lblWeeks);

		txtWeeks = new JTextField();
		txtWeeks.setHorizontalAlignment(SwingConstants.CENTER);
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
				lblNbrUnits.setText("" +copy.getGame().getUnits()*Integer.parseInt(txtWeeks.getText()));
				lblDateTo.setText("" + LocalDate.now().plusWeeks(Integer.parseInt(txtWeeks.getText())));
			}
		});
		txtWeeks.setFont(new Font("Stencil", Font.PLAIN, 20));
		txtWeeks.setBounds(325, 143, 80, 38);
		contentPane.add(txtWeeks);
		txtWeeks.setColumns(10);

		JLabel lblRentTheGame = new JLabel();
		lblRentTheGame.setText("Name of the game : ");
		lblRentTheGame.setHorizontalAlignment(SwingConstants.LEFT);
		lblRentTheGame.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblRentTheGame.setBounds(103, 192, 212, 38);
		contentPane.add(lblRentTheGame);

		JLabel lblGameName = new JLabel();
		lblGameName.setText(copy.getGame().getNameGame());
		lblGameName.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameName.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblGameName.setBounds(324, 192, 212, 38);
		contentPane.add(lblGameName);

		JLabel lblUnits = new JLabel();
		lblUnits.setText("Units : ");
		lblUnits.setHorizontalAlignment(SwingConstants.LEFT);
		lblUnits.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblUnits.setBounds(103, 237, 88, 38);
		contentPane.add(lblUnits);

		lblNbrUnits = new JLabel();
		lblNbrUnits.setText("" +copy.getGame().getUnits()*Integer.parseInt(txtWeeks.getText()));
		lblNbrUnits.setHorizontalAlignment(SwingConstants.LEFT);
		lblNbrUnits.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblNbrUnits.setBounds(193, 237, 212, 38);
		contentPane.add(lblNbrUnits);

		JLabel lblFrom = new JLabel();
		lblFrom.setText("From : ");
		lblFrom.setHorizontalAlignment(SwingConstants.LEFT);
		lblFrom.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblFrom.setBounds(103, 286, 88, 38);
		contentPane.add(lblFrom);

		JLabel lblTo = new JLabel();
		lblTo.setText("To :");
		lblTo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTo.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblTo.setBounds(406, 286, 47, 38);
		contentPane.add(lblTo);

		JLabel lblDateFrom = new JLabel();
		lblDateFrom.setText("" + LocalDate.now());
		lblDateFrom.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateFrom.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblDateFrom.setBounds(178, 286, 212, 38);
		contentPane.add(lblDateFrom);

		lblDateTo = new JLabel();
		lblDateTo.setText("" + LocalDate.now().plusWeeks(Integer.parseInt(txtWeeks.getText())));
		lblDateTo.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateTo.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblDateTo.setBounds(456, 286, 212, 38);
		contentPane.add(lblDateTo);

		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeAllGamesFrame frame = new SeeAllGamesFrame(player);
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
		btnBack.setBounds(42, 393, 50, 47);
		contentPane.add(btnBack);

		JButton btnValid = new JButton("");
		btnValid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nbrUnits = Integer.parseInt(lblNbrUnits.getText());
				LocalDate dateFrom = LocalDate.now();
				LocalDate dateTo = LocalDate.parse(lblDateTo.getText());
				
				if (nbrUnits>player.getBalance()) {
					JOptionPane.showMessageDialog(null, "You don't have enough units");
				} else {
					Location loc = new Location (dateFrom, dateTo, nbrUnits, true, player, copy);
					boolean createLoc = loc.create();
					boolean updateCopy = copy.update();
					if (createLoc && updateCopy) {
						JOptionPane.showMessageDialog(null, "Congratulation you rent the game for " + txtWeeks.getText() + " weeks !");
						SeeAllGamesFrame frame = new SeeAllGamesFrame(player);
						frame.setVisible(true);
						dispose();
					}
				}
			}
		});
		Image imgValid = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/valid.png")).getImage();
		btnValid.setIcon(new ImageIcon(imgValid));
		btnValid.setOpaque(false);
		btnValid.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnValid.setContentAreaFilled(false);
		btnValid.setBorderPainted(false);
		btnValid.setBounds(690, 393, 50, 47);
		contentPane.add(btnValid);
		
		JLabel lblBalance = new JLabel();
		lblBalance.setText(player.getBalance() +" units");
		lblBalance.setHorizontalAlignment(SwingConstants.LEFT);
		lblBalance.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblBalance.setBounds(42, 29, 135, 38);
		contentPane.add(lblBalance);

		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
		
		
	}
}
