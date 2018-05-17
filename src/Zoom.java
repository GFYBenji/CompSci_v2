import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Zoom {

    private int fps;
    private double stepX1, stepY1, stepX2, stepY2;
    private MyImage I;


    public Zoom(int frames){
        fps = frames*5;
    }
    public void calcXZoom(Double dest1, Double dest2){
        stepX1 = (-2.0 + dest1)/fps;
        stepX2 = (2.0 - dest2)/fps;

    }

    public void calcYZoom(Double dest1, Double dest2){
        stepY1 = (2.0 - dest1)/fps;
        stepY2 = (-2.0 + dest2)/fps;
    }

    public void makeZoom(){
        I = new MyImage(800,800, BufferedImage.TYPE_INT_RGB);
        Calculator calc = new Calculator(I,300);
        for (int i = 0; i < fps + 1; i++){
                I.Plot(-2.0-i*(stepX1),2.0-i*(stepY1),2.0-i*(stepX2),-2.0-i*(stepY2));
                //System.out.println("X plot: " + I.getPlotX() + ", Y plot: " + I.getPlotY());

                try{
                    ImageIO.write(calc.mandelBrot(), "png", new File("Mandelbrot_" + i + ".png"));
                }catch(IOException e){
                    e.printStackTrace();
                }
        }
    }

}
