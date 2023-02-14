package flappyBirdPac;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class birdImage {
	
	private BufferedImage img=null;
	private static int bird_diam=36;
	public static int x=(gamePanel.width/2)-bird_diam/2;
	public static int y=(gamePanel.height/2);
	
	private static int speed=1;
	private int acc=1;
	
	public birdImage(){
		LoadImage();
	}

	private void LoadImage() {
		// TODO Auto-generated method stub
		try{
			img=ImageIO.read(new File("G:/Java Programs/FlappyBird/FlappyBirdImages/bird image.png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public void drawBird(Graphics g){
		g.drawImage(img, x, y, null);
	}
	
	public void moveBird(){
		
		if(y>=0 && y<=gamePanel.height){
			speed += acc;
			y += speed;
		}
		else{
			
			boolean option= gamePanel.dialogueBox();
			if(option){
				try{
					Thread.sleep(500);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				reset();
			}
			else{
				JFrame frame= mainBird.getFrame();
				frame.dispose();
				mainBird.timer.stop();
			}
		}
	}
	public void moveUp(){
		speed = -17;
	}
	public static void reset() {
		
		speed=2;
		y=(gamePanel.height/2);
		
		gamePanel.gameOver= true;
		gamePanel.score= 0;
	}
	public static Rectangle getBirdRect(){
		
		Rectangle birdRect= new Rectangle(x,y,bird_diam,35);
		return birdRect;
	}

}
