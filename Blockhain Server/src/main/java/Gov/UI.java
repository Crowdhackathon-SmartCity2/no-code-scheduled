package Gov;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.annotation.PostConstruct;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class UI {

	private JFrame frame;
	protected JTextField lName;
	protected JTextField Height;
	protected JTextField fName;
	protected JTextField Father;
	protected JTextField Mother;
	protected JTextField Day;
	protected JTextField Month;
	protected JTextField Year;
	protected JComboBox Race;
	protected JComboBox eyeColor;
	private JLabel lblAmka;
	private JTextField AMKA;
	private JButton Requests;

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
		frame = new JFrame("Gov Application");
		frame.setBounds(100, 100, 770, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][grow][][grow][grow][grow]", "[][][][][][][][][]"));
		
		JLabel lblFirstName = new JLabel("First Name");
		frame.getContentPane().add(lblFirstName, "cell 0 0,alignx trailing");
		
		fName = new JTextField();
		frame.getContentPane().add(fName, "cell 1 0,growx");
		fName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		frame.getContentPane().add(lblLastName, "cell 2 0,alignx trailing");
		
		lName = new JTextField();
		frame.getContentPane().add(lName, "cell 3 0 3 1,growx");
		lName.setColumns(10);
		
		JLabel lblHeight = new JLabel("Height(cm)");
		frame.getContentPane().add(lblHeight, "cell 0 1,alignx trailing");
		
		Height = new JTextField();
		frame.getContentPane().add(Height, "cell 1 1,growx");
		Height.setColumns(10);
		
		JLabel lblRace = new JLabel("Race");
		frame.getContentPane().add(lblRace, "cell 2 1,alignx trailing");
		
		Race = new JComboBox();
		Race.setModel(new DefaultComboBoxModel(new String[] {"Please Select", "Caucasion", "Mongoloid", "Negroid"}));
		frame.getContentPane().add(Race, "cell 3 1 3 1,growx");
		
		JLabel lblEyeColor = new JLabel("Eye Color");
		frame.getContentPane().add(lblEyeColor, "cell 0 2,alignx trailing");
		
		eyeColor = new JComboBox();
		eyeColor.setModel(new DefaultComboBoxModel(new String[] {"Please Select", "Brown", "Hazel", "Blue", "Green", "Silver", "Amber"}));
		frame.getContentPane().add(eyeColor, "cell 1 2,growx");
		
		JLabel lblNewLabel = new JLabel("Date");
		frame.getContentPane().add(lblNewLabel, "cell 2 2,alignx trailing");
		
		Day = new JTextField();
		frame.getContentPane().add(Day, "cell 3 2,growx");
		Day.setColumns(10);
		
		Month = new JTextField();
		frame.getContentPane().add(Month, "cell 4 2,growx");
		Month.setColumns(10);
		
		Year = new JTextField();
		frame.getContentPane().add(Year, "cell 5 2,growx");
		Year.setColumns(10);
		
		JLabel lblFather = new JLabel("Father");
		frame.getContentPane().add(lblFather, "cell 0 3,alignx trailing");
		
		Father = new JTextField();
		frame.getContentPane().add(Father, "cell 1 3,growx");
		Father.setColumns(10);
		
		JLabel lblMother = new JLabel("Mother");
		frame.getContentPane().add(lblMother, "cell 2 3,alignx trailing");
		
		Mother = new JTextField();
		frame.getContentPane().add(Mother, "cell 3 3 3 1,growx");
		Mother.setColumns(10);
		
		lblAmka = new JLabel("AMKA");
		frame.getContentPane().add(lblAmka, "cell 0 4,alignx trailing");
		
		AMKA = new JTextField();
		frame.getContentPane().add(AMKA, "cell 1 4,growx");
		AMKA.setColumns(10);
		
		Requests = new JButton("Requests");
		Requests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Requests req = new Requests();
				req.RunMe();
			}
		});
		
		JButton btnValidateData = new JButton("Validate Data");
		btnValidateData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object[] test = MakeObj();
				Hash hashing = new Hash(Arrays.toString(test));
				Post("GetSearch", hashing.getHashCode(), "3");
				
			}
		});
		
		JButton btnNewButton = new JButton("Post Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] test = MakeObj();
				Hash hashing = new Hash(Arrays.toString(test));
				Post("GetGiveData", hashing.getHashCode(), "");
			}
		});
		frame.getContentPane().add(btnNewButton, "cell 1 6,alignx center");
		frame.getContentPane().add(btnValidateData, "cell 5 6,alignx center");
		frame.getContentPane().add(Requests, "cell 1 7 5 1,alignx center");
		
		
	}
	
	private String Post(String dom, String hash, String pos) {
		
			URL u;
	      InputStream is = null;
	      DataInputStream dis;
	      String s = null;
	      
	      try {
	    	  System.out.println(hash);
	          u = new URL("http://localhost:9000/" + dom +"?pos=" + pos + "&hash=" + hash);
	          is = u.openStream();        
	          dis = new DataInputStream(new BufferedInputStream(is));
	          while ((s = dis.readLine()) != null) {
	             System.out.println(s);
	          }

	       } catch (MalformedURLException mue) {

	          System.out.println("Ouch - a MalformedURLException happened.");
	          mue.printStackTrace();
	          System.exit(1);

	       } catch (IOException ioe) {

	          System.out.println("Oops- an IOException happened.");
	          ioe.printStackTrace();
	          System.exit(1);

	       } finally {

	          try {
	             is.close();
	          } catch (IOException ioe) {
	          }

	       }
	      return s;

	}
	
	private Object[] MakeObj() {
		Object[] temp = new Object[16];
		if(fName.getText() != null && !fName.getText().trim().isEmpty()) {
			temp[0] = fName.getText().toString().toLowerCase();
		}
		if(lName.getText() != null && !lName.getText().trim().isEmpty()) {
			temp[1] = lName.getText().toString().toLowerCase();
		}
		if(Height.getText() != null && !Height.getText().trim().isEmpty()) {
			temp[2] = Height.getText().toString().toLowerCase();
		}
		if(Father.getText() != null && !Father.getText().trim().isEmpty()) {
			temp[5] = Father.getText().toString().toLowerCase();
		}
		if(Mother.getText() != null && !Mother.getText().trim().isEmpty()) {
			temp[6] = Mother.getText().toString().toLowerCase();
		}
		if(Day.getText() != null && !Day.getText().trim().isEmpty() && Month.getText() != null && !Month.getText().trim().isEmpty() && Year.getText() != null && !Year.getText().trim().isEmpty()) {
			String d = Day.getText().toString().toLowerCase();
			String m = Month.getText().toString().toLowerCase();
			String y = Year.getText().toString().toLowerCase();
			temp[7] = d + "/" + m + "/" + y;
		}
		if(AMKA.getText() != null && !AMKA.getText().trim().isEmpty()) {
			temp[8] = AMKA.getText().toString().toLowerCase();
		}
		if(Race.getSelectedIndex() != 0) {
			temp[3] = Race.getSelectedItem().toString();
		}
		if(eyeColor.getSelectedIndex() != 0) {
			temp[4] = eyeColor.getSelectedItem().toString();
		}
		return temp;
	}
	

}
