/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Represents a ship placed on the board. A ship is defined by its category,
 * its size, its bearing (orientation) and the set of positions it occupies,
 * and it knows how to answer questions about its own state and location.
 */
public interface IShip {

    /**
     * Returns the category (kind) of this ship, such as galleon, frigate,
     * carrack, caravel or barge.
     *
     * @return the ship's category
     */
    String getCategory();

    /**
     * Returns the size of this ship, i.e. the number of cells it occupies.
     *
     * @return the number of cells occupied by the ship
     */
    Integer getSize();

    /**
     * Returns all the positions occupied by this ship.
     *
     * @return the list of occupied positions
     */
    List<IPosition> getPositions();

    /**
     * Returns the reference (origin) position of this ship, from which it was
     * built.
     *
     * @return the ship's reference position
     */
    IPosition getPosition();

    /**
     * Returns the bearing (orientation) of this ship.
     *
     * @return the ship's bearing
     */
    Compass getBearing();

    /**
     * Indicates whether the ship is still floating. A ship is floating while at
     * least one of its positions has not yet been hit.
     *
     * @return {@code true} if the ship still has an unhit position,
     *         {@code false} once every position has been hit
     */
    boolean stillFloating();

    /**
     * Returns the smallest row index occupied by this ship.
     *
     * @return the top-most row occupied by the ship
     */
    int getTopMostPos();

    /**
     * Returns the largest row index occupied by this ship.
     *
     * @return the bottom-most row occupied by the ship
     */
    int getBottomMostPos();

    /**
     * Returns the smallest column index occupied by this ship.
     *
     * @return the left-most column occupied by the ship
     */
    int getLeftMostPos();

    /**
     * Returns the largest column index occupied by this ship.
     *
     * @return the right-most column occupied by the ship
     */
    int getRightMostPos();

    /**
     * Checks whether this ship occupies the given position.
     *
     * @param pos the position to test
     * @return {@code true} if the ship occupies {@code pos}, {@code false}
     *         otherwise
     */
    boolean occupies(IPosition pos);

    /**
     * Checks whether this ship is too close to another ship. Two ships are too
     * close when any position of the other ship is adjacent to a position of
     * this ship, which is not allowed when placing a fleet.
     *
     * @param other the other ship
     * @return {@code true} if the ships are too close (touching), {@code false}
     *         otherwise
     */
    boolean tooCloseTo(IShip other);

    /**
     * Checks whether the given position is too close to this ship, i.e. adjacent
     * to any of the positions the ship occupies.
     *
     * @param pos the position to test
     * @return {@code true} if the position is adjacent to the ship,
     *         {@code false} otherwise
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Registers a shot against this ship. If the given position belongs to the
     * ship, the matching position is marked as hit; otherwise the call has no
     * effect.
     *
     * @param pos the position being shot at
     */
    void shoot(IPosition pos);
}
