package com.mainiac;/*
* Authors Diloş && Batuş
*
*
* */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import Controller.adaptor_pattern.*;
import Controller.iterator.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.PrototypePattern.Images;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import Controller.adaptor_pattern.*;
import Controller.iterator.*;

public class MusicPlayer extends JFrame{
	private static final long serialVersionUID = 9130109710451091873L;
	private Images img=new Images();
	private JPanel holder;
	
	private JButton previous;
	private JButton next;
	
	private JButton stop;
	private JButton start;
	private JButton pause;
	private JButton resume;
	
	private JComboBox<String> selectMusic;
	private int songindex = 0;
	
	private JPanel panel;
	
	FileInputStream FIS;
	BufferedInputStream BIS;
	
	public Player player;
	public long pauseLocation;
	public long songTotalLength;
	public ArrayList<String> filez;
	public String Song;
	
	public AudioPlayer audioPlayer = new AudioPlayer();
	
	public MusicPlayer(String pet) throws Exception {

		MusicRepository namesRepository = new MusicRepository();
		filez = new ArrayList<String>();
		//ArrayList<String> strings = new ArrayList<String>();

		filez.add(pet);

		for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
			filez.add((String)iter.next());
		}

		/*
		filez = new ArrayList<String>();
		filez.add(pet);
		filez.add("sfx/lose_your_self.mp3");
		filez.add("sfx/bazı_zamanlar.mp3");
		filez.add("sfx/kotu_eleman.mp3");
		filez.add("sfx/yar_agladi_ben_agladim.mp3");
		filez.add("sfx/gokyuzu.mp3");
		*/
		
		holder = new JPanel(new GridLayout(1,8));


		previous = new JButton(new ImageIcon(img.getAbsolutepath()+"next_previous.png"));
		next = new JButton(new ImageIcon(img.getAbsolutepath()+"next.png"));
		start = new JButton(new ImageIcon(img.getAbsolutepath()+"start_button.png"));
		stop = new JButton(new ImageIcon(img.getAbsolutepath()+"stop.png"));
		pause = new JButton(new ImageIcon(img.getAbsolutepath()+"pause.png"));
		resume = new JButton(new ImageIcon(img.getAbsolutepath()+"resum.png"));
		
		
		
		selectMusic = new JComboBox<>();
		//selectMusic.addItem(pet);
		for (int j = 0; j < filez.size(); j++) {
			String[] arrOfStr = filez.get(j).split("/"); 
			selectMusic.addItem(arrOfStr[arrOfStr.length-1]);
		}
		
		selectMusic.setBounds(0, 0, 250, 180);
		
		panel=new JPanel();
		panel.setLayout(null);
		panel.add(selectMusic);
		
		magic();
		
		previous.addActionListener(pr);
		next.addActionListener(nx);
		start.addActionListener(st);
		stop.addActionListener(stp);
		pause.addActionListener(ps);
		resume.addActionListener(rs);
		
		
		holder.add(previous);
		holder.add(stop);
		holder.add(start);
		holder.add(pause);
		holder.add(resume);
		holder.add(next);
		holder.add(panel);
		
		add(holder);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1050,220);
		setLocationRelativeTo(null);
		setAlwaysOnTop(false);
	}
	
	////////////////methods
	
	private void magic() {
		
		previous.setBackground(Color.DARK_GRAY);
		next.setBackground(Color.DARK_GRAY);
		start.setBackground(Color.DARK_GRAY);
		stop.setBackground(Color.DARK_GRAY);
		pause.setBackground(Color.DARK_GRAY);
		resume.setBackground(Color.DARK_GRAY);
		selectMusic.setBackground(Color.DARK_GRAY);
		
		previous.setForeground(Color.white);
		next.setForeground(Color.white);
		start.setForeground(Color.white);
		stop.setForeground(Color.white);
		pause.setForeground(Color.white);
		resume.setForeground(Color.white);
		selectMusic.setForeground(Color.white);
		selectMusic.setFont(new Font("comicsans",Font.PLAIN,20));
	}
	
	
	

	
	
	ActionListener pr = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {/*24.01.2021 23:29*/

			if(player!=null) {
				player.close();
				
				pauseLocation=0;
				songTotalLength=0;
				
			}
			
			songindex--;
			if(songindex<0) {
				songindex = filez.size()-1;
			}
			
			Song = filez.get(songindex);
			 
			
			try {
				FIS=new FileInputStream(Song);
				BIS=new BufferedInputStream(FIS);
				player = new Player(BIS);
				songTotalLength=FIS.available();
				
			}catch (Exception e2) {e2.getCause();}
			
			new Thread() {
				
				public void run() {
					try {
						player.play();
					} catch (JavaLayerException e) {e.printStackTrace();} catch (Exception e) {e.printStackTrace();}
				}
				
			}.start();
			
		}
	};
	ActionListener nx = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(player!=null) {
				player.close();
				
				pauseLocation=0;
				songTotalLength=0;
				
			}
			
			songindex++;
			songindex = songindex % (filez.size());
			Song = filez.get(songindex);
			 
			
			try {
				FIS=new FileInputStream(Song);
				BIS=new BufferedInputStream(FIS);
				player = new Player(BIS);
				songTotalLength=FIS.available();
				
			}catch (Exception e2) {e2.getCause();}
			
			new Thread() {
				
				public void run() {
					try {
						player.play();
					} catch (JavaLayerException e) {e.printStackTrace();} catch (Exception e) {e.printStackTrace();}
				}
				
			}.start();
			
		}
	};
	ActionListener st = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(player!=null) {
				player.close();
				
				pauseLocation=0;
				songTotalLength=0;
				
			}
			try {
				Song = filez.get(selectMusic.getSelectedIndex());
				FIS=new FileInputStream(Song);
				BIS=new BufferedInputStream(FIS);
				player = new Player(BIS);
				songTotalLength=FIS.available();
				
			}catch (Exception e2) {e2.getCause();}
			
			new Thread(() -> {
				try {
					player.play();
				} catch (JavaLayerException e1) {
					e1.printStackTrace();} catch (Exception e1) {
					e1.printStackTrace();}
			}).start();
		}
	};
	ActionListener ps = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(player!=null) {
				
				try {
						pauseLocation=FIS.available();
						player.close();
					} catch (Exception e1) {e1.printStackTrace();}
			}
			
		}
	};
	ActionListener rs = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				FIS=new FileInputStream(Song);
				BIS=new BufferedInputStream(FIS);
				player=new Player(BIS);
				
				FIS.skip(songTotalLength-pauseLocation);
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
			new Thread() {
				
				public void run() {
					try {
						player.play();
					} catch (JavaLayerException e) {
						e.printStackTrace();
					}					
				}
				
			}.start();
			
			
		}
	};
	ActionListener stp = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(player!=null) {
				player.close();
				
				pauseLocation=0;
				songTotalLength=0;
				
			}
			
		}
	};
	
	
	public static void main(String[] args) {
		try {
			new MusicPlayer("sfx/a.mp3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}