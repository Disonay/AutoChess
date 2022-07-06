package figures;

import figures.position.Position;
import player.*;
import game.field.GameField;

import java.util.ArrayList;
import java.util.Collections;

public class King extends AbstractFigure{

    public King(Position position, Player player) {
        super(position, player);
    }

    @Override
    public ArrayList<Position> getPossiblePositions(GameField gameField) {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        possiblePositions.add(new Position(getVerticalPosition()+1, getHorizontalPosition()));
        possiblePositions.add(new Position(getVerticalPosition()-1, getHorizontalPosition()));
        possiblePositions.add(new Position(getVerticalPosition(), getHorizontalPosition() + 1));
        possiblePositions.add(new Position(getVerticalPosition(), getHorizontalPosition() - 1));
        possiblePositions.add(new Position(getVerticalPosition()+1, getHorizontalPosition() + 1));
        possiblePositions.add(new Position(getVerticalPosition()+1, getHorizontalPosition() - 1));
        possiblePositions.add(new Position(getVerticalPosition()-1, getHorizontalPosition() -1));
        possiblePositions.add(new Position(getVerticalPosition()-1, getHorizontalPosition() + 1));

        Collections.shuffle(possiblePositions);

        return possiblePositions;
    }

    @Override
    public String toString() {
        if (player instanceof White) {
            return "\u2654";
        } else {
            return "\u265a";
        }
    }
}
