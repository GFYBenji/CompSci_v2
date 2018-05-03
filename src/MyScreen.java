import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyScreen implements MouseMotionListener, ActionListener, KeyListener, MouseListener {

    private JFrame window;
    private MyImage I;
    private JLabel main;

    public MyScreen(){
        makeScreen("Window1", makeImage());
    }

    protected void makeScreen(String title, BufferedImage image){
        window = new JFrame(title);
        main = new JLabel();
        window.setBounds(0,0,800,800);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setLayout(null);
        main.setBounds(0,0,800,800);
        main.setIcon(new ImageIcon(image));
        main.addMouseListener(this);
        main.addKeyListener(this);
        window.add(main);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setFocusable(true);
        window.setVisible(true);
    }

    private void makeSave(){
        BufferedImage J = I;
        try{
            File output = new File("Julia.png");
            ImageIO.write(J, "png", output);
        }catch(IOException ex){

        }
    }

    protected BufferedImage makeImage(){
        BufferedImage img;
        try{
            img = ImageIO.read(new File("Green Square.png"));
            return img;
        }catch(IOException ex){
            System.out.println("Failed To Load Image");
            return null;
        }
    }

    protected void rePaint(MyImage im){
        I = im;
        main.setIcon(new ImageIcon(I));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 's'){
            makeSave();
        }if(e.getKeyChar() == 'm'){
            window.dispose();
            new MandelScreen();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    //region Unused Methods
    @Override
    public void mouseDragged(MouseEvent e) {

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
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    //endregion
}
