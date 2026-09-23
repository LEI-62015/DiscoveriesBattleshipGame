/**
 * Enumeration representing possible directions (bearings) in the game.
 * <p>
 * Each direction is associated with a character representation.
 */
package iscteiul.ista.battleship;

/**
 * @author fba
 */
public enum Compass {
    NORTH('n'), SOUTH('s'), EAST('e'), WEST('o'), UNKNOWN('u');

    private final char c;

    /**
     * Constructs a Compass direction.
     *
     * @param c character representing the direction
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Returns the character associated with this direction.
     *
     * @return direction character
     */
    public char getDirection() {
        return c;
    }

    /**
     * Returns the string representation of the direction.
     *
     * @return string with the direction character
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converts a character to a Compass value.
     *
     * @param ch character representing direction
     * @return corresponding Compass value or UNKNOWN if invalid
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }
        return bearing;
    }
}
