package flappyBirdPac;

import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class menuPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img=null;
	public boolean start=false;
	
	public menuPanel(){
		
		LoadImage();
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				
				super.mouseClicked(e);
				start=true;
			}
		});
	}
	private void LoadImage() {
		
		try{
			img=ImageIO.read(new File("G:/Java Programs/FlappyBird/FlappyBirdImages/first page.png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g){
		
		super.paint(g);
		g.drawImage(img, 0, 0, gamePanel.width, gamePanel.height, null);
	}

}
