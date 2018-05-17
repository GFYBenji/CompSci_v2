import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Benji on 29/04/2018.
 */
public class JuliaScreen extends MyScreen{

    private  MyImage I;
    private Calculator calc;

    public JuliaScreen(Double re, Double im) {
        super("Julia Set",2 );
        I = new MyImage(800,800, BufferedImage.TYPE_INT_RGB);
        I.Plot(-2.0,2.0,2.0,-2.0);
        calc = new Calculator(I, 300);
        I = calc.juliaSet(re,im);
        rePaint(I);
    }

    @Override
    protected BufferedImage makeImage(){
        BufferedImage img;
        try{
            img = ImageIO.read(new File("LogoStarter.png"));
            return img;
        }catch(IOException ex){
            System.out.println("Failed To Load Julia Image Image");
            return null;
        }
    }

    @Override
    protected void makeSave(){
        BufferedImage J = I;
        dirChooser(J);
    }
}
