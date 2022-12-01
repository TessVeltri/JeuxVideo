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
import be.veltri.POJO.Reservation;
import be.veltri.POJO.ReservationStatus;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SeeAllGamesFrame extends JFrame {

	private static final long serialVersionUID = 5977157189933631246L;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SeeAllGamesFrame(Player player) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRentAGame = new JLabel("Rent a game");
		lblRentAGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblRentAGame.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblRentAGame.setBounds(253, 11, 279, 79);
		contentPane.add(lblRentAGame);

		JButton btnDisconnect = new JButton("");
		btnDisconnect.setOpaque(false);
		btnDisconnect.setContentAreaFilled(false);
		btnDisconnect.setBorderPainted(false);
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionFrame conn = new ConnectionFrame();
				conn.setVisible(true);
				dispose();
			}
		});
		Image imgDisconnect = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/logOut.png")).getImage();
		btnDisconnect.setIcon(new ImageIcon(imgDisconnect));
		btnDisconnect.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnDisconnect.setBounds(726, 19, 50, 47);
		contentPane.add(btnDisconnect);

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
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnBack.setBounds(37, 405, 50, 47);
		contentPane.add(btnBack);

		JScrollPane gameScrollPane = new JScrollPane();
		gameScrollPane.setBounds(69, 134, 495, 261);
		contentPane.add(gameScrollPane);

		JTable table = new JTable();
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Game name", "Units", "Console", "Version" }));

		gameScrollPane.setViewportView(table);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ArrayList<Game> lstGame = Game.getAll(null);

		for (Game g : lstGame) {
			if (Location.getAll(player, g).size() > 0) {

			} else {
				boolean check = false;
				for (Copy c : player.getLstCopy()) {
					if (g.getNameGame().equals(c.getGame().getNameGame())
							&& g.getNameVersion().equals(c.getGame().getNameVersion()) && check == false) {
						check = true;
					}
				}
				if (!check) {
					Object[] row = new Object[] { g.getNameGame(), g.getUnits(), g.getNameConsole(),
							g.getNameVersion() };
					model.addRow(row);
				}
			}

		}

		ArrayList<String> lstConsoles = Game.getAllName("Console", "");
		lstConsoles.add(0, "Select a console");

		JComboBox cbVersion = new JComboBox();
		cbVersion.setModel(new DefaultComboBoxModel(new String[] { "Select a version" }));
		cbVersion.setFont(new Font("Stencil", Font.PLAIN, 15));
		cbVersion.setBounds(289, 101, 199, 22);
		contentPane.add(cbVersion);

		Object[] lstC = lstConsoles.toArray();
		JComboBox cbConsole = new JComboBox(lstC);
		cbConsole.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbConsole.getSelectedItem().toString().equals("Select a console")) {
					cbVersion.setModel(new DefaultComboBoxModel(new String[] { "Select a version" }));
				} else {
					ArrayList<String> lstVersions = Game.getAllName("Version", cbConsole.getSelectedItem().toString());
					lstVersions.add(0, "Select a version");
					Object[] lstV = lstVersions.toArray();
					cbVersion.setModel(new DefaultComboBoxModel(lstV));
				}
			}
		});
		cbConsole.setModel(new DefaultComboBoxModel(lstC));
		cbConsole.setFont(new Font("Stencil", Font.PLAIN, 15));
		cbConsole.setBounds(69, 101, 199, 22);
		contentPane.add(cbConsole);

		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int modelCount = model.getRowCount();
				for (int i = 0; i < modelCount; i++)
					model.removeRow(0);
				String console = cbConsole.getSelectedItem().toString();
				String version = cbVersion.getSelectedItem().toString();
				if (version.equals("Select a version") && console.equals("Select a console")) {
					ArrayList<Game> lstGame = Game.getAll(null);
					for (Game g : lstGame) {
						if (Location.getAll(player, g).size() > 0) {

						} else {

							boolean check = false;
							for (Copy c : player.getLstCopy()) {
								if (g.getNameGame().equals(c.getGame().getNameGame())
										&& g.getNameVersion().equals(c.getGame().getNameVersion()) && check == false) {
									check = true;
								}
							}
							if (!check) {
								Object[] row = new Object[] { g.getNameGame(), g.getUnits(), g.getNameConsole(),
										g.getNameVersion() };
								model.addRow(row);
							}
						}
					}
				} else if (version.equals("Select a version")) {
					JOptionPane.showMessageDialog(null, "Select a version to make a research");
				} else {
					Game game = new Game("", 0, "", version);
					ArrayList<Game> lstGameByVersion = Game.getAll(game);
					for (Game g : lstGameByVersion) {
						if (Location.getAll(player,g).size() > 0) {

						} else {
							boolean check = false;
							for (Copy c : player.getLstCopy()) {
								if (g.getNameGame().equals(c.getGame().getNameGame())
										&& g.getNameVersion().equals(c.getGame().getNameVersion()) && check == false) {
									check = true;
								}
							}
							if (!check) {
								Object[] row = new Object[] { g.getNameGame(), g.getUnits(), g.getNameConsole(),
										g.getNameVersion() };
								model.addRow(row);
							}
						}
					}
				}
			}
		});
		Image imgSearch = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/search.png")).getImage();
		btnSearch.setIcon(new ImageIcon(imgSearch));
		btnSearch.setOpaque(false);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setBorderPainted(false);
		btnSearch.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnSearch.setBounds(498, 101, 20, 20);
		contentPane.add(btnSearch);

		JButton btnRent = new JButton("Rent");
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "No row selected, select one to rent it");
				} else {
					String gameName = model.getValueAt(index, 0).toString();
					int units = Integer.parseInt(model.getValueAt(index, 1).toString());
					String consoleName = model.getValueAt(index, 2).toString();
					String versionName = model.getValueAt(index, 3).toString();
					Game game = new Game(gameName, units, consoleName, versionName);
					Copy copy = new Copy(null, game);
					boolean checkCopy = copy.isAvailable();

					if (checkCopy) {
						Copy copyChecked = game.copyAvailable(copy);
						ConfirmLocationFrame frame = new ConfirmLocationFrame(copyChecked, player);
						frame.setVisible(true);
						dispose();
					} else {
						Reservation resTmp = new Reservation(null, ReservationStatus.InProgress.toString(), 0, player,
								game);
						Reservation res = resTmp.find();
						if (res != null) {
							JOptionPane.showMessageDialog(null, "You already reserve this game");
						} else {
							ConfirmReservationFrame frame = new ConfirmReservationFrame(game, player);
							frame.setVisible(true);
							dispose();
						}

					}
				}
			}
		});
		btnRent.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnRent.setBounds(586, 246, 175, 47);
		contentPane.add(btnRent);

		JLabel lblBalance = new JLabel();
		lblBalance.setText(player.getBalance() + " units");
		lblBalance.setHorizontalAlignment(SwingConstants.LEFT);
		lblBalance.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblBalance.setBounds(37, 29, 135, 38);
		contentPane.add(lblBalance);

		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
	}
}
