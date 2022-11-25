package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.veltri.POJO.Admin;
import be.veltri.POJO.Game;
import be.veltri.POJO.UnitsHistory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AdminManageUnits extends JFrame {
	private static final long serialVersionUID = 1536471930906574681L;
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
	public AdminManageUnits(Admin admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnBack.setBounds(37, 405, 50, 47);
		contentPane.add(btnBack);
		
		JLabel lblManageUnits = new JLabel("Manage units");
		lblManageUnits.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageUnits.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblManageUnits.setBounds(188, 11, 410, 79);
		contentPane.add(lblManageUnits);
		
		JScrollPane gameScrollPane = new JScrollPane();
		gameScrollPane.setBounds(50, 124, 495, 261);
		contentPane.add(gameScrollPane);
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Game name", "Units", "Console", "Version" }));
		
		gameScrollPane.setViewportView(table);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ArrayList<Game> lstGame = Game.getAll("");
		for (Game g : lstGame) {
			Object[] row = new Object[] { g.getNameGame(),g.getUnits(), g.getNameConsole(),
					g.getNameVersion()};
			model.addRow(row);
		}
		
		ArrayList<String> lstConsoles = Game.getAllName("Console", "");
		lstConsoles.add(0, "Select a console");
		
		JComboBox cbVersion = new JComboBox();
		cbVersion.setModel(new DefaultComboBoxModel(new String[] { "Select a version" }));
		cbVersion.setFont(new Font("Stencil", Font.PLAIN, 15));
		cbVersion.setBounds(270, 91, 199, 22);
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
		cbConsole.setBounds(50, 91, 199, 22);
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
					ArrayList<Game> lstGame = Game.getAll("");
					for (Game g : lstGame) {
						Object[] row = new Object[] { g.getNameGame(),g.getUnits(), g.getNameConsole(),
								g.getNameVersion()};
						model.addRow(row);
					}
				} else if (version.equals("Select a version")) {
					JOptionPane.showMessageDialog(null, "Select a version to make a research");
				} else {
					ArrayList<Game> lstGameByVersion = Game.getAll(version);
					for (Game g : lstGameByVersion) {
						Object[] row = new Object[] { g.getNameGame(),g.getUnits(), g.getNameConsole(),
								g.getNameVersion()};
						model.addRow(row);
					}
				}
			}
		});
		Image imgSearch = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/search.png")).getImage();
		btnSearch.setIcon(new ImageIcon(imgSearch));
		btnSearch.setOpaque(false);
		btnSearch.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnSearch.setContentAreaFilled(false);
		btnSearch.setBorderPainted(false);
		btnSearch.setBounds(479, 91, 20, 20);
		contentPane.add(btnSearch);
		
		JButton btnManage = new JButton("Manage");
		btnManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "No row selected, select one to manage the units");
				} else {
					String newUnits = JOptionPane.showInputDialog("New units : ");
					if (newUnits == null || newUnits.equals("") || !newUnits.matches("^[12345]*$"))
						newUnits = JOptionPane.showInputDialog("Only number accepted (1 to 5), new units : ");
					int units = Integer.parseInt(newUnits);
					String gameName = model.getValueAt(index, 0).toString();
					String consoleName = model.getValueAt(index, 2).toString();
					String versionName = model.getValueAt(index, 3).toString();
					Game game = new Game (gameName, units, consoleName, versionName);
					boolean updateGame = game.update();
					UnitsHistory unitH = new UnitsHistory(LocalDate.now(), units, game);
					boolean createUH = unitH.create();
					if (updateGame && createUH) {
						JOptionPane.showMessageDialog(null, "Units update");
						AdminManageUnits frame = new AdminManageUnits(admin);
						frame.setVisible(true);
						dispose();
					}
				}
			}
		});
		btnManage.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnManage.setBounds(578, 209, 175, 47);
		contentPane.add(btnManage);

		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
	}
}
