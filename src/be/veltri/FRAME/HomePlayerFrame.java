package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.veltri.POJO.Player;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class HomePlayerFrame extends JFrame {

	private static final long serialVersionUID = 8307154674432024040L;
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
	public HomePlayerFrame(Player player) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBalance = new JLabel();
		lblBalance.setText(player.getBalance() + " units");
		lblBalance.setHorizontalAlignment(SwingConstants.LEFT);
		lblBalance.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblBalance.setBounds(35, 28, 135, 38);
		contentPane.add(lblBalance);
		
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
		
		JButton btnAccount = new JButton("");
		btnAccount.setOpaque(false);
		btnAccount.setContentAreaFilled(false);
		btnAccount.setBorderPainted(false);
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountPlayerFrame frame = new AccountPlayerFrame(player);
				frame.setVisible(true);
				dispose();
			}
		});
		Image imgAccount = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/account.png")).getImage();
		btnAccount.setIcon(new ImageIcon(imgAccount));
		btnAccount.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnAccount.setBounds(666, 19, 50, 47);
		contentPane.add(btnAccount);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblWelcome.setBounds(253, 23, 279, 79);
		contentPane.add(lblWelcome);
		
		JButton btnPut = new JButton("Put a new game on rent");
		btnPut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGameFrame frame = new AddGameFrame(player);
				frame.setVisible(true);
				dispose();
			}
		});
		btnPut.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnPut.setBounds(214, 157, 357, 67);
		contentPane.add(btnPut);
		
		JButton btnSee = new JButton("See all games for rent");
		if (!player.checkBalance()) {
			btnSee.setEnabled(false);
		}
		btnSee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeAllGamesFrame frame = new SeeAllGamesFrame(player);
				frame.setVisible(true);
				dispose();
			}
		});
		btnSee.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnSee.setBounds(214, 265, 357, 67);
		contentPane.add(btnSee);
		
		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
	}
}
