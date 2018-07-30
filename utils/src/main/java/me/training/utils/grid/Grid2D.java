package me.training.utils.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Grid2D<T> {

    private List<List<T>> tab;

    public Grid2D(int columns, int rows, T defaultValue) {
        tab = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            tab.add(new ArrayList<>(columns));
            for (int j = 0; j < columns; j++) {
                tab.get(i).add(defaultValue);
            }
        }
    }

    public Grid2D(int columns, int rows, T defaultValue, T[] values) {
        int charIndex = 0;
        tab = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            tab.add(new ArrayList<>(columns));
            for (int j = 0; j < columns; j++) {
                if (charIndex >= values.length) {
                    tab.get(i).add(defaultValue);
                } else {
                    tab.get(i).add(values[charIndex]);
                    charIndex++;
                }
            }
        }
    }

    public Grid2D(int columns, int rows, Supplier<T> valueSupplier) {
        tab = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            tab.add(new ArrayList<>(columns));
            for (int j = 0; j < columns; j++) {
                tab.get(i).add(valueSupplier.get());
            }
        }
    }

    public T get(Point point) {
        return tab.get(point.getY()).get(point.getX());
    }

    public T get(int x, int y) {

        return tab.get(y).get(x);
    }

    public void set(Point point, T t) {

        tab.get(point.getY()).set(point.getX(), t);
    }

    public void set(int x, int y, T t) {

        tab.get(y).set(x, t);
    }

    public int getColumnsCount() {
        return tab.get(0).size();
    }

    public int getRowsCount() {
        return tab.size();
    }

    public boolean isInBounds(Point point) {

        return isInBounds(point.getX(), point.getY());
    }

    public List<T> getRow(int y) {
        return tab.get(y);
    }

    public boolean isInBounds(int x, int y) {
        return y < tab.size() && x < tab.get(0).size() && x >= 0 && y >= 0;
    }

    @Override
    public String toString() {
        return tab.stream().map(Object::toString).reduce("", (s1, s2) -> s1 + s2 + '\n');
    }
}
