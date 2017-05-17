import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class HelpFrame extends JFrame {
	private PhysicsDash app;
	private HelpPanel hp;
	public HelpFrame(PhysicsDash p) {
		super("Help");
		app = p;
		setBounds(300, 300, 600, 700);
		setLayout(null);
		hp = new HelpPanel(app);
		hp.setBounds(0, 0, 600, 700);
		addWindowListener(new WindowResponse());
		add(hp);
	}
	class WindowResponse extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			hp.nextHint();
		}
	}
}
