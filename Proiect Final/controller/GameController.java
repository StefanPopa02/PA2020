package sample.controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.entity.GameSettings;
import sample.entity.Question;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GameController {

    @FXML
    private ImageView imgView;

    @FXML
    private Button gameButton1;

    @FXML
    private Button gameButton3;

    @FXML
    private Button gameButton2;

    @FXML
    private Button gameButton4;

    private int nrOfRounds = 0;
    private List<String> images;
    private List<String> chosenOptions;
    private List<String> questionsUsed;
    private Question currentQuestion;
    private List<Button> buttons;
    private PointsController pointsController;
    private String path = "D:\\Cursuri\\Anul II\\Semestrul II\\Programare avansata\\FinalProject\\images\\";

    @FXML
    public void initialize() {
        pointsController = new PointsController();
        buttons = new ArrayList<>();
        buttons.add(gameButton1);
        buttons.add(gameButton2);
        buttons.add(gameButton3);
        buttons.add(gameButton4);
        checkAnswer();
        questionsUsed = new ArrayList<>();
        path += GameSettings.getCategory();
        File file = new File(path);
        loadImages(file);
        nextQuestion();
    }

    public void checkAnswer() {
        EventHandler<ActionEvent> buttonHandler = event -> {
            Button btnPressed = ((Button) event.getSource());
            if (btnPressed.getText().equals(currentQuestion.getCorrectAnswer())) {
                pointsController.correctAnswer();
                btnPressed.getStyleClass().add("addButtonTrue");
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
                pauseTransition.setOnFinished(event1 -> {
                    takeAwayAllEffects();
                    nrOfRounds++;
                    nextQuestion();
                });
                pauseTransition.play();
            } else {
                pointsController.wrongAnswer();
                btnPressed.getStyleClass().add("addButtonFalse");

                List<Button> remainingButtons = buttons
                        .stream()
                        .filter(btn -> !(btn.equals(btnPressed)))
                        .collect(Collectors.toList());

                showCorrectAnswer(remainingButtons);
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
                pauseTransition.setOnFinished(event1 -> {
                    takeAwayAllEffects();
                    nrOfRounds++;
                    nextQuestion();
                });
                pauseTransition.play();
            }
        };
        gameButton1.setOnAction(buttonHandler);
        gameButton2.setOnAction(buttonHandler);
        gameButton3.setOnAction(buttonHandler);
        gameButton4.setOnAction(buttonHandler);
    }

    private void showCorrectAnswer(List<Button> buttons) {
        buttons
                .stream()
                .filter(btn -> currentQuestion.getCorrectAnswer().equals(btn.getText()))
                .map(btn -> {
                    btn.getStyleClass().add("addButtonTrue");
                    return true;
                })
                .count();
    }

    private void takeAwayAllEffects() {
        gameButton1.getStyleClass().clear();
        gameButton1.getStyleClass().add("button");
        gameButton2.getStyleClass().clear();
        gameButton2.getStyleClass().add("button");
        gameButton3.getStyleClass().clear();
        gameButton3.getStyleClass().add("button");
        gameButton4.getStyleClass().clear();
        gameButton4.getStyleClass().add("button");
    }

    private void nextQuestion() {
        if (nrOfRounds < 10) {
            currentQuestion = generateQuestion();
            updateQuestion(currentQuestion);
        }else{
            goToGameOver();
        }
    }

    private void updateQuestion(Question question) {
        File carImg = new File(path + "\\" + question.getImagine());
        Image image = new Image(carImg.toURI().toString(), 705, 400, false, false);
        imgView.setImage(image);

        gameButton1.setText(question.getVarianta1());
        gameButton2.setText(question.getVarianta2());
        gameButton3.setText(question.getVarianta3());
        gameButton4.setText(question.getVarianta4());
    }

    private Question generateQuestion() {
        chosenOptions = getRandomElement(new ArrayList<>(images));
        String carImage = chosenOptions.get(0);
        questionsUsed.add(carImage);
        String correctAnswer = carImage.replaceAll(".jpg", "").replaceAll(".png", "");
        Collections.shuffle(chosenOptions);
        String varianta1 = chosenOptions.get(0).replaceAll(".jpg", "").replaceAll(".png", "");
        String varianta2 = chosenOptions.get(1).replaceAll(".jpg", "").replaceAll(".png", "");
        String varianta3 = chosenOptions.get(2).replaceAll(".jpg", "").replaceAll(".png", "");
        String varianta4 = chosenOptions.get(3).replaceAll(".jpg", "").replaceAll(".png", "");
        return new Question(carImage, correctAnswer, varianta1, varianta2, varianta3, varianta4);
    }

    private void loadImages(final File directory) {
        if (images == null) {
            images = new ArrayList<>();
        } else {
            images.clear();
        }

        File[] files = directory.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                loadImages(f);
            } else {
                images.add(f.getName());
            }
        }
    }

    /***
     *
     * @param list = Lista cu toate optiunile posibile
     * @return Lista cu cele 4 variante posibile, pe pozitia 0 se afla raspunsul corect
     */
    public List<String> getRandomElement(List<String> list) {
        Random rand = new Random();
        int totalItems = 4;
        int randomIndex = 0;
        List<String> newList;
        do {
            newList = new ArrayList<>();
            for (int i = 0; i < totalItems; i++) {
                randomIndex = rand.nextInt(list.size());
                newList.add(list.get(randomIndex));
                list.remove(randomIndex);
            }
        } while (questionsUsed.contains(newList.get(0)));
        return newList;
    }

    @FXML
    public void goToGameOver() {
        gameButton1.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/gameOver.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        GameOverController gameOverController = loader.getController();
        System.out.println("Puncte acumulate "+pointsController);
        gameOverController.transferPoints(pointsController);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
