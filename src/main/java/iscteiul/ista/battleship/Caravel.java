/**
 * Represents a Caravel ship in the game.
 * A Caravel occupies two positions depending on its orientation.
 */
package iscteiul.ista.battleship;
/**
 * Representa uma caravela no jogo da Batalha Naval.
 *
 * <p>A caravela é um tipo de navio da época dos
 * Descobrimentos, representado por duas posições
 * consecutivas no tabuleiro.</p>
 *
 * <p>Esta classe estende Ship e permite criar
 * caravelas com orientação horizontal ou vertical.</p>
 *
 * @author Afonso Carolo
 * @see Ship
 * @see Compass
 */
public class Caravel extends Ship {

    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";
    /**
     * Cria uma caravela com uma orientação e posição inicial.
     *
     * <p>A caravela ocupa duas posições consecutivas
     * no tabuleiro, de acordo com a orientação indicada.</p>
     *
     * <p>As orientações NORTH e SOUTH correspondem
     * ao posicionamento vertical, enquanto EAST e WEST
     * correspondem ao posicionamento horizontal.</p>
     *
     * @param bearing orientação da caravela
     * @param pos posição inicial da caravela
     * @throws NullPointerException se a orientação for nula
     * @throws IllegalArgumentException se a orientação for inválida
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }
    }

    /**
     * Obtém o tamanho da caravela.
     *
     * <p>A caravela ocupa sempre duas posições
     * consecutivas no tabuleiro.</p>
     *
     * @return o tamanho da caravela, igual a 2
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}
