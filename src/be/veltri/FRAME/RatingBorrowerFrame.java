package be.veltri.FRAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.veltri.POJO.Location;
import be.veltri.POJO.Player;
import be.veltri.POJO.Rating;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RatingBorrowerFrame extends JFrame {
	private static final long serialVersionUID = 127470160053970723L;
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
	public RatingBorrowerFrame(Location location) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnStar1 = new JButton("");
		JButton btnStar2 = new JButton("");
		JButton btnStar3 = new JButton("");
		JButton btnStar4 = new JButton("");
		JButton btnStar5 = new JButton("");
		Image imgStar = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/star.png")).getImage();
		Image imgStarFull = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/starFull.png")).getImage();
		Rating rate = new Rating(0, location.getBorrower());

		JLabel lblRatingTheBorrower = new JLabel("Rating the borrower");
		lblRatingTheBorrower.setHorizontalAlignment(SwingConstants.CENTER);
		lblRatingTheBorrower.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblRatingTheBorrower.setBounds(192, 70, 398, 79);
		contentPane.add(lblRatingTheBorrower);

		JLabel lblDoYouWant = new JLabel();
		lblDoYouWant.setText("Do you want to rate the borrower ?");
		lblDoYouWant.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoYouWant.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblDoYouWant.setBounds(81, 152, 633, 44);
		contentPane.add(lblDoYouWant);

		JLabel lblRateborrowerFor = new JLabel();
		lblRateborrowerFor.setText(
				"Rate " + location.getBorrower().getPseudo() + " for " + location.getCopy().getGame().getNameGame());
		lblRateborrowerFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblRateborrowerFor.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblRateborrowerFor.setBounds(76, 209, 633, 44);
		contentPane.add(lblRateborrowerFor);

		JButton btnNo = new JButton("");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player player = location.getOwner().find();
				AccountPlayerFrame frame = new AccountPlayerFrame(player);
				frame.setVisible(true);
				dispose();
			}
		});
		Image imgCross = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/cross.png")).getImage();
		btnNo.setIcon(new ImageIcon(imgCross));
		btnNo.setOpaque(false);
		btnNo.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnNo.setContentAreaFilled(false);
		btnNo.setBorderPainted(false);
		btnNo.setBounds(51, 384, 50, 47);
		contentPane.add(btnNo);

		btnStar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStar1.setIcon(new ImageIcon(imgStarFull));
				btnStar2.setIcon(new ImageIcon(imgStar));
				btnStar3.setIcon(new ImageIcon(imgStar));
				btnStar4.setIcon(new ImageIcon(imgStar));
				btnStar5.setIcon(new ImageIcon(imgStar));
				rate.setRating(1);
			}
		});
		btnStar1.setIcon(new ImageIcon(imgStar));
		btnStar1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStar1.setIcon(new ImageIcon(imgStarFull));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (rate.getRating() == 0)
					btnStar1.setIcon(new ImageIcon(imgStar));
			}
		});
		btnStar1.setOpaque(false);
		btnStar1.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnStar1.setContentAreaFilled(false);
		btnStar1.setBorderPainted(false);
		btnStar1.setBounds(248, 281, 50, 47);
		contentPane.add(btnStar1);

		btnStar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStar1.setIcon(new ImageIcon(imgStarFull));
				btnStar2.setIcon(new ImageIcon(imgStarFull));
				btnStar3.setIcon(new ImageIcon(imgStar));
				btnStar4.setIcon(new ImageIcon(imgStar));
				btnStar5.setIcon(new ImageIcon(imgStar));
				rate.setRating(2);
			}
		});
		btnStar2.setIcon(new ImageIcon(imgStar));
		btnStar2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStar1.setIcon(new ImageIcon(imgStarFull));
				btnStar2.setIcon(new ImageIcon(imgStarFull));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				switch (rate.getRating()) {
				case 0:
					btnStar1.setIcon(new ImageIcon(imgStar));
					btnStar2.setIcon(new ImageIcon(imgStar));
					break;
				case 1:
					btnStar2.setIcon(new ImageIcon(imgStar));
					break;
				}
			}
		});
		btnStar2.setOpaque(false);
		btnStar2.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnStar2.setContentAreaFilled(false);
		btnStar2.setBorderPainted(false);
		btnStar2.setBounds(308, 281, 50, 47);
		contentPane.add(btnStar2);

		btnStar3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStar1.setIcon(new ImageIcon(imgStarFull));
				btnStar2.setIcon(new ImageIcon(imgStarFull));
				btnStar3.setIcon(new ImageIcon(imgStarFull));
				btnStar4.setIcon(new ImageIcon(imgStar));
				btnStar5.setIcon(new ImageIcon(imgStar));
				rate.setRating(3);
			}
		});
		btnStar3.setIcon(new ImageIcon(imgStar));
		btnStar3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStar1.setIcon(new ImageIcon(imgStarFull));
				btnStar2.setIcon(new ImageIcon(imgStarFull));
				btnStar3.setIcon(new ImageIcon(imgStarFull));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				switch (rate.getRating()) {
				case 0:
					btnStar1.setIcon(new ImageIcon(imgStar));
					btnStar2.setIcon(new ImageIcon(imgStar));
					btnStar3.setIcon(new ImageIcon(imgStar));
					break;
				case 1:
					btnStar2.setIcon(new ImageIcon(imgStar));
					btnStar3.setIcon(new ImageIcon(imgStar));
					break;
				case 2:
					btnStar3.setIcon(new ImageIcon(imgStar));
					break;
				}
			}
		});
		btnStar3.setOpaque(false);
		btnStar3.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnStar3.setContentAreaFilled(false);
		btnStar3.setBorderPainted(false);
		btnStar3.setBounds(368, 281, 50, 47);
		contentPane.add(btnStar3);

		btnStar4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStar1.setIcon(new ImageIcon(imgStarFull));
				btnStar2.setIcon(new ImageIcon(imgStarFull));
				btnStar3.setIcon(new ImageIcon(imgStarFull));
				btnStar4.setIcon(new ImageIcon(imgStarFull));
				btnStar5.setIcon(new ImageIcon(imgStar));
				rate.setRating(4);
			}
		});
		btnStar4.setIcon(new ImageIcon(imgStar));
		btnStar4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStar1.setIcon(new ImageIcon(imgStarFull));
				btnStar2.setIcon(new ImageIcon(imgStarFull));
				btnStar3.setIcon(new ImageIcon(imgStarFull));
				btnStar4.setIcon(new ImageIcon(imgStarFull));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				switch (rate.getRating()) {
				case 0:
					btnStar1.setIcon(new ImageIcon(imgStar));
					btnStar2.setIcon(new ImageIcon(imgStar));
					btnStar3.setIcon(new ImageIcon(imgStar));
					btnStar4.setIcon(new ImageIcon(imgStar));
					break;
				case 1:
					btnStar2.setIcon(new ImageIcon(imgStar));
					btnStar3.setIcon(new ImageIcon(imgStar));
					btnStar4.setIcon(new ImageIcon(imgStar));
					break;
				case 2:
					btnStar3.setIcon(new ImageIcon(imgStar));
					btnStar4.setIcon(new ImageIcon(imgStar));
					break;
				case 3:
					btnStar4.setIcon(new ImageIcon(imgStar));
					break;
				}
			}
		});
		btnStar4.setOpaque(false);
		btnStar4.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnStar4.setContentAreaFilled(false);
		btnStar4.setBorderPainted(false);
		btnStar4.setBounds(428, 281, 50, 47);
		contentPane.add(btnStar4);

		btnStar5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStar1.setIcon(new ImageIcon(imgStarFull));
				btnStar2.setIcon(new ImageIcon(imgStarFull));
				btnStar3.setIcon(new ImageIcon(imgStarFull));
				btnStar4.setIcon(new ImageIcon(imgStarFull));
				btnStar5.setIcon(new ImageIcon(imgStarFull));
				rate.setRating(5);
			}
		});
		btnStar5.setIcon(new ImageIcon(imgStar));
		btnStar5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStar1.setIcon(new ImageIcon(imgStarFull));
				btnStar2.setIcon(new ImageIcon(imgStarFull));
				btnStar3.setIcon(new ImageIcon(imgStarFull));
				btnStar4.setIcon(new ImageIcon(imgStarFull));
				btnStar5.setIcon(new ImageIcon(imgStarFull));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				switch (rate.getRating()) {
				case 0:
					btnStar1.setIcon(new ImageIcon(imgStar));
					btnStar2.setIcon(new ImageIcon(imgStar));
					btnStar3.setIcon(new ImageIcon(imgStar));
					btnStar4.setIcon(new ImageIcon(imgStar));
					btnStar5.setIcon(new ImageIcon(imgStar));
					break;
				case 1:
					btnStar2.setIcon(new ImageIcon(imgStar));
					btnStar3.setIcon(new ImageIcon(imgStar));
					btnStar4.setIcon(new ImageIcon(imgStar));
					btnStar5.setIcon(new ImageIcon(imgStar));
					break;
				case 2:
					btnStar3.setIcon(new ImageIcon(imgStar));
					btnStar4.setIcon(new ImageIcon(imgStar));
					btnStar5.setIcon(new ImageIcon(imgStar));
					break;
				case 3:
					btnStar4.setIcon(new ImageIcon(imgStar));
					btnStar5.setIcon(new ImageIcon(imgStar));
					break;
				case 4:
					btnStar5.setIcon(new ImageIcon(imgStar));
					break;
				}
			}
		});
		btnStar5.setOpaque(false);
		btnStar5.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnStar5.setContentAreaFilled(false);
		btnStar5.setBorderPainted(false);
		btnStar5.setBounds(488, 281, 50, 47);
		contentPane.add(btnStar5);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean createRating = rate.create();
				if (createRating) {
					JOptionPane.showMessageDialog(null, "You add your rate for " + location.getBorrower().getPseudo());
					Player player = location.getOwner().find();
					AccountPlayerFrame frame = new AccountPlayerFrame(player);
					frame.setVisible(true);
					dispose();
				}
			}
		});
		btnSend.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnSend.setBounds(574, 384, 202, 47);
		contentPane.add(btnSend);

		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/be/veltri/IMG/background.jpg")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setBounds(0, 0, 790, 470);
		getContentPane().add(image);
	}
}
