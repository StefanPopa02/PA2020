public class Token implements Comparable<Token> {
    private Integer number;

    public Token(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public int compareTo(Token token) {
        return this.number.compareTo(token.number);
    }
}
