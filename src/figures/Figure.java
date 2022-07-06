package figures;

import figures.position.Position;
import player.Player;
import game.field.GameField;

import java.util.ArrayList;

public interface Figure {
    boolean canMove(GameField gameField);

    int getHorizontalPosition();

    int getVerticalPosition();

    boolean isDifferentPlayers(Player anotherPlayer);

    void changePosition(Position position);

    ArrayList<Position> getPossiblePositions(GameField gameField);

    void step(GameField gameField);

    void removeThisFigureFromPlayer();
}
