package Buissnes;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI {
	
	protected JLabel Status;
	private JFrame frame;
	private String hash;
	protected JLabel Result;

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
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
			    Object[] test = new Object[16]; 
			    test[0] = "George".toString().toLowerCase();
			    test[1] = "Zaimis".toString().toLowerCase();
			    Hash hashing = new Hash(Arrays.toString(test));
			    hash = hashing.getHashCode();
			    if(Post("GetSearch", hashing.getHashCode(), "1").matches("Valid")) {
			    	
			    	Status.setText("Valid");
			    	Status.setForeground(Color.GREEN);
			    }
			  }
			}, 2*1000);
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
		
		Status = new JLabel("Please tap the card to the scanner");
		frame.getContentPane().add(Status, "cell 1 0 2 1");
		
		final JComboBox VerOpt = new JComboBox();
		VerOpt.setModel(new DefaultComboBoxModel(new String[] {"Please select verification query", "Is user older than 18?", "Give me IBAN", "Is having valid driving license?", "Is income below 5.000\u20AC?", "Does own property ... ?", "Is a 3 years old driver?"}));
		frame.getContentPane().add(VerOpt, "cell 0 1 3 1,growx");
		
		JButton Ver = new JButton("Verify");
		Ver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(VerOpt.getSelectedIndex() !=0) {
					if(PostUser("1", hash, Integer.toString(VerOpt.getSelectedIndex())).matches("Valid")){
						Result.setText("Valid information");
						Result.setForeground(Color.GREEN);
					}else {
						Result.setText("Not Valid Information");
						Result.setForeground(Color.RED);
					}
				}
				
			}
		});
		frame.getContentPane().add(Ver, "cell 1 3,alignx center");
		
		Result = new JLabel("Watting for the data");
		frame.getContentPane().add(Result, "cell 1 4,alignx center");
	}
	
	private String Post(String dom, String hash, String pos) {
		
		URL u;
      InputStream is = null;
      DataInputStream dis;
      String s = null;
	  String res = "";
      try {
          u = new URL("http://localhost:9000/" + dom +"?pos=" + pos + "&hash=" + hash);
          is = u.openStream();        
          dis = new DataInputStream(new BufferedInputStream(is));
          while ((s = dis.readLine()) != null) {
        	  res = s;
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
      return res;

}
	
private String PostUser(String pos, String hash, String dom) {
		
		URL u;
      InputStream is = null;
      DataInputStream dis;
      String s = null;
	  String res = "";
      try {
          u = new URL("http://localhost:9000/GetSearchUser?pos=" + pos + "&hash=" + hash + "&queryID=" + dom);
          is = u.openStream();        
          dis = new DataInputStream(new BufferedInputStream(is));
          while ((s = dis.readLine()) != null) {
        	  res = s;
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
      return res;

}

}
