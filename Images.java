package snake;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.FileInputStream;

public class Images {
	
	BufferedImage snake; {
        try {
            File file = new File("C:/Users/naema/eclipse-workspace/Snake/src/R.png");
            FileInputStream fis = new FileInputStream(file);
            snake = ImageIO.read(fis);
        } catch (IOException e) {
            System.err.println(e);
        }
       }
	BufferedImage snakehead; {
        try {
            File file = new File("C:/Users/naema/eclipse-workspace/Snake/src/snake/snakeheadoriginal.png");
            FileInputStream fis = new FileInputStream(file);
            snakehead = ImageIO.read(fis);
        } catch (IOException e) {
            System.err.println(e);
        }
       }
	BufferedImage apple; {
        try {
            File file = new File("C:/Users/naema/eclipse-workspace/Snake/src/utilities/apple.png");
            FileInputStream fis = new FileInputStream(file);
            apple = ImageIO.read(fis);
        } catch (IOException e) {
            System.err.println(e);
        }
       }
	BufferedImage background; {
        try {
            File file = new File("C:/Users/naema/eclipse-workspace/Snake/src/snake/snakeoriginalbackground2.png");
            FileInputStream fis = new FileInputStream(file);
            background = ImageIO.read(fis);
        } catch (IOException e) {
            System.err.println(e);
        }
       }
	BufferedImage powerup; {
        try {
            File file = new File("C:/Users/naema/eclipse-workspace/Snake/src/snake/powerup.png");
            FileInputStream fis = new FileInputStream(file);
            powerup = ImageIO.read(fis);
        } catch (IOException e) {
            System.err.println(e);
        }
       }
}

