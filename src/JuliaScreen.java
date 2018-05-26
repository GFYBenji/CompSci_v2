import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Benji on 29/04/2018.
 */
public class JuliaScreen extends MyScreen{

    private  MyImage I;
    private Calculator calc;
    private JSlider slider;

    public JuliaScreen(Double re, Double im) {
        super("Julia Set",2 );
        I = new MyImage(800,800, BufferedImage.TYPE_INT_RGB);
        I.Plot(-2.0,2.0,2.0);
        calc = new Calculator(I, 300);
        I = calc.juliaSet(re,im);
        if(re == 0.7885){
            slider();
        }
        rePaint(I);
    }


    //@Override
    /*protected BufferedImage makeImage(){
        BufferedImage img;
        try{
            img = ImageIO.read(new File("LogoStarter.png"));
            return img;
        }catch(IOException ex){
            System.out.println("Failed To Load Julia Image Image");
            return null;
        }
    }*/

    @Override
    protected void makeSave(){
        BufferedImage J = I;
        dirChooser(J);
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider src = (JSlider)e.getSource();
        if(src.getValueIsAdjusting()){
            Double r = 0.7885;
            Double a = Math.toRadians(src.getValue());
            I = calc.juliaSet(r*Math.cos(a),r*Math.sin(a));
            rePaint(I);
        }
    }
    @Override
    protected void makeVideo(){
        Zoom z = new Zoom(10, 36, 500, "JuliaSpin");
        z.spin();
    }
}
