package figures;

import figures.position.Position;
import player.Black;
import player.Player;
import player.White;
import game.field.GameField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;


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

    private boolean isQueen() {
        if (player instanceof White) {
            return position.vertical() == 7;
        } else if (player instanceof Black) {
            return position.vertical() == 0;
        }
        return false;
    }

    private void toQueen(GameField gameField) {
        removeThisFigureFromPlayer();
        gameField.dropFigure(this);

        Figure queen = new Queen(position, player);
        player.addFigure(queen);
    }

    @Override
    public void step(GameField gameField) {
        for (Position possiblePosition : getPossiblePositions(gameField)) {
            if (possiblePosition.isValid()) {
                if (!gameField.isEmpty(possiblePosition) &&
                        gameField.getFigure(possiblePosition).isDifferentPlayers(player) &&
                        possiblePosition.horizontal() != getHorizontalPosition()) {
                    gameField.getFigure(possiblePosition).removeThisFigureFromPlayer();
                }
                else if (gameField.isEmpty(possiblePosition) &&
                        possiblePosition.horizontal() != getHorizontalPosition()) {
                    continue;
                }

                gameField.dropFigure(this);
                changePosition(possiblePosition);
                if (isQueen()) {
                    toQueen(gameField);
                }
                gameField.placeFigure(this);
                break;
            }
        }
    }
    @Override
    public boolean canMove(GameField gameField) {
        Predicate<Position> isValid = Position::isValid;
        Predicate<Position> isMoveToFreeCell = x -> gameField.isEmpty(x) && x.horizontal() == getHorizontalPosition();
        Predicate<Position> isMoveToEnemyFigure = x -> !gameField.isEmpty(x) &&
                gameField.getFigure(x).isDifferentPlayers(player) &&
                x.horizontal() != getHorizontalPosition();

        return getPossiblePositions(gameField).stream()
                .anyMatch(isValid.and(isMoveToFreeCell.or(isMoveToEnemyFigure)));
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
