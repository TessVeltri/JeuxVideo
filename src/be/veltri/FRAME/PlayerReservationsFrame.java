package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PlayerReservationsFrame extends JFrame {
	private static final long serialVersionUID = -4293107333965157865L;
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
	public PlayerReservationsFrame(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMyReservations = new JLabel("My reservations");
		lblMyReservations.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyReservations.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblMyReservations.setBounds(236, 11, 314, 79);
		contentPane.add(lblMyReservations);
		
		JScrollPane reservScrollPane = new JScrollPane();
		reservScrollPane.setBounds(55, 101, 495, 261);
		contentPane.add(reservScrollPane);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Reservation date", "Nbr weeks", "Status", "Game name", "Console", "Version" }));
		reservScrollPane.setViewportView(table);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ArrayList<Reservation> lstReserv = Reservation.getAll(player.getUsername(), "", "");
		for (Reservation r : lstReserv) {
			Object[] row = new Object[] { r.getDateReservation(), r.getNbrWeeks(), r.getStatusReservation(), 
					r.getGame().getNameGame(), r.getGame().getNameConsole(), r.getGame().getNameVersion()};
			model.addRow(row);
		}
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "No row selected, select one to delete it");
				} else {
					String status = model.getValueAt(index, 2).toString();
					if (status.equals(ReservationStatus.InProgress.toString())) {
						String date = model.getValueAt(index, 0).toString();
						LocalDate resDate = LocalDate.parse(date);
						String nbrWeeks = model.getValueAt(index, 1).toString();
						String gameName = model.getValueAt(index, 3).toString();
						String console = model.getValueAt(index, 4).toString();
						String version = model.getValueAt(index, 5).toString();
						Game game = new Game(gameName, 0, console, version);
						Reservation res = new Reservation (resDate, ReservationStatus.Cancelled.toString(), Integer.parseInt(nbrWeeks), player,game);
						boolean deleteRes = res.update();
						if (deleteRes) {
							JOptionPane.showMessageDialog(null, "You delete your reservation");
							AccountPlayerFrame frame = new AccountPlayerFrame(player);
							frame.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "An error has occurred");
						}
					}
				}
				
			}
		});
		btnCancel.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnCancel.setBounds(574, 202, 202, 47);
		contentPane.add(btnCancel);
		
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
		btnBack.setBounds(29, 387, 50, 47);
		contentPane.add(btnBack);

		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
	}

}
