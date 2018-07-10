import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JuliaScreen extends MyScreen{

    private  MyImage I;
    private Calculator calc;
    //private JTextField fpsTxt, itersTxt;

    public JuliaScreen(double re, double im) {
        super("Julia Set",2 );
        I = new MyImage(800,800, BufferedImage.TYPE_INT_RGB);
        I.Plot(-2, 2, 2);
        calc = new Calculator(I, 300);
        I = calc.juliaSet(re,im);
        if(re == 0.7885){
            slider();
        }
        rePaint(I);
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
    public void stateChanged(ChangeEvent e) {
        JSlider src = (JSlider)e.getSource();
        if(src.getValueIsAdjusting()){
            double r = 0.7885;
            double a = Math.toRadians(src.getValue());
            I = calc.juliaSet(r*Math.cos(a),r*Math.sin(a));
            rePaint(I);
        }
    }

    @Override
    protected void makeVideo(){
        /*progressWindow();
        Thread t1 = new Thread(new Zoom(dirChooser(".mp4"), Integer.valueOf(fpsTxt.getText()), Integer.valueOf(itersTxt.getText())) {
           //@Override
            public void run() {
                //spin(loadingBar, progressLabel);
            }
        });
        t1.start();*/
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
