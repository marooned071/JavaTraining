package me.training.colorgrid;

import me.training.utils.grid.Point;
import org.apache.commons.lang3.tuple.Pair;

public interface ColorGrid {
    Pair<Point, Integer> getLargestRegion();
}
