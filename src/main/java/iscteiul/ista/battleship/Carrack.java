/**
 * Represents a Carrack ship in the game.
 * A Carrack occupies three positions depending on its orientation.
 */
package iscteiul.ista.battleship;

public class Carrack extends Ship {
    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Creates a Carrack with a given orientation and starting position.
     *
     * @param bearing the direction the ship is facing
     * @param pos the initial position of the Carrack
     * @throws IllegalArgumentException if the bearing is invalid
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Returns the size of the Carrack.
     *
     * @return the size (3)
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }
}
