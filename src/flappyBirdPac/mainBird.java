package flappyBirdPac;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JFrame;

public class mainBird {

	public static JFrame jf;
	public static Timer timer, timer2;
	private int mainStartCounter= 4;
	
	private mainBird(){
		
		jf=new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(gamePanel.width, gamePanel.height);
		jf.setLocationRelativeTo(null);
		jf.setTitle("Flappy Bird");
		jf.setResizable(true);
		
	}
	private void makePanels(){
		
		menuPanel mp=new menuPanel();
		gamePanel gp=new gamePanel();
		
		timer=new Timer(20,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				gp.repaint();
				gp.move();
			}
		});
		
		jf.add(mp);
		jf.setVisible(true);
		
		while(!mp.start){
			try{
				Thread.sleep(10);
			}
			catch(Exception e){
				e.printStackTrace();
			}	
		}
		
		jf.remove(mp);
		jf.add(gp);
		gp.setVisible(true);
		jf.revalidate();
		
		timer2= new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				mainStartCounter--;
				gamePanel.startCounter= mainStartCounter;
				gamePanel.beforeStart= true;			
				gp.repaint();
				if(mainStartCounter == 0){
					timer2.stop();
					timer.start();
					gamePanel.beforeStart= false;
				}
			}
		});
		
		timer2.start();
	}
	
	public static JFrame getFrame(){
		return jf;
	}
	
	public static void main(String[] args) {
		
		mainBird mb=new mainBird();
		mb.makePanels();
	}

}
