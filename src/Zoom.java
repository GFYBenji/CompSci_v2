import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Zoom extends Thread {

    private int fps, frames;
    private String directory, imgName, out;
    private double stepX1, stepY1, stepX2;
    private MyImage I;
    private Calculator calc;


    public Zoom(String finalFile, int fps, int iters) {
        frames = fps * 10;
        this.fps = fps;
        String FILE_SEP = File.separator;
        directory = finalFile.substring(0, finalFile.lastIndexOf(".")) + FILE_SEP;
        out = finalFile;
        I = new MyImage(800, 800, BufferedImage.TYPE_INT_RGB);
        calc = new Calculator(I, iters);
        File d = new File(directory);
        d.mkdir();
    }

    public void calcXZoom(double dest1, double dest2) {
        stepX1 = (dest1 + 2.0)/frames;
        stepX2 = (dest2 - 2.0)/frames;

    }

    public void calcYZoom(double dest1, double dest2) {
        stepY1 = (dest1 - 2.0)/frames;
    }

    public void calcZoom(double destx1, double destx2, double desty1) {
        stepX1 = (destx1 +2.0)*0.5;
        stepX1 = 1 - stepX1/(destx1 + 2.0);
    }

    public void spin(JProgressBar bar, JLabel info) {
        double r = 0.7885;
        double a;
        I.Plot(-2, 2, 2);
        for (int i = 0; i <= 720; i++) {
            a = Math.toRadians(i / 2);
            status(720, i, bar, info);
            try{
                ImageIO.write(calc.juliaSet(r * Math.cos(a), r * Math.sin(a)), "png", new File(directory + "Julia_" + i + ".png"));
            }catch(IOException e){

            }
        }
        imgName = directory + "Julia_%0d.png";
        compile();
        info.setText("Done!");
    }
    public void linearZoom() {
        for (int i = 0; i <= fps; i++) {
            I.Plot(-2.0 + i * (stepX1), 2.0 + i * (stepY1), 2.0 + i * (stepX2));
            try {
                ImageIO.write(calc.mandelBrot(), "png", new File(directory + "Mandelbrot_" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void compile() {
        String args[] = new String[10];
        args[0] = "ffmpeg";
        args[1] = "-framerate";
        args[2] = Integer.toString(fps);
        args[3] = "-i";
        args[4] = imgName;
        args[5] = "-c:v";
        args[6] = "libx264";
        args[7] = "-pix_fmt";
        args[8] = "yuv420p";
        args[9] = out;
        try {
            ProcessBuilder build = new ProcessBuilder(args);
            build.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished Making Video");
    }

    protected void status(int total, int current, JProgressBar bar, JLabel prog) {
        double in = ((double) current / total) * 100;
        int out = (int) in;
        prog.setText(String.valueOf(out) + "%");
        bar.setValue(out);
        //System.out.println(prog.getText());
        //System.out.println(bar.getValue());
    }

    @Override
    public void run() {
    }

    public int getFps(){
        return fps;
    }

}
