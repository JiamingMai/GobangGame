package jm.app;

public enum Chessman {
    BLACK("●"), WHITE("○");
    private String chessman;
    private Chessman(String chessman) {
        this.chessman = chessman;
    }
    public String getChessman() {
        return this.chessman;
    }
}
