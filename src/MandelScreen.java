import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MandelScreen extends MyScreen{

    private  MyImage I;
    private int xS, yS;

    public MandelScreen(){

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
    public void mousePressed(MouseEvent e) {
        xS = e.getX();
        yS = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int xE = e.getX();
        int yE = e.getY();
        if(xS == xE || yE == yS){
            System.out.println("Click!");
        }else{
            I.Plot(xS,yS,xE,yE);
            Calculator calc = new Calculator(I, 300);
            I = calc.mandelBrot();
            rePaint(I);
        }
    }
}
