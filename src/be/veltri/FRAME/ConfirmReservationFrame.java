package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.veltri.POJO.Game;
import be.veltri.POJO.Player;
import javax.swing.JLabel;

public class ConfirmReservationFrame extends JFrame {

	private static final long serialVersionUID = -720606444795905402L;
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
	public ConfirmReservationFrame(Game game, Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("reservation");
		lblNewLabel.setBounds(294, 210, 49, 14);
		contentPane.add(lblNewLabel);
	}

}
