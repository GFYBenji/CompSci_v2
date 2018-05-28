import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MandelScreen extends MyScreen{

    private  MyImage I;
    private int xS, yS;

    protected MandelScreen(){
        super("Mandelbrot Set", 2);
    }

    @Override
    protected BufferedImage makeImage(){
        I = new MyImage(800,800, BufferedImage.TYPE_INT_RGB);
        I.Plot(-2, 2, 2);
        Calculator calc = new Calculator(I, 300);
        I = calc.mandelBrot();
        return I;
    }

    @Override
    protected void makeVideo(){
        //Zoom z = new Zoom(25, 5, 300, "Mandelvideo");
        //z.calcXZoom(I.getStartX(), I.getEndX());
        //z.calcYZoom(I.getStartY(), I.getEndY());
        //z.linearZoom();
        //z.calcZoom(I.getStartX(), I.getEndX(), I.getStartY());
        //z.geometricZoom();
    }

    @Override
    protected void makeSave(){
        try {
            ImageIO.write(I, "png", new File(dirChooser(".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            System.out.println("Start X,Y: " + I.convertX(xS) + ", " + I.convertY(yS) + "   End X,Y: " + I.convertX(xE));
            I.Plot(I.convertX(xS), I.convertY(yS),I.convertX(xE));
            Calculator calc = new Calculator(I, 300);
            I = calc.mandelBrot();
            rePaint(I);
        }
    }

    @Override
    public void keyTyped(KeyEvent e){
        if(e.getKeyChar() == 'k'){
            double r = 0.7885;
            double a = 0.0;
            new JuliaScreen(r*Math.cos(a),r*Math.sin(a));
        }
    }
}
