package figures.position;

public record Position(int vertical, int horizontal) {

    public boolean isValid() {
        return isValidVerticalPosition() && isValidHorizontalPosition();
    }

    public boolean isValidVerticalPosition() {
        return (0 <= vertical) && (vertical < 8);
    }

    public boolean isValidHorizontalPosition() {
        return (0 <= horizontal) && (horizontal < 8);
    }
}
