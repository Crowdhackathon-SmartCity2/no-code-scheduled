package Gov;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;

public class Requests {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Requests window = new Requests();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	void RunMe() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Requests window = new Requests();
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
	public Requests() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Requests");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow][]", "[][grow][]"));
		
		JButton Refresh = new JButton("Refresh");
		Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int row = model.getRowCount();
				for(int i=row-1;i>=0;i--) {
					model.removeRow(i);
				}
				try {
					tableRefresh(table);
				} catch (NumberFormatException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(Refresh, "cell 1 0");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Type", "Date", "License Code", "User"
			}
		));
		try {
			tableRefresh(table);
		} catch (NumberFormatException | IOException e1) {
			e1.printStackTrace();
		}
		frame.getContentPane().add(table, "cell 0 1 2 1,grow");
		
		JButton Reject = new JButton("Reject");
		Reject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if(row != -1) {
					model.removeRow(row);
					PrintWriter writer = null;
					try {
						writer = new PrintWriter("response.txt", "UTF-8");
					} catch (FileNotFoundException | UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
					DefaultTableModel model2 = (DefaultTableModel) table.getModel();
					writer.println(table.getRowCount());
					for(int i=0;i<table.getRowCount();i++) {
						for(int z=0;z<4;z++) {
							writer.println(model2.getValueAt(i, z));
						}
					}
			        writer.close();
				}
			}
		});
		frame.getContentPane().add(Reject, "cell 0 2");
		
		JButton Accept = new JButton("Accept");
		Accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if(row != -1) {
					
					
					File f = new File("Users/", model.getValueAt(row, 3) + ".txt");
					System.out.println(f);
					if(f.exists() && !f.isDirectory()) {
						BufferedReader br = null;
						int count = 0;
						int maxart = 0;
						try {
							br = new BufferedReader(new FileReader(f));
							count = (Integer) Integer.parseInt(br.readLine());
							maxart = (Integer) Integer.parseInt(br.readLine());
						} catch (FileNotFoundException e2) {
							e2.printStackTrace();
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						String[][] Arr = new String[count][maxart];
						for(int i=0;i<count;i++) {
							int art = 0;
							try {
								art = (Integer) Integer.parseInt(br.readLine());
							} catch (NumberFormatException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							Arr[i][0] = Integer.toString(art);
							for(int z=1;z<art;z++) {
								try {
									Arr[i][z] = br.readLine();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
						
						
						
						PrintWriter writer = null;
						try {
							writer = new PrintWriter(f);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						
						if(table.getColumnCount()>maxart) {
							maxart = table.getColumnCount();
						}
						int col = 0;
						
						writer.println(count+1);
						writer.println(maxart);
						if(maxart != 0) {
							for(int i=0;i<count;i++) {
								if(count != 0) {
									col = Integer.parseInt(Arr[i][0]);
								}else {
									col = 0;
								}
								for(int z=0;z<col;z++) {
									writer.println(Arr[i][z]);
								}
							}
						}
						writer.println("4");
						String tt = model.getValueAt(table.getSelectedRow(), 2).toString();
						if(tt.matches(" ")){	
							writer.println("Income");
						}else{
							writer.println("Car");
						}
						writer.println(table.getValueAt(row, 0));
						writer.println(table.getValueAt(row, 1));
						writer.println(table.getValueAt(row, 2));
						writer.close();

						
						
						model.removeRow(row);
						
						try {
							writer = new PrintWriter("response.txt", "UTF-8");
						} catch (FileNotFoundException | UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
						DefaultTableModel model2 = (DefaultTableModel) table.getModel();
						writer.println(table.getRowCount());
						for(int i=0;i<table.getRowCount();i++) {
							for(int z=0;z<4;z++) {
								writer.println(model2.getValueAt(i, z));
							}
						}
				        writer.close();
					}else {
						System.out.println("File Does Not Exist");
					}
				}
			}
		});
		frame.getContentPane().add(Accept, "cell 1 2");
	}
	
	private void tableRefresh(JTable table) throws NumberFormatException, IOException {

		File f = new File("response.txt");
		if(f.exists() && !f.isDirectory()) { 
			 
			BufferedReader br = new BufferedReader(new FileReader(f));
			int count = Integer.parseInt(br.readLine());
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for(int i=0;i<count;i++) {
				model.addRow(new Object[] {br.readLine(), br.readLine(), br.readLine(), br.readLine()});
			}
			br.close();
		}
		
	}

}

