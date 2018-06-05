import java.awt.*;

public class Calculator {

    private int MAX_ITER;
    private double cIm, cRe, zIm, zRe, tmp;
    private MyImage I;
    private int[] colors;

    public Calculator(MyImage image, int iter){
        I = image;
        MAX_ITER =  iter;
        makeColors();
    }

    protected MyImage mandelBrot() {
        final long startTime = System.currentTimeMillis();
        for(int y = 0; y < I.getHeight(); y++){
            cIm = I.convertY(y);
            for(int x = 0; x <  I.getWidth(); x++){
                cRe = I.convertX(x);
                zRe = zIm = 0.0;
                int iter = 0;
                while(zRe*zRe + zIm*zIm < 4 && iter < MAX_ITER -1){
                    tmp = zRe*zRe - zIm*zIm + cRe;
                    zIm = 2.0 * zRe*zIm + cIm;
                    zRe = tmp;
                    iter++;
                }

                if(iter < MAX_ITER -1){
                    //I.setRGB(x,y, Color.white.getRGB());
                    I.setRGB(x, y, colors[iter]);
                }else{
                    I.setRGB(x,y, Color.black.getRGB());
                }
            }
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
        return I;
    }

    protected MyImage juliaSet(double re, double im) {
        final long startTime = System.currentTimeMillis();
        //cRe= -0.7;
        //cIm = 0.27015;
        cRe = re;
        cIm = im;
        for(int y = 0; y < I.getHeight(); y++){
            double yStart = I.convertY(y); //convert Y up here to be 25% faster(at 200 iterations)
            for (int x = 0; x < I.getWidth(); x ++){
                zIm = yStart; //I.convertY(y);
                zRe = I.convertX(x);
                int iter = 0;
                while ((zRe * zRe + zIm * zIm) < 4 && iter < MAX_ITER - 1) {
                    tmp = zRe * zRe - zIm * zIm + cRe;
                    zIm = 2.0 * zRe*zIm + cIm;
                    zRe = tmp;
                    iter++;
                }
                if(iter < MAX_ITER -1){
                    //I.setRGB(x,y, Color.white.getRGB());
                    I.setRGB(x, y, colors[iter]);
                }else{
                    I.setRGB(x,y, Color.black.getRGB());
                }
            }
        }
        //final long endTime = System.currentTimeMillis();
        //System.out.println("Total execution time: " + (endTime - startTime) );
        return I;
    }

    private void makeColors() {
        colors = new int[MAX_ITER];
        for (int i = 0; i < MAX_ITER; i++) {
            colors[i] = Color.HSBtoRGB((float) i / MAX_ITER, 0.5F, 1);
        }
    }

}
