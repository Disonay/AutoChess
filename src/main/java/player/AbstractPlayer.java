package player;

import figures.Figure;
import game.field.GameField;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractPlayer implements Player{
    ArrayList<Figure> figures;

    @Override
    public ArrayList<Figure> getAvailableFigures() {
        return figures;
    }

    @Override
    public boolean haveSteps(GameField gameField) {
        return figures.stream().anyMatch(x -> x.canMove(gameField));
    }

    @Override
    public void step(GameField gameField) {
        Collections.shuffle(figures);
        for (Figure figure: figures) {
            if (figure.canMove(gameField)) {
                figure.step(gameField);
                break;
            }
        }
    }

    @Override
    public void removeFigure(Figure figure) {
        figures.remove(figure);
    }

    @Override
    public void addFigure(Figure figure) {
        figures.add(figure);
    }
}
