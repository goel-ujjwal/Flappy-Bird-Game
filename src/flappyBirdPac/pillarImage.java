package flappyBirdPac;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class pillarImage {
	
	private Random r=new Random();
	public int X;
	public int Y=r.nextInt(gamePanel.height-400)+200; // max=600 min=200
	private int width_pillar=45;
	private int height_pillar=gamePanel.height-Y;
	private int gap=200;
	
	public static int speed= -2;
	private BufferedImage img=null;
	
	public pillarImage(int X){
		
		this.X=X;
		LoadImage();
	}

	private void LoadImage() {
		// TODO Auto-generated method stub
		try{
			img=ImageIO.read(new File("G:/Java Programs/FlappyBird/FlappyBirdImages/pillar image.png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void drawPillar(Graphics g){
		
		g.drawImage(img, X, Y, null); // bottom wall
		g.drawImage(img, X, (-gamePanel.height)+(Y-gap), null); // upper wall
	}
	
	public void movePillar(){
		
		X += speed;
		if(X + width_pillar <= 0){
			X= gamePanel.width;
			Y= r.nextInt(gamePanel.height-400)+200;
			height_pillar= gamePanel.height-Y;
		}
		
		Rectangle lowRect= new Rectangle(X,Y,width_pillar,height_pillar);
		Rectangle upRect= new Rectangle(X,0,width_pillar,gamePanel.height-(height_pillar+gap));
		
		if(lowRect.intersects(birdImage.getBirdRect()) || upRect.intersects(birdImage.getBirdRect())){
			
			boolean option= gamePanel.dialogueBox();
			if(option){
				try{
					Thread.sleep(500);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				birdImage.reset();
				pillarReset();
			}
			else{
				JFrame frame= mainBird.getFrame();
				frame.dispose();
				mainBird.timer.stop();
			}
		}
	}

	private void pillarReset() {
		
		Y=r.nextInt(gamePanel.height-400)+200;
		height_pillar=gamePanel.height-Y;
		
		gamePanel.gameOver= true;
	}
}
