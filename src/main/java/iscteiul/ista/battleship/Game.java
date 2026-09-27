
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a fleet, records shots and provides game statistics
 * and text representations of the board.
 *
 * @author fba
 */
public class Game implements IGame {
    /**
     * Fleet targeted by the shots.
     */
    private IFleet fleet;
    /**
     * Recorded shot positions, excluding invalid and repeated attempts.
     */
    private List<IPosition> shots;

    /**
     * Number of rejected shot attempts.
     */
    private Integer countInvalidShots;
    /**
     * Number of attempts to fire at an already recorded position.
     */
    private Integer countRepeatedShots;
    /**
     * Hit counter, currently left uninitialized by the constructor.
     */
    private Integer countHits;
    /**
     * Sunk ship counter, currently left uninitialized by the constructor.
     */
    private Integer countSinks;


    /**
     * Creates a game with the given fleet and an empty shot list.
     * Initializes the invalid and repeated shot counters to zero.
     *
     * <p>The hit and sunk ship counters are currently left uninitialized.</p>
     *
     * @param fleet the fleet targeted by the shots; must not be null
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Processes a shot at the given position.
     * Counts invalid or repeated attempts without adding them to the shot list.
     * Records new valid shots and applies damage to any ship at that position.
     *
     * <p>The current implementation throws an exception when incrementing
     * the uninitialized hit counter after a ship is hit.</p>
     *
     * @param pos the target position; must not be null
     * @return the ship sunk by this shot, or null if the method completes
     *         without sinking a ship
     * @throws NullPointerException if pos is null, the fleet is needed but null,
     *         or an uninitialized counter is incremented
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns the internal list of recorded shot positions.
     * Changes to the returned list affect the game's shot history.
     *
     * @return the mutable list of recorded shots
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Returns the number of repeated shot attempts.
     *
     * @return the number of attempts at already recorded positions
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Returns the number of shot attempts rejected by the coordinate check.
     *
     * @return the number of invalid shot attempts
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Returns the value of the hit counter.
     *
     * @return the number of hits
     * @throws NullPointerException if the hit counter is uninitialized,
     *         as it is in the current constructor
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Returns the value of the sunk ship counter.
     *
     * @return the number of sunk ships
     * @throws NullPointerException if the sunk ship counter is uninitialized,
     *         as it is in the current constructor
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Returns the number of ships in the fleet that are still floating.
     *
     * @return the number of remaining ships
     * @throws NullPointerException if the fleet is null
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Checks whether both coordinates are between zero and
     * {@link Fleet#BOARD_SIZE}, inclusive.
     *
     * <p>The current check accepts BOARD_SIZE, although this value is outside
     * the array bounds used by printBoard.</p>
     *
     * @param pos the position to check; must not be null
     * @return true if both coordinates are within the accepted range;
     *         false otherwise
     * @throws NullPointerException if pos is null
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Checks whether the given position is already in the shot list.
     *
     * @param pos the position to check
     * @return true if the position matches a recorded shot; false otherwise
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }


    /**
     * Prints a board to standard output using the given marker at the
     * supplied positions and a dot at all other positions.
     *
     * @param positions the positions to mark; the list and its elements
     *                  must not be null
     * @param marker the character used to mark each supplied position;
     *               must not be null
     * @throws NullPointerException if positions or any of its elements is null,
     *         or marker is null when a position is marked
     * @throws ArrayIndexOutOfBoundsException if a coordinate is negative
     *         or greater than or equal to Fleet.BOARD_SIZE
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }


    /**
     * Prints the recorded shots using 'X' for shot positions
     * and '.' for all other positions.
     *
     * @throws ArrayIndexOutOfBoundsException if a recorded position falls
     *         outside the array bounds, including a coordinate equal
     *         to Fleet.BOARD_SIZE
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }


    /**
     * Prints all ships in the fleet using '#' for occupied positions
     * and '.' for all other positions.
     *
     * @throws NullPointerException if the fleet is null
     * @throws ArrayIndexOutOfBoundsException if a ship position falls
     *         outside the board array bounds
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

}
