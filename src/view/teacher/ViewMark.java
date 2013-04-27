package view.teacher;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewMark extends JFrame{
	private String  titleMark[] = {"Etudiant", "Note"};
	private JPanel bot = new JPanel();
	private JTable markTab;
	ViewMark(Object[][] data){
		this.setTitle("Notes");
		this.setSize(500, 550);
		this.setLocationRelativeTo(null);
				
		markTab = new JTable(data, titleMark){
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		bot.add(new JScrollPane(markTab));
		
		this.setContentPane(bot);
		this.setVisible(true);
	}
}
