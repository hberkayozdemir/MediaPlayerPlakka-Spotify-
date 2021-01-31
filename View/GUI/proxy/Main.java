package View.GUI.proxy;

import java.awt.*;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main extends JFrame{
	
	private static final long serialVersionUID = 7496915955779509324L;
	public JPanel holder;
	public JLabel label;
	int EkranX, EkranY;
	Toolkit kit = Toolkit.getDefaultToolkit();
	public Main() {
		
		ProxyImage imgz = new ProxyImage("/View/GUI/Images/vinyl.gif",this);
		
		holder = new JPanel(new GridLayout(1,1)); 
		
		Icon imgIcon = imgz.primage();
		label = new JLabel(imgIcon);
		//this.getContentPane().add(label);
		holder.add(label);
		
		imgz.display();
		
		
		
		
		this.add(holder);
		
		setTitle("Plakka");
		setAlwaysOnTop(false);
		setResizable(false);
		setLocationRelativeTo(null);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		java.util.Timer timer = new java.util.Timer();
		timer.schedule(task, 2000, 5000);
		EkranX = (int) kit.getScreenSize().width; //Ekran boyutunun genişliğini alıyoruz...
		EkranY = (int) kit.getScreenSize().height;//Ekran boyutunun yüksekliğini alıyoruz...

		this.setSize(1200, 700); // Pencere boyutunu belirliyoruz...
		this.setLocation((EkranX - 1200) / 2, (EkranY - 700) / 2);
	}
	
	public TimerTask task = new TimerTask() {
		@Override
		public void run() {
			holder.remove(label);
			label = new JLabel(new ImageIcon(Main.class.getResource("/View/GUI/Images/Eazy-E.jpg")));
			holder.add(label);
			
		}
	};
	
	
	public static void main(String[] args) {
		new Main();
	}

}
