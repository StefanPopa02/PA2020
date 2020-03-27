import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    //create all buttons (Load, Reset, Exit)
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");


    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        //add all buttons ...TODO
        add(loadBtn);
        add(saveBtn);
        add(resetBtn);
        add(exitBtn);
        //configure listeners for all buttons
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);

    }

    private void save(ActionEvent e) {
        try {
            ImageIO.write(frame.getCanvas().image, "PNG", new File("d:/test.png"));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void load(ActionEvent e) {
        try {
            URL url = new URL("https://programming.codes/wp-content/uploads/2018/12/c-humor-1.jpg");
            BufferedImage img = ImageIO.read(url);
            frame.getCanvas().reset();
            frame.repaint();
            frame.getCanvas().setImage(img);
            frame.getCanvas().paintComponent(img.createGraphics());

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void reset(ActionEvent e) { // de facut
            frame.getCanvas().reset();
            frame.repaint();
    }

    private void exit(ActionEvent e) { // de facut
        frame.dispose();
    }

}