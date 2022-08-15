package figures;

import figures.position.Position;
import player.*;
import game.field.GameField;

import java.util.ArrayList;
import java.util.Collections;

public class Horse extends AbstractFigure {

    public Horse(Position position, Player player) {
        super(position, player);
    }

    @Override
    public ArrayList<Position> getPossiblePositions(GameField gameField) {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        possiblePositions.add(new Position(getVerticalPosition()+2, getHorizontalPosition()+ 1));
        possiblePositions.add(new Position(getVerticalPosition()+2, getHorizontalPosition() -1));
        possiblePositions.add(new Position(getVerticalPosition()+1, getHorizontalPosition() + 2));
        possiblePositions.add(new Position(getVerticalPosition()+1, getHorizontalPosition() - 2));
        possiblePositions.add(new Position(getVerticalPosition()-1, getHorizontalPosition() + 2));
        possiblePositions.add(new Position(getVerticalPosition()-1, getHorizontalPosition() - 2));
        possiblePositions.add(new Position(getVerticalPosition()-2, getHorizontalPosition() + 1));
        possiblePositions.add(new Position(getVerticalPosition()-2, getHorizontalPosition() - 1));

        Collections.shuffle(possiblePositions);

        return possiblePositions;
    }

    @Override
    public String toString() {
        if (player instanceof White) {
            return "\u2658";
        } else {
            return "\u265e";
        }
    }
}
