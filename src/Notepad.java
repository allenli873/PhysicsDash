import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Notepad extends JPanel {
	public Notepad() {
		setSize(480, 140);
		setLayout(new BorderLayout());
		JTextArea j = new JTextArea(10, 40);
		add(j);
	}
}
