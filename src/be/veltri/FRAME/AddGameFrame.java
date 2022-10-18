package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.veltri.POJO.Copy;
import be.veltri.POJO.Game;
import be.veltri.POJO.Message;
import be.veltri.POJO.Player;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddGameFrame extends JFrame {

	private static final long serialVersionUID = 8164099276517389785L;
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
	public AddGameFrame(Player player) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAskVersion = new JButton("");

		JLabel lblAddANew = new JLabel("Add a new game");
		lblAddANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddANew.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblAddANew.setBounds(233, 43, 320, 79);
		contentPane.add(lblAddANew);

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
		btnBack.setBounds(46, 379, 50, 47);
		contentPane.add(btnBack);

		ArrayList<String> lstGames = Game.getAllName("Game", "");
		lstGames.add(0, "Select a game");
		ArrayList<String> lstConsoles = Game.getAllName("Console", "");
		lstConsoles.add(0, "Select a console");

		Object[] lstG = lstGames.toArray();
		JComboBox cbGame = new JComboBox(lstG);
		cbGame.setModel(new DefaultComboBoxModel(lstG));
		cbGame.setFont(new Font("Stencil", Font.PLAIN, 15));
		cbGame.setBounds(253, 144, 280, 47);
		contentPane.add(cbGame);

		JComboBox cbVersion = new JComboBox();
		cbVersion.setModel(new DefaultComboBoxModel(new String[] { "Select a version" }));
		cbVersion.setFont(new Font("Stencil", Font.PLAIN, 15));
		cbVersion.setBounds(253, 260, 280, 47);
		contentPane.add(cbVersion);

		Object[] lstC = lstConsoles.toArray();
		JComboBox cbConsole = new JComboBox(lstC);
		cbConsole.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbConsole.getSelectedItem().toString().equals("Select a console")) {
					btnAskVersion.setEnabled(false);
					cbVersion.setModel(new DefaultComboBoxModel(new String[] { "Select a version" }));

				} else {
					btnAskVersion.setEnabled(true);
					ArrayList<String> lstVersions = Game.getAllName("Version", cbConsole.getSelectedItem().toString());
					lstVersions.add(0, "Select a version");
					Object[] lstV = lstVersions.toArray();
					cbVersion.setModel(new DefaultComboBoxModel(lstV));
				}

			}
		});
		cbConsole.setModel(new DefaultComboBoxModel(lstC));
		cbConsole.setFont(new Font("Stencil", Font.PLAIN, 15));
		cbConsole.setBounds(253, 202, 280, 47);
		contentPane.add(cbConsole);

		JButton btnValid = new JButton("");
		btnValid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbGame.getSelectedItem().toString().equals("Select a game")
						|| cbConsole.getSelectedItem().toString().equals("Select a console")
						|| cbVersion.getSelectedItem().toString().equals("Select a version")) {
					JOptionPane.showMessageDialog(null, "Please select all fields");
				} else {
					String gameName = cbGame.getSelectedItem().toString();
					String consoleName = cbConsole.getSelectedItem().toString();
					String versionName = cbVersion.getSelectedItem().toString();

					Game newG = new Game(gameName, 0, consoleName, versionName);
					Game find = newG.find();
					if (find == null) {
						boolean createGame = newG.create();
						if (createGame) {
							Message msg = new Message(
									"Check the units of the new game " + newG.getNameGame() + " for " + newG.getNameVersion(), false,
									player, null);
							boolean createMsg = msg.create();
							Copy copy = new Copy(player, newG);
							boolean createCopy = copy.create();
							if (createCopy && createMsg) {
								JOptionPane.showMessageDialog(null, "Great! You add a new game for rent to the list");
								HomePlayerFrame frame = new HomePlayerFrame(player);
								frame.setVisible(true);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null,
										"An error has occurred when your game was added, please try again later :( [2]");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"An error has occurred when you create a new game, please try again later :(");
						}
					} else {
						Copy copy = new Copy(player, find);
						boolean createCopy = copy.create();
						if (createCopy) {
							JOptionPane.showMessageDialog(null, "Great! You add a new game for rent to the list");
							HomePlayerFrame frame = new HomePlayerFrame(player);
							frame.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									"An error has occurred when your game was added, please try again later :( [1]");
						}
					}
				}
			}
		});
		Image imgValid = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/valid.png")).getImage();
		btnValid.setIcon(new ImageIcon(imgValid));
		btnValid.setOpaque(false);
		btnValid.setContentAreaFilled(false);
		btnValid.setBorderPainted(false);
		btnValid.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnValid.setBounds(675, 379, 50, 47);
		contentPane.add(btnValid);

		JButton btnAskGame = new JButton("");
		btnAskGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newGame = JOptionPane.showInputDialog("Name of the game to add : ");
				if (newGame != null) {
					Message msg = new Message("Ask for add a new game : " + newGame, false, player, null);
					boolean createMsg = msg.create();
					if (createMsg) {
						JOptionPane.showMessageDialog(null,
								"Your request for a new game has been sent to the administrator :)");
					} else {
						JOptionPane.showMessageDialog(null, "An error has occurred, please try again later :(");
					}
				}
			}
		});
		Image imgPlus = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/plus.png")).getImage();
		btnAskGame.setIcon(new ImageIcon(imgPlus));
		btnAskGame.setOpaque(false);
		btnAskGame.setContentAreaFilled(false);
		btnAskGame.setBorderPainted(false);
		btnAskGame.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnAskGame.setBounds(543, 144, 40, 40);
		contentPane.add(btnAskGame);

		JButton btnAskConsole = new JButton("");
		btnAskConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newConsole = JOptionPane.showInputDialog("Name of the console to add : ");
				if (newConsole != null) {
					Message msg = new Message("Ask for add a new console : " + newConsole, false, player, null);
					boolean createMsg = msg.create();
					if (createMsg) {
						JOptionPane.showMessageDialog(null,
								"Your request for a new console has been sent to the administrator :)");
					} else {
						JOptionPane.showMessageDialog(null, "An error has occurred, please try again later :(");
					}

				}
			}
		});
		btnAskConsole.setIcon(new ImageIcon(imgPlus));
		btnAskConsole.setOpaque(false);
		btnAskConsole.setContentAreaFilled(false);
		btnAskConsole.setBorderPainted(false);
		btnAskConsole.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnAskConsole.setBounds(543, 202, 40, 40);
		contentPane.add(btnAskConsole);

		btnAskVersion.setEnabled(false);
		btnAskVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newVersion = JOptionPane.showInputDialog("Name of the version to add : ");
				if (newVersion != null) {
					Message msg = new Message("Ask for add a new version : " + newVersion + " for the console : "
							+ cbConsole.getSelectedItem().toString(), false, player, null);
					boolean createMsg = msg.create();
					if (createMsg) {
						JOptionPane.showMessageDialog(null,
								"Your request for a new version has been sent to the administrator :)");
					} else {
						JOptionPane.showMessageDialog(null, "An error has occurred, please try again later :(");
					}

				}
			}
		});
		btnAskVersion.setIcon(new ImageIcon(imgPlus));
		btnAskVersion.setOpaque(false);
		btnAskVersion.setContentAreaFilled(false);
		btnAskVersion.setBorderPainted(false);
		btnAskVersion.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnAskVersion.setBounds(543, 260, 40, 40);
		contentPane.add(btnAskVersion);

		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);

	}
}
