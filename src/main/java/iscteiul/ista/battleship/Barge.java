/**
 * Represents a Barge ship in the game.
 * <p>
 * A Barge is the smallest type of ship and occupies a single position on the board.
 */
package iscteiul.ista.battleship;

public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Creates a Barge with a given orientation and initial position.
     * Since the Barge has size 1, it occupies only the given position.
     *
     * @param bearing the direction the ship is facing
     * @param pos the initial position of the barge
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Returns the size of the Barge.
     *
     * @return the size (1)
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}
