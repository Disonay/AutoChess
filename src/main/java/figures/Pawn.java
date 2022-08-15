package figures;

import figures.position.Position;
import player.Black;
import player.Player;
import player.White;
import game.field.GameField;

import java.util.ArrayList;
import java.util.Collections;


public class Pawn extends AbstractFigure {
    public Pawn(Position position, Player player) {
        super(position, player);
    }

    public ArrayList<Position> getPossiblePositions(GameField gameField) {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        if (player instanceof White) {
            possiblePositions.add(new Position(getVerticalPosition() + 1, getHorizontalPosition()));
            possiblePositions.add(new Position(getVerticalPosition() + 1, getHorizontalPosition() - 1));
            possiblePositions.add(new Position(getVerticalPosition() + 1, getHorizontalPosition() + 1));
        } else {
            possiblePositions.add(new Position(getVerticalPosition() - 1, getHorizontalPosition()));
            possiblePositions.add(new Position(getVerticalPosition() - 1, getHorizontalPosition() - 1));
            possiblePositions.add(new Position(getVerticalPosition() - 1, getHorizontalPosition() + 1));
        }
        Collections.shuffle(possiblePositions);

        return possiblePositions;
    }

    private boolean isQueen(Position newPosition) {
        if (player instanceof White) {
            return newPosition.vertical() == 7;
        } else if (player instanceof Black) {
            return newPosition.vertical() == 0;
        }
        return false;
    }

    private void toQueen(Position newPosition, GameField gameField) {
        gameField.getFigure(position).removeThisFigureFromPlayer();
        Figure queen = new Queen(newPosition, player);
        player.addFigure(queen);
        gameField.placeFigure(queen);
        gameField.dropFigure(this);
    }

    @Override
    public void step(GameField gameField) {
        for (Position possiblePosition : getPossiblePositions(gameField)) {
            if (possiblePosition.isValid()) {
                if (gameField.isEmpty(possiblePosition) && possiblePosition.horizontal() == getHorizontalPosition()) {
                    if (isQueen(possiblePosition)) {
                        toQueen(possiblePosition, gameField);
                    } else {
                        gameField.dropFigure(this);
                        changePosition(possiblePosition);
                        gameField.placeFigure(this);
                    }
                    break;
                } else if (!gameField.isEmpty(possiblePosition) &&
                        gameField.getFigure(possiblePosition).isDifferentPlayers(player) &&
                        possiblePosition.horizontal() != getHorizontalPosition()) {
                    if (isQueen(possiblePosition)) {
                        toQueen(possiblePosition, gameField);
                    } else {
                        gameField.dropFigure(this);
                        changePosition(possiblePosition);
                        gameField.getFigure(possiblePosition).removeThisFigureFromPlayer();
                        gameField.placeFigure(this);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public boolean canMove(GameField gameField) {
        return getPossiblePositions(gameField).stream().anyMatch(x -> x.isValid() &&
                ((gameField.isEmpty(x) && x.horizontal() == getHorizontalPosition()) ||
                        (!gameField.isEmpty(x) &&
                                gameField.getFigure(x).isDifferentPlayers(player) &&
                                x.horizontal() != getHorizontalPosition())));
    }

    @Override
    public String toString() {
        if (player instanceof White) {
            return "\u2659";
        } else {
            return "\u265f";
        }
    }
}
