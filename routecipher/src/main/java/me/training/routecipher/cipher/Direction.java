package me.training.routecipher.cipher;

public enum Direction {
    DOWN(0, 1),
    UP(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0);

    private int xChange;
    private int yChange;

    Direction(int xChange, int yChange) {
        this.xChange = xChange;
        this.yChange = yChange;
    }

    public int getXChange() {
        return xChange;
    }

    public int getYChange() {
        return yChange;
    }
}