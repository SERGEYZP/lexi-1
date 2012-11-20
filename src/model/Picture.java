package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

public class Picture extends Glyph {
	
	private BufferedImage image;
	private String fullFilePath;
	
	/*public Picture(BufferedImage image){
		if (image == null){
			throw new NullPointerException("Image is null");
		}
		
		this.image = image;
	}*/
	
	public Picture(String fullFilePath){
		this.fullFilePath = fullFilePath;
	}
	
	@Override
	public void draw(Graphics graphics, int x, int y){
		graphics.drawImage(this.getImage(), x + 3, y, null);
	}	
	
	@Override
	public void select(Graphics graphics, int x, int y){
		Color previousColor = graphics.getColor();
		graphics.setColor(Color.BLACK);		
		graphics.drawRect(x, y - 2, this.getImage().getWidth() + 2 , this.getImage().getHeight() + 2);
		graphics.fillRect(x, y - 2, this.getImage().getWidth() + 2, this.getImage().getHeight() + 2);		
		graphics.setColor(previousColor);
		this.draw(graphics, x, y);
	}
	
	@Override
	public int getWidth(){
		return this.getImage().getWidth() + 2;
	}
	
	@Override
	public int getHeight(){
		return this.getImage().getHeight() + 15;
	}
	
	private BufferedImage getImage(){
		try {			
			if (this.image == null){				
				this.image = ImageIO.read(new File(this.fullFilePath));				
			}
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		
		return this.image;
	}
}
