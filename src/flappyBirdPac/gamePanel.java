package flappyBirdPac;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class gamePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean gameOver=false;
	public static int score= 0;
	public static boolean beforeStart= false;
	public static int startCounter= -1;
	
	public static final int width=600;
	public static final int height=800;
	private int x=0;
	private BufferedImage img;
	
	birdImage bi=new birdImage();
	pillarImage pi=new pillarImage(gamePanel.width);
	pillarImage pi2=new pillarImage(gamePanel.width + (gamePanel.width/2));
	
	public gamePanel(){
	
		LoadImage();
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				
				super.mousePressed(e);
				bi.moveUp();
			}
		});
	}

	private void LoadImage() {
		
		try{
			img=ImageIO.read(new File("G:/Java Programs/FlappyBird/FlappyBirdImages/background image.png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g){
		
		super.paint(g);
		g.drawImage(img, x, 0, null);
		g.drawImage(img, x+2400, 0, null);
		
		bi.drawBird(g);
		pi.drawPillar(g);
		pi2.drawPillar(g);
		
		g.setFont(new Font("Tahoma",Font.BOLD,40));
		g.drawString("Score "+score, 0, 35);
		
		if(beforeStart){
			g.setFont(new Font("Tahoma",Font.BOLD,150));
			g.drawString(Integer.toString(startCounter), width/2-75, 250);
		}
	}
	
	public void move(){
		
		bi.moveBird();
		pi.movePillar();
		pi2.movePillar();
		
		if(gameOver){
			pi.X= gamePanel.width;
			pi2.X= gamePanel.width + (gamePanel.width/2);
			gameOver = false;
		}
		
		x += pillarImage.speed;
		if(x <= -2400){
			x=0;
		}
		
		if(pi.X == birdImage.x || pi2.X == birdImage.x){
			score++;
		}
	}
	
	public static boolean dialogueBox(){
		
		int res= JOptionPane.showConfirmDialog(null,"Game Over, Your score is : "+score+"\nDo you want to RESTART the game...","Game Over", JOptionPane.YES_NO_OPTION);
		
		if(res==JOptionPane.YES_OPTION){
			return true;
		}
		else{
			return false;
		}
	}
}
