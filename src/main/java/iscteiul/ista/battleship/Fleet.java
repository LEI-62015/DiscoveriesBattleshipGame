/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a fleet of ships on the Battleship board.
 * Provides operations to add ships, find ships at a position,
 * select ships by category and list ships that are still afloat.
 */
public class Fleet implements IFleet {
    /**
     * Prints the string representation of each ship in the given list.
     *
     * @param ships the ships to print
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    private List<IShip> ships;

    /**
     * Creates an empty fleet.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Returns the list of ships in this fleet.
     * The returned list is the fleet's internal list, so changes to it
     * also affect the fleet.
     *
     * @return the fleet's list of ships
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Attempts to add a ship to this fleet.
     * Checks the fleet's current size, the board boundaries and
     * proximity to ships already in the fleet.
     *
     * @param s the ship to add
     * @return true if the ship was added, or false if a check failed
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Finds the ships whose category matches the given category.
     *
     * @param category the category to match
     * @return a new list containing the matching ships,
     *         or an empty list if none match
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Finds the ships in this fleet that are still afloat.
     *
     * @return a new list containing the ships that are still afloat,
     *         or an empty list if none remain
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Finds the first ship in this fleet that occupies the given position.
     *
     * @param pos the position to inspect
     * @return the ship occupying the position, or null if no ship is found
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Checks whether the ship's boundaries are inside the board.
     *
     * @param s the ship to check
     * @return true if all ship boundaries are inside the board,
     *         or false otherwise
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Checks whether any ship in this fleet is too close to the given ship.
     *
     * @param s the ship to check
     * @return true if an existing ship is too close, or false otherwise
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }


    /**
     * Prints all ships, the ships still afloat and the ships grouped
     * into the categories Galeao, Fragata, Nau, Caravela and Barca.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Prints the ships whose category matches the given category.
     *
     * @param category the category of ships to print; must not be null
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
   * Prints the ships in this fleet that are still afloat.
   */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Prints all ships in this fleet.
     */
    void printAllShips() {
        printShips(ships);
    }

}
