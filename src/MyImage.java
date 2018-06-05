import java.awt.image.BufferedImage;

public class MyImage extends BufferedImage {

    private double startX, startY, endX, endY;
    private int windowX, windowY;
    private double plotX, plotY;

    public MyImage(int width, int height, int imageType) {
        super(width, height, imageType);
        windowX = width;
        windowY = height;
    }

    public void Plot(double xS, double yS, double xE) {
        double tmp;
        if( xS > xE){
            startX = xE;
            endX = xS;
        }else{
            startX = xS;
            endX = xE;
        }
        startY = yS;
        endY = yS - (xE - xS);
        if(startY < endY){
            tmp = endY;
            endY = startY;
            startY = tmp;
        }
        plotX = endX - startX;
        plotY = startY - endY;
    }


    public double convertX(int x) {
        //x = x * plotX / windowX + startX;
        return x * plotX / windowX + startX;
    }

    public double convertY(int y) {
        //y = startY - y * plotY / windowY;
        return (startY - y * plotY / windowY);
    }

    public void getInfo(){
        System.out.println("Plot X:" + plotX + ", Plot Y: " + plotY);
        System.out.println("Start X,Y: " + startX + ", " + startY + "   End X,Y: " + endX + ", " + endY);
    }

    public double getPlotX() {
        return plotX;
    }

    public double getPlotY() {
        return plotY;
    }

    public double getStartX() {
        return startX;
    }

    public double getEndX() {
        return endX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndY() {
        return endY;
    }
}
