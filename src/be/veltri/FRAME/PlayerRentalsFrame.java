package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.veltri.POJO.Location;
import be.veltri.POJO.Player;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PlayerRentalsFrame extends JFrame {
	private static final long serialVersionUID = -4025779110229226555L;
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
	public PlayerRentalsFrame(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMyRentals = new JLabel("My rentals");
		lblMyRentals.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyRentals.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblMyRentals.setBounds(253, 11, 279, 79);
		contentPane.add(lblMyRentals);
		
		JScrollPane rentalsScrollPane = new JScrollPane();
		rentalsScrollPane.setBounds(145, 101, 495, 261);
		contentPane.add(rentalsScrollPane);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Begin date", "End date", "Game name", "Units", "Console", "Version" }));
		rentalsScrollPane.setViewportView(table);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ArrayList<Location> lstLocation = player.getLstLocationBorrower();
		for (Location l : lstLocation) {
			Object[] row = new Object[] { l.getDateBeginLocation(), l.getDateEndLocation(), l.getCopy().getGame().getNameGame(), 
					l.getTotalUnits(), l.getCopy().getGame().getNameConsole(), 
					l.getCopy().getGame().getNameVersion()};
			model.addRow(row);
		}
		
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
		btnBack.setBounds(34, 382, 50, 47);
		contentPane.add(btnBack);

		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
	}
}
