import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Notepad extends JPanel {
	public Notepad() {
		JTextArea j = new JTextArea(10, 40);
		add(j);
	}
}
