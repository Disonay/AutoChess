package game.field;

import figures.Figure;
import figures.position.Position;

public class GameField {
    Cell[][] gameField;

    public GameField() {
        gameField = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int ii = 0; ii < 8; ii++) {
                gameField[i][ii] = new Cell();
            }
        }
    }

    public void placeFigure(Figure figure) {
        gameField[figure.getVerticalPosition()][figure.getHorizontalPosition()].setFigure(figure);
    }

    public void dropFigure(Figure figure) {
        gameField[figure.getVerticalPosition()][figure.getHorizontalPosition()].makeEmpty();
    }

    public boolean isEmpty(Position position) {
        return gameField[position.vertical()][position.horizontal()].isEmpty();
    }

    public Figure getFigure(Position position) {
        return gameField[position.vertical()][position.horizontal()].getFigure();
    }

    public void resetField() {
        for (int i = 0; i < 8; i++) {
            for (int ii = 0; i < 8; i++) {
                gameField[i][ii].makeEmpty();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            for (int ii = 0; ii < 8; ii++) {
                stringBuilder.append(gameField[i][ii].toString()).append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
