/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Represents a game played against a single fleet. A game keeps track of the
 * shots that have been fired and of the statistics gathered during play, such
 * as hits, sunk ships and invalid or repeated shots.
 */
public interface IGame {

    /**
     * Fires a shot at the given position. A shot outside the board is counted as
     * invalid and a shot at an already targeted position is counted as repeated;
     * in both cases the shot has no further effect. A valid, new shot is
     * registered and, when it lands on a ship, that ship is marked as hit.
     *
     * @param pos the target position
     * @return the ship sunk by this shot, or {@code null} if no ship was sunk
     *         (including on misses, invalid shots and repeated shots)
     */
    IShip fire(IPosition pos);

    /**
     * Returns the valid positions that have already been fired at.
     *
     * @return the list of valid shots
     */
    List<IPosition> getShots();

    /**
     * Returns how many shots were fired at a position that had already been
     * targeted.
     *
     * @return the number of repeated shots
     */
    int getRepeatedShots();

    /**
     * Returns how many shots were fired outside the limits of the board.
     *
     * @return the number of invalid shots
     */
    int getInvalidShots();

    /**
     * Returns how many shots have hit a ship.
     *
     * @return the number of hits
     */
    int getHits();

    /**
     * Returns how many ships have been sunk so far.
     *
     * @return the number of sunk ships
     */
    int getSunkShips();

    /**
     * Returns how many ships of the fleet are still floating.
     *
     * @return the number of remaining ships
     */
    int getRemainingShips();

    /**
     * Prints the board marking all the valid shots fired so far.
     */
    void printValidShots();

    /**
     * Prints the board showing the positions occupied by the fleet.
     */
    void printFleet();
}
