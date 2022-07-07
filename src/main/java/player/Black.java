package player;

import figures.*;
import figures.position.Position;

import java.util.ArrayList;

public class Black extends AbstractPlayer {
    public Black() {
        figures = new ArrayList<>();
    }
    public void resetFigures() {
        figures.clear();
        for (int i = 0; i < 8; i++) {
            figures.add(new Pawn(new Position(6, i), this));
        }
        figures.add(new Rook(new Position(7, 0), this));
        figures.add(new Horse(new Position(7, 1), this));
        figures.add(new Eliphant(new Position(7, 2), this));
        figures.add(new Queen(new Position(7, 3), this));
        figures.add(new King(new Position(7, 4), this));
        figures.add(new Eliphant(new Position(7, 5), this));
        figures.add(new Horse(new Position(7, 6), this));
        figures.add(new Rook(new Position(7, 7), this));
    }
}
