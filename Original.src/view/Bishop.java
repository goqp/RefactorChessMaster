import java.util.ArrayList;
import javax.swing.ImageIcon;
// -------------------------------------------------------------------------
/**
 * Class to represent the Bishop piece.
 * 
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class Bishop extends ChessGamePiece{
    /**
     * Creates a new Bishop object.
     * 
     * @param board
     *            board the board to create the bishop on
     * @param row
     *            row location of the Bishop
     * @param col
     *            col location of the Bishop
     * @param color
     *            either GamePiece.WHITE, BLACK, or UNASSIGNED
     */
    public Bishop( ChessGameBoard board, int row, int col, int color ){
        super( board, row, col, color );
    }
    /**
     * Calculates the possible moves for this piece. These are ALL the possible
     * moves, including illegal (but at the same time valid) moves.
     * 
     * @param board
     *            the game board to calculate moves on
     * @return ArrayList<String> the moves
     */
    @Override
    protected ArrayList<String> calculatePossibleMoves( ChessGameBoard board ){
        ArrayList<String> northEastMoves = calculateNorthEastMoves( board, 8 );
        ArrayList<String> northWestMoves = calculateNorthWestMoves( board, 8 );
        ArrayList<String> southEastMoves = calculateSouthEastMoves( board, 8 );
        ArrayList<String> southWestMoves = calculateSouthWestMoves( board, 8 );
        ArrayList<String> allMoves = new ArrayList<String>();
        allMoves.addAll( northEastMoves );
        allMoves.addAll( northWestMoves );
        allMoves.addAll( southEastMoves );
        allMoves.addAll( southWestMoves );
        return allMoves;
    }
    /**
     * Creates an icon for this piece depending on the piece's color.
     * 
     * @return ImageIcon the ImageIcon representation of this piece.
     */
    @Override
    public ImageIcon createImageByPieceType(){
        if ( getColorOfPiece() == ChessGamePiece.WHITE ){
            return new ImageIcon(
                getClass().getResource("chessImages/WhiteBishop.gif")
            );            
        }
        else if ( getColorOfPiece() == ChessGamePiece.BLACK ){
            return new ImageIcon(
                getClass().getResource("chessImages/BlackBishop.gif")
            );
        }
        else{
            return new ImageIcon(
                getClass().getResource("chessImages/BlackBishop.gif")
            );
        }
    }
}
