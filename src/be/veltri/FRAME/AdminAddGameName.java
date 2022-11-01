package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.veltri.POJO.Admin;
import be.veltri.POJO.Game;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminAddGameName extends JFrame {
	private static final long serialVersionUID = -8106010411576515450L;
	private JPanel contentPane;
	private JTextField txtGameName;

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
	public AdminAddGameName(Admin admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddNewGame = new JLabel("Add new game name");
		lblAddNewGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewGame.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblAddNewGame.setBounds(198, 91, 410, 79);
		contentPane.add(lblAddNewGame);
		
		JLabel lblGameName = new JLabel("Game name:");
		lblGameName.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameName.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblGameName.setBounds(215, 208, 117, 36);
		contentPane.add(lblGameName);
		
		txtGameName = new JTextField();
		txtGameName.setHorizontalAlignment(SwingConstants.LEFT);
		txtGameName.setForeground(Color.BLACK);
		txtGameName.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtGameName.setColumns(10);
		txtGameName.setBounds(342, 208, 244, 36);
		contentPane.add(txtGameName);
		
		JButton btnNo = new JButton("");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeAdminFrame frame = new HomeAdminFrame(admin);
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
		btnNo.setBounds(295, 302, 50, 47);
		contentPane.add(btnNo);
		
		JButton btnYes = new JButton("");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gameName = txtGameName.getText();
				if (!gameName.equals("")) {
					Game game = new Game (gameName, 0, "", "XBOX 360");
					boolean createGame = game.create();
					if (createGame) {
						JOptionPane.showMessageDialog(null, "Success insert new game, change the units of the game");
						AdminManageUnits frame = new AdminManageUnits(admin);
						frame.setVisible(true);
						dispose();
					}
				}
			}
		});
		Image imgValid = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/valid.png")).getImage();
		btnYes.setIcon(new ImageIcon(imgValid));
		btnYes.setOpaque(false);
		btnYes.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnYes.setContentAreaFilled(false);
		btnYes.setBorderPainted(false);
		btnYes.setBounds(427, 302, 50, 47);
		contentPane.add(btnYes);
		
		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
	}
}
