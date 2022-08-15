package figures;

import figures.position.Position;
import player.*;
import game.field.GameField;

import java.util.ArrayList;
import java.util.Collections;

public class Rook extends AbstractFigure {
    public Rook(Position position, Player player) {
        super(position, player);
    }

    @Override
    public ArrayList<Position> getPossiblePositions(GameField gameField) {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        possiblePositions.addAll(possibleUpDirection(gameField));
        possiblePositions.addAll(possibleDownDirection(gameField));
        possiblePositions.addAll(possibleRightDirection(gameField));
        possiblePositions.addAll(possibleLeftDirection(gameField));

        Collections.shuffle(possiblePositions);

        return possiblePositions;
    }

    @Override
    public String toString() {
        if (player instanceof White) {
            return "\u2656";
        } else {
            return "\u265c";
        }
    }
}
