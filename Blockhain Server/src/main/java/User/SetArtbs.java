package User;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;

public class SetArtbs {

	private JFrame frame;
	private JTextField Year;
	private JTextField Month;
	private JTextField Day;
	private JTextField Number;
	private JTextField Income;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetArtbs window = new SetArtbs();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void RunCar(final int index) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetArtbs window = new SetArtbs(index);
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
	public SetArtbs() {
		initializeIn();
	}
	
	public SetArtbs(int index) {
		if(index == 1) {
			initializeCar();
		}else if (index == 2) {
			initializeIn();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 141);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
	}
	
	private void initializeCar() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][grow][grow][grow]", "[][][][]"));
		
		JLabel lblTypeOfLiensce = new JLabel("Type of license");
		frame.getContentPane().add(lblTypeOfLiensce, "cell 0 0,alignx trailing");
		
		final JComboBox Type = new JComboBox();
		Type.setModel(new DefaultComboBoxModel(new String[] {"Please Select", "AM (M)", "A1", "A2", "A3", "A", "B", "BE", "C", "C1", "CE", "C1E", "D", "D1", "DE", "D1E"}));
		frame.getContentPane().add(Type, "cell 1 0 3 1,growx");
		
		JLabel lblExpireAt = new JLabel("Expire at");
		frame.getContentPane().add(lblExpireAt, "cell 0 1,alignx trailing");
		
		Day = new JTextField();
		frame.getContentPane().add(Day, "cell 1 1,growx");
		Day.setColumns(10);
		
		Month = new JTextField();
		frame.getContentPane().add(Month, "cell 2 1,growx");
		Month.setColumns(10);
		
		Year = new JTextField();
		frame.getContentPane().add(Year, "cell 3 1,growx");
		Year.setColumns(10);
		
		JButton btnNewButton = new JButton("Sumbit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Type.getSelectedIndex() != 0) {
					int p = JOptionPane.showOptionDialog(frame, "Tap the id to the scanner", "Identidy", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, null, 0);
					if(p == JOptionPane.YES_OPTION) {
							String d = Day.getText().toString().toLowerCase();
							String m = Month.getText().toString().toLowerCase();
							String y = Year.getText().toString().toLowerCase();
							String Date = d + "/" + m + "/" + y;
							String No = Number.getText().toString().toLowerCase();
							File responce = new File("response.txt");
							if(responce.exists() && !responce.isDirectory()) { 
								 
								BufferedReader br = null;
								try {
									br = new BufferedReader(new FileReader(responce));
								} catch (FileNotFoundException e1) {
									e1.printStackTrace();
								}
								int count = 0;
								try {
									count = Integer.parseInt(br.readLine());
								} catch (NumberFormatException | IOException e2) {
									e2.printStackTrace();
								}
								String[][] data = new String[count][4];
								for(int i=0;i<count;i++) {
									try {
										data[i][0] = br.readLine();
										data[i][1] = br.readLine();
										data[i][2] = br.readLine();
										data[i][3] = "d407c2e1c75abf778d3ca5edcd9df9d6da04b8628fff80fbbfee4086af3cc01e";
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
								PrintWriter pr = null;
								try {
									pr = new PrintWriter("response.txt");
								} catch (FileNotFoundException e2) {
									e2.printStackTrace();
								}
								pr.println(count+1);
								for(int i=0;i<count;i++) {
									for(int z=0;z<4;z++) {
										pr.println(data[i][z]);
									}
								}
								pr.println(Type.getSelectedItem().toString());
						        pr.println(Date);
						        pr.println(No);
						        pr.println("d407c2e1c75abf778d3ca5edcd9df9d6da04b8628fff80fbbfee4086af3cc01e");
						        pr.close();
								try {
									br.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}else {
								PrintWriter writer = null;
								try {
									writer = new PrintWriter("response.txt", "UTF-8");
								} catch (FileNotFoundException | UnsupportedEncodingException e1) {
									e1.printStackTrace();
								}
								writer.println(1);
						        writer.println(Type.getSelectedItem().toString());
						        writer.println(Date);
						        writer.println(No);
						        writer.println("d407c2e1c75abf778d3ca5edcd9df9d6da04b8628fff80fbbfee4086af3cc01e");
						        writer.close();
						    }
						
							
						}
					}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Licence Number");
		frame.getContentPane().add(lblNewLabel, "cell 0 2,alignx trailing");
		
		Number = new JTextField();
		frame.getContentPane().add(Number, "cell 1 2 3 1,growx");
		Number.setColumns(10);
		frame.getContentPane().add(btnNewButton, "cell 1 3 2 1,alignx center");

	}


	private void initializeIn() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 141);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel lblIncome = new JLabel("Income:");
		frame.getContentPane().add(lblIncome, "cell 0 0,alignx trailing");
		
		Income = new JTextField();
		frame.getContentPane().add(Income, "cell 1 0,growx");
		Income.setColumns(10);
		
		JButton Submit = new JButton("Submit");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!Income.getText().matches("") && Income.getText() != null) {
					int p = JOptionPane.showOptionDialog(frame, "Tap the id to the scanner", "Identidy", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, null, 0);
					if(p == JOptionPane.YES_OPTION) {

							File responce = new File("response.txt");
							if(responce.exists() && !responce.isDirectory()) { 
								 
								BufferedReader br = null;
								try {
									br = new BufferedReader(new FileReader(responce));
								} catch (FileNotFoundException e1) {
									e1.printStackTrace();
								}
								int count = 0;
								try {
									count = Integer.parseInt(br.readLine());
								} catch (NumberFormatException | IOException e2) {
									e2.printStackTrace();
								}
								String[][] data = new String[count][4];
								for(int i=0;i<count;i++) {
									try {
										data[i][0] = br.readLine();
										data[i][1] = br.readLine();
										data[i][2] = br.readLine();
										data[i][3] = "d407c2e1c75abf778d3ca5edcd9df9d6da04b8628fff80fbbfee4086af3cc01e";
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
								PrintWriter pr = null;
								try {
									pr = new PrintWriter("response.txt");
								} catch (FileNotFoundException e2) {
									e2.printStackTrace();
								}
								pr.println(count+1);
								for(int i=0;i<count;i++) {
									for(int z=0;z<4;z++) {
										pr.println(data[i][z]);
									}
								}
								pr.println(Income.getText().toString().toLowerCase());
						        pr.println("98787654332132");
						        pr.println(" ");
						        pr.println("d407c2e1c75abf778d3ca5edcd9df9d6da04b8628fff80fbbfee4086af3cc01e");
						        pr.close();
								try {
									br.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}else {
								PrintWriter writer = null;
								try {
									writer = new PrintWriter("response.txt", "UTF-8");
								} catch (FileNotFoundException | UnsupportedEncodingException e1) {
									e1.printStackTrace();
								}
								writer.println(1);
						        writer.println(Income.getText().toString().toLowerCase());
						        writer.println("98787654332132");
						        writer.println(" ");
						        writer.println("d407c2e1c75abf778d3ca5edcd9df9d6da04b8628fff80fbbfee4086af3cc01e");
						        writer.close();
						    }
						
							
						}
					}
			}
		});
		frame.getContentPane().add(Submit, "cell 0 2 2 1,alignx center");

	}

}