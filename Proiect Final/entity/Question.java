package sample.entity;

public class Question {
    private String imagine;
    private String varianta1;
    private String varianta2;
    private String varianta3;
    private String varianta4;
    private String correctAnswer;

    public Question(String imagine, String correctAnswer, String varianta1, String varianta2, String varianta3, String varianta4) {
        this.imagine = imagine;
        this.varianta1 = varianta1;
        this.varianta2 = varianta2;
        this.varianta3 = varianta3;
        this.varianta4 = varianta4;
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getImagine() {
        return imagine;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }

    public String getVarianta1() {
        return varianta1;
    }

    public void setVarianta1(String varianta1) {
        this.varianta1 = varianta1;
    }

    public String getVarianta2() {
        return varianta2;
    }

    public void setVarianta2(String varianta2) {
        this.varianta2 = varianta2;
    }

    public String getVarianta3() {
        return varianta3;
    }

    public void setVarianta3(String varianta3) {
        this.varianta3 = varianta3;
    }

    public String getVarianta4() {
        return varianta4;
    }

    public void setVarianta4(String varianta4) {
        this.varianta4 = varianta4;
    }

    @Override
    public String toString() {
        return "Question{" +
                "imagine='" + imagine + '\'' +
                ", varianta1='" + varianta1 + '\'' +
                ", varianta2='" + varianta2 + '\'' +
                ", varianta3='" + varianta3 + '\'' +
                ", varianta4='" + varianta4 + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
