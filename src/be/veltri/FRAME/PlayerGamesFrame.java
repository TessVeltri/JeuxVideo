package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.veltri.POJO.Copy;
import be.veltri.POJO.Game;
import be.veltri.POJO.Location;
import be.veltri.POJO.Player;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PlayerGamesFrame extends JFrame {
	private static final long serialVersionUID = -8340450076553035414L;
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
	public PlayerGamesFrame(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMyGames = new JLabel("My games");
		lblMyGames.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyGames.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblMyGames.setBounds(259, 11, 279, 79);
		contentPane.add(lblMyGames);

		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountPlayerFrame frame = new AccountPlayerFrame(player);
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
		btnBack.setBounds(34, 383, 50, 47);
		contentPane.add(btnBack);

		JScrollPane gameScrollPane = new JScrollPane();
		gameScrollPane.setBounds(56, 101, 495, 261);
		contentPane.add(gameScrollPane);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Game name", "Units", "Console", "Version", "Used" }));
		gameScrollPane.setViewportView(table);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ArrayList<Copy> lstCopy = Copy.getAll(player.getUsername());
		for (Copy c : lstCopy) {
			boolean used = c.IsAvailable();
			Object[] row = new Object[] { c.getGame().getNameGame(), c.getGame().getUnits(),
					c.getGame().getNameConsole(), c.getGame().getNameVersion(), used ? "YES" : "NO" };
			model.addRow(row);
		}

		JButton btnMakeAvailable = new JButton("Make available");
		btnMakeAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "No row selected, select one to rent it");
				} else {
					String used = model.getValueAt(index, 4).toString();
					if (used.equals("NO")) {
						String gameName = model.getValueAt(index, 0).toString();
						int units = Integer.parseInt(model.getValueAt(index, 1).toString());
						String consoleName = model.getValueAt(index, 2).toString();
						String versionName = model.getValueAt(index, 3).toString();
						Game game = new Game (gameName, units, consoleName, versionName);
						Copy copy = new Copy (player, game);
						Location loc = new Location(null, null, 0, true, player, null, copy);
						Location location = loc.find();
						int total = location.CalculateBalance();
						location.setTotalUnits(total);
						boolean updateCopy = copy.update();
						boolean updateLoc = location.endLocation();
						player.setBalance(total);
						boolean updateOwner = player.update();
						Player borrower = location.getBorrower();
						borrower.setBalance(total*=-1);
						boolean updateBorrower = borrower.update();
						if (updateCopy && updateLoc && updateOwner && updateBorrower) {
							JOptionPane.showMessageDialog(null, "You make available the game");
							AccountPlayerFrame frame = new AccountPlayerFrame(player);
							frame.setVisible(true);
							dispose();
						}
					}
				}
			}
		});
		btnMakeAvailable.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnMakeAvailable.setBounds(574, 147, 202, 47);
		contentPane.add(btnMakeAvailable);

		JButton btnAddNewGame = new JButton("Add new game");
		btnAddNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGameFrame frame = new AddGameFrame(player);
				frame.setVisible(true);
				dispose();
			}
		});
		btnAddNewGame.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnAddNewGame.setBounds(574, 234, 202, 47);
		contentPane.add(btnAddNewGame);

		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
	}
}
