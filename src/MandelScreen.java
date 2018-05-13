import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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
    protected void makeSave(){
        BufferedImage J = I;
        /*try{
            int num=0;
            String name = "Mandelbrot.png";
            File output = new File(name);
            while(output.exists()){
                name = "Mandelbrot" +(num++)+".png";
                output = new File(name);
            }
            ImageIO.write(J, "png", output);
        }catch(IOException ex){
            System.out.println("Failed To Save Image");
        }*/
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
