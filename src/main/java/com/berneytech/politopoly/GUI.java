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

    public ImageFrame(String filePath){
        super();
        setTitle("ImageTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        ImageComponent component = new ImageComponent(filePath);
        add(component);

    }

    public static final int DEFAULT_WIDTH = 725;
    public static final int DEFAULT_HEIGHT = 775;
}


 class ImageComponent extends JComponent{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Image image;
    public ImageComponent(String filePath){
        super();
        try{
            File image2 = new File(filePath);
            image = ImageIO.read(image2);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void scaleImage(Graphics g){
        g.drawImage(image.getScaledInstance(700, -1, Image. SCALE_SMOOTH), 0,0, this);
    }
    public void paintComponent (Graphics g){
        if(image == null) return;
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
        if (imageWidth>1000){
            g.drawImage(image.getScaledInstance(700, -1, Image. SCALE_SMOOTH), 0,0, this);
        }
        else{
            g.drawImage(image.getScaledInstance(50, -1, Image. SCALE_SMOOTH), 200,200, this);
        }
        for (int i = 0; i*imageWidth <= getWidth(); i++)
            for(int j = 0; j*imageHeight <= getHeight();j++)
                if(i+j>0) g.copyArea(0, 0, imageWidth, imageHeight, i*imageWidth, j*imageHeight);
    }

}
public class GUI{
    ImageFrame frame;
    public GUI(String filePath){
            frame = new ImageFrame(filePath);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true); 
    }
    void addToFrame(String filePath){
        ImageComponent component = new ImageComponent(filePath);
        frame.add(component);
    }
    void removeFromFrame(int a){
        frame.remove(a);
    }

}




