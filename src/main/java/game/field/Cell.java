package game.field;

import figures.Figure;

import java.util.Objects;

public class Cell {
    Figure figure = null;
    public boolean isEmpty(){
        return Objects.isNull(figure);
    }

    public Figure getFigure(){
        return figure;
    }

    public void setFigure(Figure figure){
        this.figure = figure;
    }

    public void makeEmpty(){
        figure = null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "\u8865";
        }
        else {
            return getFigure().toString();
        }
    }
}
