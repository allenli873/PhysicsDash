import javax.swing.JFrame;
//file to be run
public class PhysicsDash 
{
	///testing123
	public PhysicsDash()
	{
		JFrame frame = new JFrame();
		frame.setSize(960, 540);
		frame.getContentPane().add(new CreditsAndStats());
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		PhysicsDash p = new PhysicsDash();
	}
}
