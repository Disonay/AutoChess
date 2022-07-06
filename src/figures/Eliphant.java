package figures;

import figures.position.Position;
import player.*;
import game.field.GameField;

import java.util.ArrayList;
import java.util.Collections;

public class Eliphant extends AbstractFigure {

    public Eliphant(Position position, Player player) {
        super(position, player);
    }

    @Override
    public ArrayList<Position> getPossiblePositions(GameField gameField) {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        possiblePositions.addAll(possiblePositionsFromOneDirection(1, 1, gameField));
        possiblePositions.addAll(possiblePositionsFromOneDirection(1, -1, gameField));
        possiblePositions.addAll(possiblePositionsFromOneDirection(-1, 1, gameField));
        possiblePositions.addAll(possiblePositionsFromOneDirection(-1, -1, gameField));

        Collections.shuffle(possiblePositions);

        return possiblePositions;
    }

    @Override
    public String toString() {
        if (player instanceof White) {
            return "\u2657";
        } else {
            return "\u265d";
        }
    }
}
