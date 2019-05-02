package com.berneytech.politopoly;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

        
            


    

class ImageFrame extends JFrame{

    public ImageFrame(){
        super();
        setTitle("ImageTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public static final int DEFAULT_WIDTH = 900;
    public static final int DEFAULT_HEIGHT = 775;
}


 class ImageComponent extends JComponent{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Image image;
    int x;
    int y;
    int size;
    String name;
    public ImageComponent(String filePath){
        super();
        x=0;
        y=0;
        size=100;
        setImage(filePath);
    }
    public void setImage(String filePath){
        try{
            File image2 = new File(filePath);
            image = ImageIO.read(image2);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public ImageComponent(String filePath, int x, int y, int size, String name){
        super();
        this.name=name;
        this.x=x;
        this.y=y;
        this.size=size;
        try{
            File image2 = new File(filePath);
            image = ImageIO.read(image2);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void paintComponent (Graphics g){
        if(image == null) return;
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
            g.drawImage(image.getScaledInstance(size, -1, Image. SCALE_SMOOTH), x,y, this);
        for (int i = 0; i*imageWidth <= getWidth(); i++)
            for(int j = 0; j*imageHeight <= getHeight();j++)
                if(i+j>0) g.copyArea(0, 0, imageWidth, imageHeight, i*imageWidth, j*imageHeight);
    }

}
public class GUI{
    ImageFrame frame;
    public GUI(){
            frame = new ImageFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true); 
    }
    void addToFrame(ImageComponent component){
        frame.add(component);
        frame.setVisible(true);
    }
    void removeFromFrame(ImageComponent component){
        frame.remove(component);
    }

}




