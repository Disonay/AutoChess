package player;

import figures.Figure;
import game.field.GameField;

import java.util.ArrayList;

public interface Player {
    void resetFigures();
    ArrayList<Figure> getAvailableFigures();
    void step(GameField gameField);

    boolean haveSteps(GameField gameField);

    void removeFigure(Figure figure);

    void addFigure(Figure figure);
}
