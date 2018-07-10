import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyScreen implements MouseMotionListener, ActionListener, KeyListener, MouseListener, ChangeListener {

    private JFrame window;
    private JLabel main, xCoord, yCoord;
    private JButton save, video, reset, saveConfirm, videoConfirm;
    private JCheckBox saveDBCheck, saveImageCheck;
    protected JTextField fpsTxt, itersTxt;
    protected JProgressBar loadingBar;
    protected JLabel progressLabel;

    public MyScreen(String title, int close){
        mainWindow(title, makeImage(), close);
        //saveWindow();
        //slider();
        //videoWindow();
        //progressWindow();
    }

    //region GUI
    private void mainWindow(String title, BufferedImage image, int close) {
        window = new JFrame(title);
        window.setDefaultCloseOperation(close);
        //window.setBounds(0,0,800,870);
        window.setBounds(0,0,1920,1080);
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
        main.setBounds(0,0,1920,1080);
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

    private void saveWindow() {
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

    protected void slider() {
        JFrame window = new JFrame();
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
        slider.addChangeListener(this);
        slider.setMajorTickSpacing(180);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        window.add(slider);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setLocation(window.getX() + 500, window.getY());
        window.setVisible(true);
    }

    private void videoWindow() {
        window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setBounds(0, 0, 200, 150);
        window.setResizable(false);

        JPanel videoPanel;
        JLabel itersLbl, fpsLbl;

        JLabel spacer1, spacer2, spacer3;

        videoPanel = new JPanel();
        videoPanel.setBorder(BorderFactory.createTitledBorder("Save Video"));
        GridBagLayout gbVideoPanel = new GridBagLayout();
        GridBagConstraints gbcVideoPanel = new GridBagConstraints();
        videoPanel.setLayout(gbVideoPanel);

        videoConfirm = new JButton("Save");
        gbcVideoPanel.gridx = 3;
        gbcVideoPanel.gridy = 8;
        gbcVideoPanel.gridwidth = 3;
        gbcVideoPanel.gridheight = 1;
        gbcVideoPanel.fill = GridBagConstraints.BOTH;
        gbcVideoPanel.weightx = 1;
        gbcVideoPanel.weighty = 0;
        gbcVideoPanel.anchor = GridBagConstraints.NORTH;
        gbVideoPanel.setConstraints(videoConfirm, gbcVideoPanel);
        videoPanel.add(videoConfirm);
        videoConfirm.addActionListener(this);

        itersLbl = new JLabel("Iterations:");
        gbcVideoPanel.gridx = 2;
        gbcVideoPanel.gridy = 4;
        gbcVideoPanel.gridwidth = 2;
        gbcVideoPanel.gridheight = 1;
        gbcVideoPanel.fill = GridBagConstraints.BOTH;
        gbcVideoPanel.weightx = 1;
        gbcVideoPanel.weighty = 1;
        gbcVideoPanel.anchor = GridBagConstraints.NORTH;
        gbVideoPanel.setConstraints(itersLbl, gbcVideoPanel);
        videoPanel.add(itersLbl);

        fpsLbl = new JLabel("FPS");
        gbcVideoPanel.gridx = 2;
        gbcVideoPanel.gridy = 6;
        gbcVideoPanel.gridwidth = 2;
        gbcVideoPanel.gridheight = 1;
        gbcVideoPanel.fill = GridBagConstraints.BOTH;
        gbcVideoPanel.weightx = 1;
        gbcVideoPanel.weighty = 1;
        gbcVideoPanel.anchor = GridBagConstraints.NORTH;
        gbVideoPanel.setConstraints(fpsLbl, gbcVideoPanel);
        videoPanel.add(fpsLbl);

        itersTxt = new JTextField();
        gbcVideoPanel.gridx = 4;
        gbcVideoPanel.gridy = 4;
        gbcVideoPanel.gridwidth = 4;
        gbcVideoPanel.gridheight = 1;
        gbcVideoPanel.fill = GridBagConstraints.BOTH;
        gbcVideoPanel.weightx = 1;
        gbcVideoPanel.weighty = 0;
        gbcVideoPanel.anchor = GridBagConstraints.NORTH;
        gbVideoPanel.setConstraints(itersTxt, gbcVideoPanel);
        videoPanel.add(itersTxt);

        fpsTxt = new JTextField();
        gbcVideoPanel.gridx = 4;
        gbcVideoPanel.gridy = 6;
        gbcVideoPanel.gridwidth = 4;
        gbcVideoPanel.gridheight = 1;
        gbcVideoPanel.fill = GridBagConstraints.BOTH;
        gbcVideoPanel.weightx = 1;
        gbcVideoPanel.weighty = 0;
        gbcVideoPanel.anchor = GridBagConstraints.NORTH;
        gbVideoPanel.setConstraints(fpsTxt, gbcVideoPanel);
        videoPanel.add(fpsTxt);

        spacer1 = new JLabel("SPACER");
        spacer1.setForeground(new Color(240, 240, 240));
        gbcVideoPanel.gridx = 2;
        gbcVideoPanel.gridy = 5;
        gbcVideoPanel.gridwidth = 6;
        gbcVideoPanel.gridheight = 1;
        gbcVideoPanel.fill = GridBagConstraints.BOTH;
        gbcVideoPanel.weightx = 1;
        gbcVideoPanel.weighty = 1;
        gbcVideoPanel.anchor = GridBagConstraints.NORTH;
        gbVideoPanel.setConstraints(spacer1, gbcVideoPanel);
        videoPanel.add(spacer1);

        spacer2 = new JLabel("SPACER");
        spacer2.setForeground(new Color(240, 240, 240));
        gbcVideoPanel.gridx = 2;
        gbcVideoPanel.gridy = 7;
        gbcVideoPanel.gridwidth = 6;
        gbcVideoPanel.gridheight = 1;
        gbcVideoPanel.fill = GridBagConstraints.BOTH;
        gbcVideoPanel.weightx = 1;
        gbcVideoPanel.weighty = 1;
        gbcVideoPanel.anchor = GridBagConstraints.NORTH;
        gbVideoPanel.setConstraints(spacer2, gbcVideoPanel);
        videoPanel.add(spacer2);

        spacer3 = new JLabel("SPACER");
        spacer3.setForeground(new Color(240, 240, 240));
        gbcVideoPanel.gridx = 6;
        gbcVideoPanel.gridy = 8;
        gbcVideoPanel.gridwidth = 2;
        gbcVideoPanel.gridheight = 1;
        gbcVideoPanel.fill = GridBagConstraints.BOTH;
        gbcVideoPanel.weightx = 1;
        gbcVideoPanel.weighty = 1;
        gbcVideoPanel.anchor = GridBagConstraints.NORTH;
        gbVideoPanel.setConstraints(spacer3, gbcVideoPanel);
        videoPanel.add(spacer3);


        window.add(videoPanel);
        window.setLocationRelativeTo(null);
        window.setVisible(true);


    }

    protected void progressWindow() {
        JPanel loadingPanel;
        JFrame window;
        window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //window.setBounds(0,0,200,150);
        window.setResizable(false);

        loadingPanel = new JPanel();
        loadingPanel.setBorder(BorderFactory.createTitledBorder("Status"));
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        loadingPanel.setLayout(gbPanel0);

        loadingBar = new JProgressBar();
        gbcPanel0.gridx = 4;
        gbcPanel0.gridy = 9;
        gbcPanel0.gridwidth = 11;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(loadingBar, gbcPanel0);
        loadingPanel.add(loadingBar);

        //loadingBar.setMaximum(100);
        //loadingBar.setMinimum(0);

        progressLabel = new JLabel("0%");
        gbcPanel0.gridx = 8;
        gbcPanel0.gridy = 10;
        gbcPanel0.gridwidth = 2;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints(progressLabel, gbcPanel0);
        loadingPanel.add(progressLabel);

        window.add(loadingPanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
    //endregion

    //region MyMethods
    protected void makeVideo() {

    }
    protected void makeSave(){
        System.out.println("Cannot Save Image(Error 1)");
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
        main.setIcon(new ImageIcon(im));
    }

    protected String dirChooser(String format) {
        JFileChooser jfc = new JFileChooser();
        int retVal = jfc.showSaveDialog(null);
        String file = jfc.getSelectedFile().getAbsolutePath() + format;
        return file;
    }

    //endregion

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
            videoWindow();
        }
        if(e.getSource() == reset){
            window.dispose();
            new MandelScreen();
        }
        if (e.getSource() == videoConfirm) {
            makeVideo();
            window.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        window.dispose();
        new MandelScreen();
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

    @Override
    public void stateChanged(ChangeEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //endregion
}
