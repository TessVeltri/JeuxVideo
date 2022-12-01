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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AdminManageConsoleVersion extends JFrame {
	private static final long serialVersionUID = -9080637997062286037L;
	private JPanel contentPane;
	private JTextField txtConsole;
	private JTextField txtVersion;

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
	public AdminManageConsoleVersion(Admin admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddNewConsoleversion = new JLabel("Manage console/version");
		lblAddNewConsoleversion.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewConsoleversion.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblAddNewConsoleversion.setBounds(179, 26, 427, 79);
		contentPane.add(lblAddNewConsoleversion);

		ArrayList<String> lstConsoles = Game.getAllName("Console", "");
		lstConsoles.add(0, "Select a console");
		Object[] lstC = lstConsoles.toArray();

		JComboBox cbConsole = new JComboBox(lstC);
		cbConsole.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbConsole.getSelectedItem().toString().equals("Select a console")) {
					txtConsole.setEditable(true);
				} else {
					txtConsole.setText("");
					txtConsole.setEditable(false);
				}
			}
		});
		cbConsole.setModel(new DefaultComboBoxModel(lstC));
		cbConsole.setFont(new Font("Stencil", Font.PLAIN, 15));
		cbConsole.setBounds(359, 267, 169, 22);
		contentPane.add(cbConsole);

		JLabel lblConsole = new JLabel("Select a console :");
		lblConsole.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsole.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblConsole.setBounds(209, 266, 153, 22);
		contentPane.add(lblConsole);

		txtConsole = new JTextField();
		txtConsole.setHorizontalAlignment(SwingConstants.LEFT);
		txtConsole.setForeground(Color.BLACK);
		txtConsole.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtConsole.setColumns(10);
		txtConsole.setBounds(359, 313, 169, 36);
		contentPane.add(txtConsole);

		JLabel lblVersion = new JLabel("New version :");
		lblVersion.setHorizontalAlignment(SwingConstants.LEFT);
		lblVersion.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblVersion.setBounds(209, 360, 140, 36);
		contentPane.add(lblVersion);

		txtVersion = new JTextField();
		txtVersion.setHorizontalAlignment(SwingConstants.LEFT);
		txtVersion.setForeground(Color.BLACK);
		txtVersion.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtVersion.setColumns(10);
		txtVersion.setBounds(359, 360, 169, 36);
		contentPane.add(txtVersion);

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
		btnBack.setBounds(28, 394, 50, 47);
		contentPane.add(btnBack);

		JButton btnYes = new JButton("");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String console;
				String version = txtVersion.getText();
				if (cbConsole.getSelectedItem().toString().equals("Select a console"))
					console = txtConsole.getText();
				else
					console = cbConsole.getSelectedItem().toString();
				if (console.equals("") || version.equals("")) {
					JOptionPane.showMessageDialog(null, "Complete all fields please");
				} else {
					Game game = new Game ("", 0, console, version);
					boolean create = game.create();
					if (create) {
						JOptionPane.showMessageDialog(null, "You add a new console/version");
						HomeAdminFrame frame = new HomeAdminFrame(admin);
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
		btnYes.setBounds(705, 394, 50, 47);
		contentPane.add(btnYes);

		JLabel lblOr = new JLabel("or");
		lblOr.setHorizontalAlignment(SwingConstants.LEFT);
		lblOr.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblOr.setBounds(209, 285, 153, 36);
		contentPane.add(lblOr);

		JLabel lblNewConsole = new JLabel("New console :");
		lblNewConsole.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewConsole.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblNewConsole.setBounds(209, 313, 153, 36);
		contentPane.add(lblNewConsole);

		JScrollPane consoleScrollPane = new JScrollPane();
		consoleScrollPane.setBounds(57, 93, 292, 162);
		contentPane.add(consoleScrollPane);
		JTable tableCons = new JTable();
		tableCons.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Console name" }));
		
		consoleScrollPane.setViewportView(tableCons);
		DefaultTableModel model = (DefaultTableModel) tableCons.getModel();
		ArrayList<String> lstCons = Game.getAllName("Console", "");
		for (String s : lstCons) {
			Object[] row = new Object[] { s };
			model.addRow(row);
		}
		
		JScrollPane versionScrollPane = new JScrollPane();
		versionScrollPane.setBounds(429, 94, 292, 162);
		contentPane.add(versionScrollPane);
		JTable tableVers = new JTable();
		tableVers.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Version name" }));
		
		versionScrollPane.setViewportView(tableVers);
		DefaultTableModel model2 = (DefaultTableModel) tableVers.getModel();
		ArrayList<String> lstVers = Game.getAllName("Version", "");
		for (String s : lstVers) {
			Object[] row = new Object[] { s };
			model2.addRow(row);
		}
		
		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
		
	}
}
