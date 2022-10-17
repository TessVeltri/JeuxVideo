package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.veltri.POJO.Player;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
		
		JButton btnValid = new JButton("");
		btnValid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Check si les infos sont choisies et insert 
				// revenir à la frame HomePlayer
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
		
		JComboBox cbGame = new JComboBox();
		cbGame.setModel(new DefaultComboBoxModel(new String[] {"Select a game"}));
		cbGame.setFont(new Font("Stencil", Font.PLAIN, 15));
		cbGame.setBounds(253, 144, 280, 47);
		contentPane.add(cbGame);
		
		JComboBox cbConsole = new JComboBox();
		cbConsole.setModel(new DefaultComboBoxModel(new String[] {"Select a console"}));
		cbConsole.setFont(new Font("Stencil", Font.PLAIN, 15));
		cbConsole.setBounds(253, 202, 280, 47);
		contentPane.add(cbConsole);
		
		JComboBox cbVersion = new JComboBox();
		cbVersion.setModel(new DefaultComboBoxModel(new String[] {"Select a version"}));
		cbVersion.setFont(new Font("Stencil", Font.PLAIN, 15));
		cbVersion.setBounds(253, 260, 280, 47);
		contentPane.add(cbVersion);

		JButton btnAskGame = new JButton("");
		btnAskGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Insert le nom du jeu dans option pane 
				// TODO joptionpane de confirmation "message envoyé aux admins"
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
				// TODO insérer une nouvelle console + message de confirmation de demande
			}
		});
		btnAskConsole.setIcon(new ImageIcon(imgPlus));
		btnAskConsole.setOpaque(false);
		btnAskConsole.setContentAreaFilled(false);
		btnAskConsole.setBorderPainted(false);
		btnAskConsole.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnAskConsole.setBounds(543, 202, 40, 40);
		contentPane.add(btnAskConsole);
		
		
		JButton btnAskVersion = new JButton("");
		btnAskVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO insérer une nouvelle version + message de confirmation de demande
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
