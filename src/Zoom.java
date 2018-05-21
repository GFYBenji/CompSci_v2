import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Zoom {

    private int fps, frames;
    private String direct, FILE_SEP;
    private double stepX1, stepY1, stepX2, stepY2;
    private MyImage I;


    public Zoom(int frames, int length) {
        fps = frames * length;
        this.frames = frames;
        direct = "MandelVideo";
        FILE_SEP = File.separator;
    }
    public void calcXZoom(Double dest1, Double dest2){
        stepX1 = (dest1 + 2.0)/fps;
        stepX2 = (dest2 - 2.0)/fps;

    }

    public void calcYZoom(Double dest1, Double dest2){
        stepY1 = (dest1 - 2.0)/fps;
        stepY2 = (dest2 + 2.0)/fps;
    }
    /*public void calcXZoom(Double x1, Double x2){
        stepX1 = 1 - ((x1 + 2.0)/2)/(x1 + 2.0);
        stepX2 = 1 - ((x2 - 2.0)/2)/(x2 - 2.0);
    }*/

    public void geometricZoom() {
        //General Formula: 1- (a/distance to move) = r
        //Then iterate through every frame using u = ar^n-1
    }

    public void linearZoom() {
        I = new MyImage(800,800, BufferedImage.TYPE_INT_RGB);
        Calculator calc = new Calculator(I, 1000);
        File dir = new File(direct);
        dir.mkdir();
        for (int i = 0; i < fps + 1; i++){
                I.Plot(-2.0+i*(stepX1),2.0+i*(stepY1),2.0+i*(stepX2));
                //System.out.println("X plot: " + I.getPlotX() + ", Y plot: " + I.getPlotY());
                try{
                    File f = new File(direct + FILE_SEP + "Mandelbrot_" + i + ".png");
                    ImageIO.write(calc.mandelBrot(), "png", f);
                }catch(IOException e){
                    e.printStackTrace();
                }
        }
        compile();
    }

    private void compile() {
        String args[] = new String[10];
        args[0] = "ffmpeg";
        args[1] = "-framerate";
        args[2] = Integer.toString(frames);
        args[3] = "-i";
        args[4] = direct + FILE_SEP + "Mandelbrot_%0d.png";
        //args[4] = "Mandelbrot_%0d.png";
        args[5] = "-c:v";
        args[6] = "libx264";
        args[7] = "-pix_fmt";
        args[8] = "yuv420p";
        //args[8] = "yuvj444p";
        args[9] = "MandelZoom.mp4";

        try {
            ProcessBuilder build = new ProcessBuilder(args);
            build.start();
            Boolean done = false;
            while (!done) {
                File f = new File(direct + FILE_SEP + "Mandelbrot_" + fps + ".png");
                if (f.exists()) {
                    Thread.sleep(5000);
                    done = true;
                    File index = new File(direct);
                    String[] entries = index.list();
                    for (String s : entries) {
                        File g = new File(index.getPath(), s);
                        g.delete();
                    }
                    f = new File(direct);
                    f.delete();
                }
            }
        } catch (Exception e) {
            //System.out.println("Failed To Save Video");
            e.printStackTrace();
        }
        System.out.println("Finished Making Video");
    }

    public int getFps(){
        return fps;
    }

}
