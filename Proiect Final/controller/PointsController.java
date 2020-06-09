package sample.controller;

public class PointsController {
    private Integer points;
    private Integer correctAnswers;
    private Integer wrongAnswers;

    public PointsController() {
        this.points = 0;
        this.correctAnswers = 0;
        this.wrongAnswers = 0;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Integer getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(Integer wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void correctAnswer(){
        points+=5;
        this.correctAnswers++;
    }

    public void wrongAnswer(){
        points-=5;
        this.wrongAnswers++;
    }

    @Override
    public String toString() {
        return "PointsController{" +
                "points=" + points +
                '}';
    }
}
