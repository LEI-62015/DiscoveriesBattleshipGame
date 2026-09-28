
package iscteiul.ista.battleship;

/**
 * Represents a frigate occupying four consecutive positions
 * in a row or column of the board.
 */
public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Creates a frigate starting at the specified position.
     * NORTH and SOUTH use increasing row indices;
     * EAST and WEST use increasing column indices.
     *
     * @param bearing the frigate's orientation
     * @param pos the frigate's starting position
     * @throws IllegalArgumentException if the orientation is unsupported
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /**
     * Returns the number of positions occupied by the frigate.
     *
     * @return the frigate's size, always 4
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}
