package User;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame = new JFrame("End User App");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][]"));
		
		JLabel lblNewLabel = new JLabel("Choose categorie to register.");
		frame.getContentPane().add(lblNewLabel, "cell 0 0,alignx center");
		
		final JComboBox Submit = new JComboBox();
		Submit.setModel(new DefaultComboBoxModel(new String[] {"Please Select", "Driving License", "Education Certification", "Property Registration"}));
		frame.getContentPane().add(Submit, "cell 0 1,growx");
		
		JButton Next = new JButton("Next");
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				if(Submit.getSelectedIndex() != 0) {
					SetArtbs next = new SetArtbs();
					next.RunCar(Submit.getSelectedIndex());
					
				}
				
			}
		});
		frame.getContentPane().add(Next, "cell 0 2,alignx center");
	}
	

}

