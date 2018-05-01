import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyScreen implements MouseMotionListener, ActionListener, KeyListener, MouseListener {

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

        save.addActionListener(this);
        video.addActionListener(this);
        reset.addActionListener(this);
        main.addMouseListener(this);
        main.addKeyListener(this);
        main.addMouseMotionListener(this);

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
            img = ImageIO.read(new File("Green Square.png"));
            return img;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    protected void saveItem() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:ucanaccess://SetStorage.accdb");
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("INSERT INTO JuliaTable(ID) VALUES(JEFF)");
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        xCoord.setText("X: " + Integer.toString(e.getX()));
        yCoord.setText("Y: " + Integer.toString(e.getY()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            saveItem();
        }
    }

    //region Unused Methods
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    //endregion
}
