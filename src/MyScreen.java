import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyScreen implements MouseMotionListener, ActionListener, KeyListener, MouseListener {

    private JFrame window;
    private MyImage I;
    private JLabel main, xCoord, yCoord;
    private JButton save, video, reset, saveConfirm;
    private JCheckBox saveDBCheck, saveImageCheck;
    private Boolean started;
    private String FILE_SEP = File.separator;

    public MyScreen(String title, int close){
        mainWindow(title, makeImage(), close);
    }

    protected void mainWindow(String title, BufferedImage image, int close){
        window = new JFrame(title);
        window.setDefaultCloseOperation(close);
        window.setBounds(0,0,800,870);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setLayout(null);

        save = new JButton("Save");
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

        main.setFocusable(true);

        window.setVisible(true);
    }

    protected void saveWindow(){
        System.out.println("Opening Save Window");
        JPanel savePanel;
        JLabel spacer1, spacer2;

        window = new JFrame("Save");
        window.setBounds(0,0,120,170);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        savePanel = new JPanel();
        savePanel.setBorder( BorderFactory.createTitledBorder( "Save" ) );
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        savePanel.setLayout( gbPanel0 );

        saveDBCheck = new JCheckBox( "Save To Database"  );
        saveDBCheck.setSelected( true );
        gbcPanel0.gridx = 2;
        gbcPanel0.gridy = 6;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( saveDBCheck, gbcPanel0 );
        savePanel.add( saveDBCheck );

        saveImageCheck = new JCheckBox( "Save As Image"  );
        saveImageCheck.setSelected( true );
        gbcPanel0.gridx = 2;
        gbcPanel0.gridy = 8;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( saveImageCheck, gbcPanel0 );
        savePanel.add( saveImageCheck );

        spacer1 = new JLabel( "label0"  );
        spacer1.setForeground( new Color( 240,240,240 ) );
        gbcPanel0.gridx = 2;
        gbcPanel0.gridy = 7;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( spacer1, gbcPanel0 );
        savePanel.add( spacer1 );

        spacer2 = new JLabel( "label1"  );
        spacer2.setForeground( new Color( 240,240,240 ) );
        gbcPanel0.gridx = 2;
        gbcPanel0.gridy = 9;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( spacer2, gbcPanel0 );
        savePanel.add( spacer2 );

        saveConfirm = new JButton( "Confirm"  );
        gbcPanel0.gridx = 2;
        gbcPanel0.gridy = 10;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( saveConfirm, gbcPanel0 );
        savePanel.add( saveConfirm );
        saveConfirm.addActionListener(this);

        window.add(savePanel);
        window.setVisible(true);
    }

    protected void makeVideo(){

    }

    protected void makeSave(){
        System.out.println("Cannot Save this Image(Error 1)");
    }

    protected BufferedImage makeImage(){
        BufferedImage img;
        try{
            img = ImageIO.read(new File("Resources/LogoStarter.png"));
            return img;
        }catch(IOException ex){
            System.out.println("Failed To Load Main Screen Image");
            return null;
        }
    }

    protected void rePaint(MyImage im){
        I = im;
        main.setIcon(new ImageIcon(I));
    }

    protected void dirChooser(BufferedImage output){
        JFileChooser jfc = new JFileChooser();
        int retVal = jfc.showSaveDialog(null);
        File f = new File(jfc.getSelectedFile().getAbsolutePath() + ".png");
        try{
            if(retVal==JFileChooser.APPROVE_OPTION){
                ImageIO.write(output,"png",f);
            }
        }catch(IOException ex){
            System.out.println("Unable to Save File");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveConfirm){
            if(saveImageCheck.isSelected()){
                makeSave();
                System.out.println("Saving To Image...");
                window.dispose();
            }if(saveDBCheck.isSelected()){
                System.out.println("Saving To DB...");
            }
        }
        if(e.getSource() == save){
            saveWindow();
        }
        if(e.getSource() == video){
            makeVideo();
        }
        if(e.getSource() == reset){
            window.dispose();
            new MandelScreen();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        window.dispose();
        new MandelScreen();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        xCoord.setText(Integer.toString(e.getX()));
        yCoord.setText(Integer.toString(e.getY()));
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
