import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MandelScreen extends MyScreen{

    private  MyImage I;
    private int xS, yS;

    protected MandelScreen(){
        super("Mandelbrot Set", 2);
    }

    @Override
    protected BufferedImage makeImage(){
        I = new MyImage(800,800, BufferedImage.TYPE_INT_RGB);
        I.Plot(-2.0,2.0,2.0);
        Calculator calc = new Calculator(I, 1000);
        I = calc.mandelBrot();
        return I;
    }

    @Override
    protected void makeVideo(){
        Zoom z = new Zoom(25, 5);
        z.calcXZoom(I.getStartX(), I.getEndX());
        z.calcYZoom(I.getStartY(), I.getEndY());
        z.linearZoom();
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
            System.out.println("Start X,Y: " + I.convertX(xS) + ", " + I.convertY(yS) + "   End X,Y: " + I.convertX(xE));
            I.Plot(I.convertX(xS), I.convertY(yS),I.convertX(xE));
            Calculator calc = new Calculator(I, 300);
            I = calc.mandelBrot();
            rePaint(I);
        }
    }
}
