package view;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ViewAdmin extends JFrame {

	public ViewAdmin() {
		this.setTitle("Interface étudiant");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JPanel top = new JPanel();
		JPanel bot = new JPanel();

		JLabel test = new JLabel("SUPERLOL");
		JLabel test2 = new JLabel("Pasuper");
		top.add(test);
		bot.add(test2);
		JTabbedPane tab = new JTabbedPane();
		JComponent panel2 = null;
		tab.addTab("Notes", bot);
		tab.addTab("Planifier le cursus", top);
				
		this.setContentPane(tab);
		this.setVisible(true);
	
	}
}
