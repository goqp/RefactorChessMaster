import javax.swing.ImageIcon;
import java.util.ArrayList;
// import java.awt.Color;
// -------------------------------------------------------------------------
/**
 * Represents a Queen game piece.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class Queen
    extends ChessGamePiece{
    // ----------------------------------------------------------
    /**
     * Create a new Queen object.
     *
     * @param board
     *            the board the queen is on
     * @param row
     *            the row location of the queen
     * @param col
     *            the column location of the queen
     * @param color
     *            either GamePiece.WHITE, BLACK, or UNASSIGNED
     */
    public Queen( ChessGameBoard board, int row, int col, int color ){
        super( board, row, col, color );
    }
    /**
     * Calculates the possible moves for this Queen.
     * @param board the board to check on
     * @return ArrayList<String> the list of moves
     */
    @Override
    protected ArrayList<String> calculatePossibleMoves( ChessGameBoard board ){
        ArrayList<String> NEMoves = calculateNEMoves( board, 8 );
        ArrayList<String> NWMoves = calculateNWMoves( board, 8 );
        ArrayList<String> SEMoves = calculateSEMoves( board, 8 );
        ArrayList<String> SWMoves = calculateSWMoves( board, 8 );
        ArrayList<String> NMoves = calculateNMoves( board, 8 );
        ArrayList<String> SMoves = calculateSMoves( board, 8 );
        ArrayList<String> EMoves = calculateEMoves( board, 8 );
        ArrayList<String> WMoves = calculateWMoves( board, 8 );
        ArrayList<String> totalMoves = new ArrayList<String>();
        totalMoves.addAll( NEMoves );
        totalMoves.addAll( NWMoves );
        totalMoves.addAll( SWMoves );
        totalMoves.addAll( SEMoves );
        totalMoves.addAll( NMoves );
        totalMoves.addAll( SMoves );
        totalMoves.addAll( WMoves );
        totalMoves.addAll( EMoves );
        return totalMoves;
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
                getClass().getResource("chessImages/WhiteQueen.gif")
            );            
        }
        else if ( getColorOfPiece() == ChessGamePiece.BLACK ){
            return new ImageIcon(
                getClass().getResource("chessImages/BlackQueen.gif")
            );            
        }
        else
        {
            return new ImageIcon(
                getClass().getResource("chessImages/default-Unassigned.gif")
            ); 
        }
    }
}
