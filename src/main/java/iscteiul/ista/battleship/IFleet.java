/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Represents a fleet of ships placed on a player's board. A fleet is
 * responsible for building itself up (respecting the placement rules) and for
 * answering queries about the ships it contains.
 */
public interface IFleet {

    /** The side length, in cells, of the (square) board. */
    Integer BOARD_SIZE = 10;

    /** The maximum number of ships a fleet may contain. */
    Integer FLEET_SIZE = 10;

    /**
     * Returns all the ships currently in the fleet.
     *
     * @return the list of ships
     */
    List<IShip> getShips();

    /**
     * Attempts to add a ship to the fleet. The ship is only added when the fleet
     * is not yet full, the ship lies entirely inside the board and it does not
     * touch (collide with) any ship already present.
     *
     * @param s the ship to add
     * @return {@code true} if the ship was successfully added, {@code false} if
     *         it violated any placement rule
     */
    boolean addShip(IShip s);

    /**
     * Returns the ships of the fleet that belong to a given category.
     *
     * @param category the category to match
     * @return the list of ships whose category equals {@code category} (empty if
     *         none match)
     */
    List<IShip> getShipsLike(String category);

    /**
     * Returns the ships of the fleet that are still floating, i.e. that have not
     * yet been completely sunk.
     *
     * @return the list of floating ships
     */
    List<IShip> getFloatingShips();

    /**
     * Returns the ship occupying a given position, if any.
     *
     * @param pos the position to inspect
     * @return the ship at {@code pos}, or {@code null} if no ship occupies it
     */
    IShip shipAt(IPosition pos);

    /**
     * Prints the current state of the fleet, including all its ships, the ships
     * still floating and the ships grouped by category.
     */
    void printStatus();
}
