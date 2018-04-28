import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyScreen implements MouseMotionListener {

    private JFrame window;
    private JLabel main, xCoord, yCoord;
    private JButton save, video, reset;

    public MyScreen(){
        //makeScreen("Mandelbrot", makeImage());
    }

    protected  void makeScreen(String title, BufferedImage image){
        window = new JFrame(title);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setBounds(0,0,800,870);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.addMouseMotionListener(this);
        window.setLayout(null);

        save = new JButton("Save Menu");
        save.setBounds(0,800,100,30);
        video = new JButton("Save Video");
        video.setBounds(100,800,100,30);
        reset = new JButton("Reset Image");
        reset.setBounds(200,800,125,30);
        xCoord = new JLabel("X: 0.0");
        xCoord.setBounds(335, 800, 200, 30);
        yCoord = new JLabel("Y: 0.0");
        yCoord.setBounds(535, 800, 200, 30);
        main = new JLabel();
        main.setBounds(0,0,800,800);
        main.setIcon(new ImageIcon(image));

        window.add(main);
        window.add(save);
        window.add(video);
        window.add(reset);
        window.add(xCoord);
        window.add(yCoord);
        window.setVisible(true);
    }

    protected BufferedImage makeImage(){
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File("C:\\Users\\Benji\\IdeaProjects\\CompSci_v2\\Green Square.png"));
            return img;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        xCoord.setText("X: " + Integer.toString(e.getX()));
        yCoord.setText("Y: " + Integer.toString(e.getY()));
    }
}
