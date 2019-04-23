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
        setTitle("ImageTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        ImageComponent component = new ImageComponent();
        add(component);

    }

    public static final int DEFAULT_WIDTH = 1203;
    public static final int DEFAULT_HEIGHT = 1232;
}


class ImageComponent extends JComponent{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Image image;
    public ImageComponent(){
        try{
            File image2 = new File("C:\\Users\\bernelia000\\Documents\\NetBeansProjects\\politopoly\\src\\bitmap.png");
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

        g.drawImage(image, 0, 0, this);

        for (int i = 0; i*imageWidth <= getWidth(); i++)
            for(int j = 0; j*imageHeight <= getHeight();j++)
                if(i+j>0) g.copyArea(0, 0, imageWidth, imageHeight, i*imageWidth, j*imageHeight);
    }

}



public class GUI {
    
}
