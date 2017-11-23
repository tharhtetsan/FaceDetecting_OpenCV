package choosePixel;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Example {

	private JFrame frmExample;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Example window = new Example();
					window.frmExample.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Example() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmExample = new JFrame();
		frmExample.setTitle("Example");
		frmExample.setBounds(100, 100, 266, 144);
		frmExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExample.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Draw Black Points");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window.init();
			}
		});
		btnNewButton.setBounds(63, 34, 138, 48);
		frmExample.getContentPane().add(btnNewButton);
	}
}
