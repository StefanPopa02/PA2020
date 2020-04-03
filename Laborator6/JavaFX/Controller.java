package sample;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;


public class Controller {
    @FXML
    private Button buttonLoad;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonReset;
    @FXML
    private Button buttonExit;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Spinner spinner;
    @FXML
    private Slider slider;
    @FXML
    private ComboBox comboBox;
    @FXML
    private CheckBox checkBox;
    GraphicsContext gc;

    public void initialize() {
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);
        canvas.setOnMouseClicked(e -> {
            drawShape(e.getX(), e.getY(), gc);
        });
        canvas.setOnMouseDragged(e -> {
            drawShape(e.getX(), e.getY(), gc);
        });
    }

    private void drawShape(double x, double y, GraphicsContext gc) {
        int radius = (int) slider.getValue();
        int sides = (int) spinner.getValue();
        String chosenShape = (String) comboBox.getValue();
        switch (chosenShape) {
            case "Standard":
                //folosim valorile alese
                break;
            case "Octogon":
                sides=8;
                break;
            case "Dodecagon":
                sides=12;
                break;
        }
        if (checkBox.isSelected()) {
            gc.setFill(Color.WHITE);
            gc.fillRect(x, y, radius, radius);
        } else {
            gc.setFill(colorPicker.getValue());
            gc.fillPolygon(getXCoord(x, radius, sides), getYCoord(y, radius, sides), sides);
        }

    }

    private double[] getXCoord(double x0, int radius, int sides) {
        double[] xPoints = new double[sides];
        double alpha = 2 * Math.PI / sides;
        for (int i = 0; i < sides; i++) {
            double x = x0 + radius * Math.cos(alpha * i);
            xPoints[i] = x;
        }
        return xPoints;
    }

    private double[] getYCoord(double y0, int radius, int sides) {
        double[] yPoints = new double[sides];
        double alpha = 2 * Math.PI / sides;
        for (int i = 0; i < sides; i++) {
            double y = y0 + radius * Math.sin(alpha * i);
            yPoints[i] = y;
        }
        return yPoints;
    }

    @FXML
    public void onButtonClicked(ActionEvent event) {
        if (event.getSource().equals(buttonLoad)) {
            System.out.println("Button Load was pressed");
            FileChooser fileChooser= new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            if(file!=null){
                Image image1 = new Image(file.toURI().toString());
                gc.drawImage(image1,0,0);
            }
        } else if (event.getSource().equals(buttonSave)) {
            System.out.println("Button Save was pressed");
            Image savedImg = canvas.snapshot(null,null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
            );
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("GIF", "*.gif"),
                    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            File file1 = fileChooser.showSaveDialog(null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(savedImg,null),"png",file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource().equals(buttonReset)) {
            System.out.println("Button Reset was pressed");
            initialize();
        } else if (event.getSource().equals(buttonExit)) {
            System.out.println("Button Exit was pressed");
            Platform.exit();
        }
    }
}
