package View.GUI.proxy;

import javafx.beans.property.SimpleObjectProperty;

import java.util.TimerTask;

import javax.swing.JFrame;
import Controller.Management.*;

public class RealImage implements IImage {
		String args[];
	   private JFrame a;
	   
	   public RealImage(JFrame a){
		   this.a = a;
	   }

	   @Override
	   public void display() {
		    loadFromDisk();
	   }

	   private void loadFromDisk(){
			java.util.Timer timer = new java.util.Timer();
			timer.schedule(task.get(), 2000, 5000);
		}

	public SimpleObjectProperty<TimerTask> task = new SimpleObjectProperty<>(this, "task", new TimerTask() {
		@Override
		public void run() {
			a.dispose();
			//new Main();
			new Test().main(args);
		}
	});
	   
	}