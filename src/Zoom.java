import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Zoom {

    private int fps, frames, iters;
    private String direct, FILE_SEP;
    private double stepX1, stepY1, stepX2, stepY2;
    private MyImage I;
    private Double r, a;


    public Zoom(int frames, int length, int iters, String folder) {
        this.frames = frames * length;
        this.iters = iters;
        fps = frames;
        direct = folder;
        FILE_SEP = File.separator;
    }
    public void calcXZoom(Double dest1, Double dest2){
        stepX1 = (dest1 + 2.0)/frames;
        stepX2 = (dest2 - 2.0)/frames;

    }

    public void calcYZoom(Double dest1, Double dest2){
        stepY1 = (dest1 - 2.0)/frames;
        stepY2 = (dest2 + 2.0)/frames;
    }
    public void calcZoom(Double destx1, Double destx2, Double desty1){
        stepX1 = (destx1 +2.0)*0.5;
        stepX1 = 1 - stepX1/(destx1 + 2.0);
    }

    public void geometricZoom() {
        //General Formula: 1- (a/distance to move) = r
        //Then iterate through every frame using u = ar^n-1
        I = new MyImage(800,800,BufferedImage.TYPE_INT_RGB);
        Calculator calc = new Calculator(I, iters);
        File dir = new File(direct);
        dir.mkdir();
        for(int i = 0; i <= frames; i++){
            stepX1 = a*Math.pow(r,i);
        }
    }

    protected void spin(){
        I = new MyImage(800,800, BufferedImage.TYPE_INT_RGB);
        Calculator calc = new Calculator(I, iters);
        File dir = new File(direct);
        dir.mkdir();
        Double r = 0.7885;
        Double a;
        for(int i = 0; i <= frames; i ++){
            a = Math.toRadians(i);
            I.Plot(-2.0,2.0,2.0);
            try{
                File f = new File(direct + FILE_SEP + "Julia_" + i + ".png");
                ImageIO.write(calc.juliaSet(r*Math.cos(a),r*Math.sin(a)),"png", f);
            }catch(IOException e){

            }
        }
        compile("Julia_%0d.png", "JuliaSpin.mp4");
    }

    public void linearZoom() {
        I = new MyImage(800,800, BufferedImage.TYPE_INT_RGB);
        Calculator calc = new Calculator(I, iters);
        File dir = new File(direct);
        dir.mkdir();
        for (int i = 0; i < frames + 1; i++){
                I.Plot(-2.0+i*(stepX1),2.0+i*(stepY1),2.0+i*(stepX2));
                //System.out.println("X plot: " + I.getPlotX() + ", Y plot: " + I.getPlotY());
                try{
                    File f = new File(direct + FILE_SEP + "Mandelbrot_" + i + ".png");
                    ImageIO.write(calc.mandelBrot(), "png", f);
                }catch(IOException e){
                    e.printStackTrace();
                }
        }
        compile("Mandelbrot_%0d.png", "MandelZoom.mp4");
    }

    private void compile(String name, String out) {
        String args[] = new String[10];
        args[0] = "ffmpeg";
        args[1] = "-framerate";
        args[2] = Integer.toString(fps);
        args[3] = "-i";
        //args[4] = direct + FILE_SEP + name;
        args[4] = direct + FILE_SEP + "Julia_%0d.png";
        args[5] = "-c:v";
        args[6] = "libx264";
        args[7] = "-pix_fmt";
        args[8] = "yuv420p";
        //args[8] = "yuvj444p";
        args[9] = out;

        try {
            ProcessBuilder build = new ProcessBuilder(args);
            build.start();
            //kBoolean done = false;
            /*while (!done) {
                File f = new File(direct + FILE_SEP + "Mandelbrot_" + fps + ".png");
                File j = new File( direct + FILE_SEP + "Julia_" + fps + ".png");
                if (f.exists() || j.exists()) {
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
            }*/
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
