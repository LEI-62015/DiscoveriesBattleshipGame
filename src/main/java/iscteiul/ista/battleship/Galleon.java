
package iscteiul.ista.battleship;

/**
 * Represents a T-shaped galleon that occupies five board positions.
 */

public class Galleon extends Ship {
    /**
     * Number of board positions occupied by a galleon.
     */
    private static final Integer SIZE = 5;

    /**
     * Name used to identify the ship's category.
     */
    private static final String NAME = "Galeao";

    /**
     * Creates a galleon with the given orientation and reference position.
     *
     * @param bearing the ship's orientation; must not be null
     * @param pos the reference position used to calculate the occupied positions;
     *            must not be null
     * @throws IllegalArgumentException if the orientation is unsupported
     * @throws NullPointerException if bearing or pos is null and assertions are disabled
     * @throws AssertionError if bearing or pos is null and assertions are enabled
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * Returns the number of board positions occupied by this galleon.
     *
     * @return the galleon's size, which is always 5
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Adds the five occupied positions for a north-facing galleon.
     *
     * @param pos the reference position used to calculate the occupied positions
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Adds the five occupied positions for a south-facing galleon.
     *
     * @param pos the reference position used to calculate the occupied positions
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Adds the five occupied positions for an east-facing galleon.
     *
     * @param pos the reference position used to calculate the occupied positions
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Adds the five occupied positions for a west-facing galleon.
     *
     * @param pos the reference position used to calculate the occupied positions
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
