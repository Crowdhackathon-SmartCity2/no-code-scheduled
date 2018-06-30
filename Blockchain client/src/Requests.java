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
		frame = new JFrame();
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
				model.removeRow(row);
				PrintWriter writer = null;
				try {
					writer = new PrintWriter("response.txt", "UTF-8");
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				model = (DefaultTableModel) table.getModel();
				writer.println(table.getRowCount());
				System.out.println(table.getRowCount());
				for(int i=0;i<row;i++) {
					System.out.println("in");
					for(int z=0;z<4;z++) {
						writer.println(model.getValueAt(i, z));
					}
				}
		        writer.close();
			}
		});
		frame.getContentPane().add(Reject, "cell 0 2");
		
		JButton Accept = new JButton("Accept");
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
