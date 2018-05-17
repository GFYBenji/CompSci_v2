import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MandelScreen extends MyScreen{

    private  MyImage I;
    private int xS, yS;
    private JLabel xCoord, yCoord;

    protected MandelScreen(){
        super("Mandelbrot Set", 2);
    }

    @Override
    protected BufferedImage makeImage(){
        I = new MyImage(800,800, BufferedImage.TYPE_INT_RGB);
        I.Plot(-2.0,2.0,2.0,-2.0);
        Calculator calc = new Calculator(I, 300);
        I = calc.mandelBrot();
        return I;
    }

    @Override
    protected void makeVideo(){
        Zoom z = new Zoom(25);
        z.calcXZoom(I.getStartX(), I.getEndX());
        z.calcYZoom(I.getStartY(), I.getEndY());
        z.makeZoom();
        String args[] = new String[10];
        args[0] = "ffmpeg";
        args[1] = "-framerate";
        args[2] = "25";
        args[3] = "-i";
        //args[4] = dir + FILE_SEP + "Mandelbrot%1d.png";
        args[4] = "Mandelbrot_%0d.png";
        args[5] = "-c:v";
        args[6] = "libx264";
        args[7] = "-pix_fmt";
        args[8] = "yuv420p";
        args[9] = "/MandelZoom.mp4";
        args[9] = "MandelZoom.mp4";

        ProcessBuilder proc = new ProcessBuilder(args);
        try{
            proc.start();
        }catch(IOException e ){
            System.out.println("Failed To Save Video");
        }
    }

    @Override
    protected void makeSave(){
        BufferedImage J = I;
        dirChooser(J);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        xS = e.getX();
        yS = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int xE = e.getX();
        int yE = e.getY();
        if(xS == xE || yE == yS){
            new JuliaScreen(I.convertX(e.getX()), I.convertY(e.getY()));
        }else{
            I.Plot(xS,yS,xE,yE);
            Calculator calc = new Calculator(I, 300);
            I = calc.mandelBrot();
            rePaint(I);
        }
    }

}
