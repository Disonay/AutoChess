package player;

import figures.*;
import figures.position.Position;

import java.util.ArrayList;

public class White extends AbstractPlayer{
    public White() {
        figures = new ArrayList<>();
    }
    public void resetFigures() {
        figures.clear();
        for (int i = 0; i < 8; i++) {
            figures.add(new Pawn(new Position(1, i), this));
        }
        figures.add(new Rook(new Position(0, 0), this));
        figures.add(new Horse(new Position(0, 1), this));
        figures.add(new Eliphant(new Position(0, 2), this));
        figures.add(new Queen(new Position(0, 3), this));
        figures.add(new King(new Position(0, 4), this));
        figures.add(new Eliphant(new Position(0, 5), this));
        figures.add(new Horse(new Position(0, 6), this));
        figures.add(new Rook(new Position(0, 7), this));
    }
}
