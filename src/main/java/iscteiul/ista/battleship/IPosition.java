/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Represents a single cell of the board, identified by its row and column.
 * A position also keeps track of whether it is occupied by a ship and whether
 * it has already been hit by a shot.
 *
 * @author fba
 */
public interface IPosition {

    /**
     * Returns the row (line) index of this position.
     *
     * @return the row index
     */
    int getRow();

    /**
     * Returns the column index of this position.
     *
     * @return the column index
     */
    int getColumn();

    /**
     * Compares this position with another object. Two positions are considered
     * equal when they share the same row and column, regardless of their
     * occupied or hit state.
     *
     * @param other the object to compare with
     * @return {@code true} if {@code other} is a position with the same
     *         coordinates, {@code false} otherwise
     */
    boolean equals(Object other);

    /**
     * Checks whether another position is adjacent to this one. A position is
     * adjacent when it lies within one cell in any direction (horizontally,
     * vertically or diagonally).
     *
     * @param other the position to test
     * @return {@code true} if the two positions are adjacent, {@code false}
     *         otherwise
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marks this position as occupied by a ship.
     */
    void occupy();

    /**
     * Marks this position as having been hit by a shot.
     */
    void shoot();

    /**
     * Indicates whether this position is occupied by a ship.
     *
     * @return {@code true} if the position is occupied, {@code false} otherwise
     */
    boolean isOccupied();

    /**
     * Indicates whether this position has already been hit.
     *
     * @return {@code true} if the position has been hit, {@code false} otherwise
     */
    boolean isHit();
}
