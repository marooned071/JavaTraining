package me.training.routecipher.grid;

import me.training.routecipher.cipher.Direction;

import java.util.Objects;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Point copy() {
        return new Point(this.x, this.y);
    }

    public static Point of(int x, int y){
        return new Point(x, y);
    }

    public Point move(Direction direction) {
        return new Point(x + direction.getXChange(), y + direction.getYChange());
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + ']';
    }
}
