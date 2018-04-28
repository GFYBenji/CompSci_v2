import java.awt.image.BufferedImage;

public class MandelScreen extends MyScreen{

    public MandelScreen(){

    }

    @Override
    protected BufferedImage makeImage(){
        MyImage image = new MyImage(800,800, BufferedImage.TYPE_INT_RGB);
        image. Plot(-2.0,2.0,2.0,-2.0);
        Calculator calc = new Calculator(image, 200);
        return calc.mandelBrot();
    }
}
