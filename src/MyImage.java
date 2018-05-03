import java.awt.image.BufferedImage;

public class MyImage extends BufferedImage {

    private Double startX, startY, endX, endY;
    private int windowX, windowY;
    private Double plotX, plotY;

    public MyImage(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    public void Plot(Double xS, Double yS, Double xE, Double yE) {
        if (xS > xE) {
            startX = xE;
            endX = xS;
        } else {
            startX = xS;
            endX = xE;
        }
        if (yS < yE) {
            startY = yE;
            endY = yS;
        } else {
            startY = yS;
            endY = yE;
        }
        plotX = endX - startX;
        plotY = startY - endY;
        windowY = windowX = 800;
    }

    public void Plot(int xS, int yS, int xE, int yE){
        startX = convertX(xS);
        startY = convertY(yS);
        endX = convertX(xE);
        endY = convertY(yE);
        plotX = endX - startX;
        plotY = startY - endY;
    }

    public Double convertX(int x) {
        //x = x * plotX / windowX + startX;
        return x * plotX / windowX + startX;
    }

    public Double convertY(int y) {
        //y = startY - y * plotY / windowY;
        return (startY - y * plotY / windowY);
    }

    public Double getPlotX(){
        return plotX;
    }
    public Double getPlotY(){
        return plotY;
    }
}
