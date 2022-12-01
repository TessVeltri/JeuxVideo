package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.veltri.POJO.Admin;
import be.veltri.POJO.Copy;
import be.veltri.POJO.Message;
import be.veltri.POJO.Player;

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
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AdminMessagesFrame extends JFrame {
	private static final long serialVersionUID = 714894523506473874L;
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
	public AdminMessagesFrame(Admin admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMessages = new JLabel("Messages");
		lblMessages.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessages.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblMessages.setBounds(253, 11, 279, 79);
		contentPane.add(lblMessages);
		
		JScrollPane messagesScrollPane = new JScrollPane();
		messagesScrollPane.setBounds(55, 101, 495, 261);
		contentPane.add(messagesScrollPane);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Message", "From" }));
		messagesScrollPane.setViewportView(table);

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ArrayList<Message> lstMessages = admin.getLstMessage();
		for (Message m : lstMessages) {
			if (!m.isRead()) {
				Object[] row = new Object[] { m.getTextMessage(), m.getSender().getUsername() };
				model.addRow(row);
			}
		}
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "No row selected, select one to see it");
				} else {
					String txt = model.getValueAt(index, 0).toString();
					String from = model.getValueAt(index, 1).toString();
					JOptionPane.showMessageDialog(null, "<html><body><p style='width: 200px;'> FROM : " + from
							+ "</p><p style='width: 200px;'>" + txt + "</p></body></html>");
				}
			}
		});
		btnOpen.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnOpen.setBounds(574, 165, 202, 47);
		contentPane.add(btnOpen);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "No row selected, select one to delete it");
				} else {
					int response = JOptionPane.showConfirmDialog(null, "Do you want to delete the message ? ");
					if (response == JOptionPane.YES_OPTION) {
						String txt = model.getValueAt(index, 0).toString();
						String from = model.getValueAt(index, 1).toString();
						Player tmp = new Player();
						tmp.setUsername(from);
						Player sender = tmp.find();
						sender.setLstCopy(Copy.getAll(sender, null));
						Message msg = new Message(txt, false, sender, admin);
						boolean updateMsg = msg.update();
						if (updateMsg) {
							JOptionPane.showMessageDialog(null, "You delete the row");
							AdminMessagesFrame frame = new AdminMessagesFrame(admin);
							frame.setVisible(true);
							dispose();
						}
					}
				}
			}
		});
		btnDelete.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnDelete.setBounds(574, 233, 202, 47);
		contentPane.add(btnDelete);
		
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
		btnBack.setBounds(32, 389, 50, 47);
		contentPane.add(btnBack);
		
		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
	}

}
