package figures;

import figures.position.Position;
import player.Player;
import game.field.GameField;

import java.util.ArrayList;
import java.util.function.Predicate;

public abstract class AbstractFigure implements Figure {

    protected Position position;

    protected Player player;

    AbstractFigure(Position position, Player player) {
        this.position = position;
        this.player = player;
    }

    @Override
    public void step(GameField gameField) {
        for (Position possiblePosition: getPossiblePositions(gameField)) {
            if (possiblePosition.isValid()) {
                gameField.dropFigure(this);
                if (!gameField.isEmpty(possiblePosition) &&
                        gameField.getFigure(possiblePosition).isDifferentPlayers(player)) {
                    gameField.getFigure(possiblePosition).removeThisFigureFromPlayer();
                }
                changePosition(possiblePosition);
                gameField.placeFigure(this);
                break;
            }
        }
    }
    @Override
    public boolean canMove(GameField gameField) {
        Predicate<Position> isValid = Position::isValid;
        Predicate<Position> isMoveToEmptyCell = gameField::isEmpty;
        Predicate<Position> isMoveToEnemyFigure = x -> gameField.getFigure(x).isDifferentPlayers(player);

        return getPossiblePositions(gameField).stream()
                .anyMatch(isValid.and(isMoveToEmptyCell.or(isMoveToEnemyFigure)));
    }

    @Override
    public boolean isDifferentPlayers(Player anotherPlayer) {
        return anotherPlayer != player;
    }

    @Override
    public void removeThisFigureFromPlayer() {
        player.removeFigure(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Figure other) {
            return (this.position.vertical() == other.getVerticalPosition())
                    && (this.position.horizontal() == other.getHorizontalPosition());
        }
        return false;
    }

    @Override
    public int getHorizontalPosition() {
        return position.horizontal();
    }

    @Override
    public int getVerticalPosition() {
        return position.vertical();
    }

    @Override
    public void changePosition(Position position) {
        this.position = position;
    }

    private ArrayList<Position>
    possiblePositionsFromOneDirection(int verticalStep, int horizontalStep, GameField gameField) {
        ArrayList<Position> possiblePositions = new ArrayList<>();

        Position variant = new Position(
                getVerticalPosition() + verticalStep,
                getHorizontalPosition() + horizontalStep);
        while (variant.isValid() && gameField.isEmpty(variant)) {
            possiblePositions.add(variant);
            variant = new Position(
                    variant.vertical() + verticalStep,
                    variant.horizontal() + horizontalStep);
        }
        if (variant.isValid() &&
                !gameField.isEmpty(variant) &&
                gameField.getFigure(variant).isDifferentPlayers(player)) {
            possiblePositions.add(variant);
        }

        return possiblePositions;
    }

    protected ArrayList<Position> possibleUpDirection(GameField gameField) {
        return possiblePositionsFromOneDirection(1, 0, gameField);
    }

    protected ArrayList<Position> possibleDownDirection(GameField gameField) {
        return possiblePositionsFromOneDirection(-1, 0, gameField);
    }

    protected ArrayList<Position> possibleRightDirection(GameField gameField) {
        return possiblePositionsFromOneDirection(0, 1, gameField);
    }

    protected ArrayList<Position> possibleLeftDirection(GameField gameField) {
        return possiblePositionsFromOneDirection(0, -1, gameField);
    }

    protected ArrayList<Position> possibleUpRightDirection(GameField gameField) {
        return possiblePositionsFromOneDirection(1, 1, gameField);
    }

    protected ArrayList<Position> possibleUpLeftDirection(GameField gameField) {
        return possiblePositionsFromOneDirection(1, -1, gameField);
    }

    protected ArrayList<Position> possibleDownRightDirection(GameField gameField) {
        return possiblePositionsFromOneDirection(-1, 1, gameField);
    }

    protected ArrayList<Position> possibleDownLeftDirection(GameField gameField) {
        return possiblePositionsFromOneDirection(-1, -1, gameField);
    }
}
