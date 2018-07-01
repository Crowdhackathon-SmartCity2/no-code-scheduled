package Buissnes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class UI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Buissnes");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][grow][]", "[][][][][]"));
		
		JLabel lblStatus = new JLabel("Status:");
		frame.getContentPane().add(lblStatus, "cell 0 0");
		
		JLabel Status = new JLabel("Please tap the card to the scanner");
		frame.getContentPane().add(Status, "cell 1 0 2 1");
		
		JComboBox VerOpt = new JComboBox();
		VerOpt.setModel(new DefaultComboBoxModel(new String[] {"Please select verification query", "Is user older than 18?", "Give me IBAN", "Is having valid driving license?", "Does own property ... ?", "Is a 3 years old driver?"}));
		frame.getContentPane().add(VerOpt, "cell 0 1 3 1,growx");
		
		JButton Ver = new JButton("Verify");
		frame.getContentPane().add(Ver, "cell 1 3,alignx center");
		
		JLabel Result = new JLabel("Watting for the data");
		frame.getContentPane().add(Result, "cell 1 4,alignx center");
	}

}
