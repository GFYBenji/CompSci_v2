import java.awt.image.BufferedImage;

public class MyImage extends BufferedImage {

    private Double startX, startY, endX, endY;
    private int windowX, windowY;
    private Double plotX, plotY;

    public MyImage(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    public void Plot(Double xS, Double yS, Double xE){
        Double tmp;
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
        windowY = windowX = 800;
    }


    public Double convertX(int x) {
        //x = x * plotX / windowX + startX;
        return x * plotX / windowX + startX;
    }

    public Double convertY(int y) {
        //y = startY - y * plotY / windowY;
        return (startY - y * plotY / windowY);
    }

    public void getInfo(){
        System.out.println("Plot X:" + plotX + ", Plot Y: " + plotY);
        System.out.println("Start X,Y: " + startX + ", " + startY + "   End X,Y: " + endX + ", " + endY);
    }

    public Double getPlotX(){
        return plotX;
    }
    public Double getPlotY(){
        return plotY;
    }
    public Double getStartX() {
        return startX;
    }
    public Double getEndX() {
        return endX;
    }
    public Double getStartY() {
        return startY;
    }
    public Double getEndY() {
        return endY;
    }
}
