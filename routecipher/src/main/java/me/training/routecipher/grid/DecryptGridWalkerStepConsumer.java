package me.training.routecipher.grid;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DecryptGridWalkerStepConsumer implements GridWalkerResultConsumer {

    private Queue<Point> fullPath;
    private String encryptedText;
    private int columns;
    private int rows;

    public DecryptGridWalkerStepConsumer(String encryptedText, int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.fullPath = new ArrayDeque<>();
        this.encryptedText = encryptedText;
    }

    @Override
    public String result() {
        List<Character> encryptedTextList = List.of(ArrayUtils.toObject(encryptedText.toCharArray()));
        Grid2D<Character> characterGrid2D = new Grid2D<>(columns, rows, '?');
        encryptedTextList.forEach(character -> characterGrid2D.set(fullPath.poll(), character));
        return IntStream.range(0, rows).mapToObj(r -> StringUtils.join(characterGrid2D.getRow(r), StringUtils.EMPTY))
                .collect(Collectors.joining());
    }

    @Override
    public void accept(Point point) {
        fullPath.add(point);
    }
}


