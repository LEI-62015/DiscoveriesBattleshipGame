/**
 * Represents a Caravel ship in the game.
 * A Caravel occupies two positions depending on its orientation.
 */
package iscteiul.ista.battleship;

public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Creates a Caravel with a given orientation and starting position.
     *
     * @param bearing the direction the Caravel is facing
     * @param pos the initial position of the Caravel
     * @throws NullPointerException if bearing is null
     * @throws IllegalArgumentException if bearing is invalid
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }
    }

    /**
     * Returns the size of the Caravel.
     *
     * @return the size (2)
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}
