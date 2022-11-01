package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AdminManageGame extends JFrame {
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
	public AdminManageGame(Admin admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblManageGame = new JLabel("Manage games");
		lblManageGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageGame.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblManageGame.setBounds(188, 26, 410, 79);
		contentPane.add(lblManageGame);
		
		JLabel lblGameName = new JLabel("New game name:");
		lblGameName.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameName.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblGameName.setBounds(198, 343, 128, 36);
		contentPane.add(lblGameName);
		
		txtGameName = new JTextField();
		txtGameName.setHorizontalAlignment(SwingConstants.LEFT);
		txtGameName.setForeground(Color.BLACK);
		txtGameName.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtGameName.setColumns(10);
		txtGameName.setBounds(347, 346, 244, 36);
		contentPane.add(txtGameName);
		
		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeAdminFrame frame = new HomeAdminFrame(admin);
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
		btnBack.setBounds(31, 379, 50, 47);
		contentPane.add(btnBack);
		
		JButton btnYes = new JButton("");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gameName = txtGameName.getText();
				if (!gameName.equals("")) {
					Game game = new Game (gameName, 0, "", "");
					boolean createGame = game.create();
					if (createGame) {
						JOptionPane.showMessageDialog(null, "Success insert new game, change the units of the game");
						AdminManageUnits frame = new AdminManageUnits(admin);
						frame.setVisible(true);
						dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Complete game name to confirm");
				}
			}
		});
		Image imgValid = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/valid.png")).getImage();
		btnYes.setIcon(new ImageIcon(imgValid));
		btnYes.setOpaque(false);
		btnYes.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnYes.setContentAreaFilled(false);
		btnYes.setBorderPainted(false);
		btnYes.setBounds(684, 379, 50, 47);
		contentPane.add(btnYes);
		
		JScrollPane gameScrollPane = new JScrollPane();
		gameScrollPane.setBounds(227, 103, 331, 225);
		contentPane.add(gameScrollPane);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Game name" }));
		
		gameScrollPane.setViewportView(table);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ArrayList<String> lstGames = Game.getAllName("Game", "");
		for (String s : lstGames) {
			Object[] row = new Object[] { s };
			model.addRow(row);
		}
		
		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
		
	}
}
