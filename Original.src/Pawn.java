import javax.swing.ImageIcon;
import java.util.ArrayList;
// -------------------------------------------------------------------------
/**
 * Represents a Pawn game piece. Unique in that it can move two locations on its
 * first turn and therefore requires a new 'notMoved' variable to keep track of
 * its turns.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class Pawn
    extends ChessGamePiece{
    private boolean notMoved;
    // ----------------------------------------------------------
    /**
     * Create a new Pawn object.
     *
     * @param board
     *            the board to create the pawn on
     * @param row
     *            row of the pawn
     * @param col
     *            column of the pawn
     * @param color
     *            either GamePiece.WHITE, BLACK, or UNASSIGNED
     */
    public Pawn( ChessGameBoard board, int row, int col, int color ){
        super( board, row, col, color, true );
        notMoved = true;
        possibleMoves = calculatePossibleMoves( board );
    }
    /**
     * Moves this pawn to a row and col
     *
     * @param board
     *            the board to move on
     * @param row
     *            the row to move to
     * @param col
     *            the col to move to
     * @return boolean true if the move was successful, false otherwise
     */
    @Override
    public boolean move( ChessGameBoard board, int row, int col ){
        if ( super.move( board, row, col ) ){
            notMoved = false;
            possibleMoves = calculatePossibleMoves( board );
            if ( ( getColorOfPiece() == ChessGamePiece.BLACK && row == 7 )
                || ( getColorOfPiece() == ChessGamePiece.WHITE && row == 0 ) ){ // pawn has reached the end of the board, promote it to queen
                board.getCell( row, col ).setPieceOnSquare( new Queen(
                    board,
                    row,
                    col,
                    getColorOfPiece() ) );
            }
            return true;
        }
        return false;
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
        ArrayList<String> moves = new ArrayList<String>();
        if ( isPieceOnScreen() ){
            int currRow =
                getColorOfPiece() == ChessGamePiece.WHITE
                    ? ( pieceRow - 1 )
                    : ( pieceRow + 1 );
            int count = 1;
            int maxIter = notMoved ? 2 : 1;
            // check for normal moves
            while ( count <= maxIter ){ // only loop while we have open slots and have not passed our
              // limit
                if ( isOnScreen( currRow, pieceColumn )
                    && board.getCell( currRow,
                        pieceColumn ).getPieceOnSquare() == null ){
                    moves.add( currRow + "," + pieceColumn );
                }
                else
                {
                    break;
                }
                currRow =
                    ( getColorOfPiece() == ChessGamePiece.WHITE )
                        ? ( currRow - 1 )
                        : ( currRow + 1 );
                count++;
            }
            // check for enemy capture points
            if ( getColorOfPiece() == ChessGamePiece.WHITE ){
                if ( isEnemy( board, pieceRow - 1, pieceColumn - 1 ) ){
                    moves.add( ( pieceRow - 1 ) + "," + ( pieceColumn - 1 ) );
                }
                if ( isEnemy( board, pieceRow - 1, pieceColumn + 1 ) ){
                    moves.add( ( pieceRow - 1 ) + "," + ( pieceColumn + 1 ) );
                }
            }
            else
            {
                if ( isEnemy( board, pieceRow + 1, pieceColumn - 1 ) ){
                    moves.add( ( pieceRow + 1 ) + "," + ( pieceColumn - 1 ) );
                }
                if ( isEnemy( board, pieceRow + 1, pieceColumn + 1 ) ){
                    moves.add( ( pieceRow + 1 ) + "," + ( pieceColumn + 1 ) );
                }
            }
        }
        return moves;
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
                getClass().getResource("chessImages/WhitePawn.gif")
            );            
        }
        else if ( getColorOfPiece() == ChessGamePiece.BLACK ){
            return new ImageIcon(
                getClass().getResource("chessImages/BlackPawn.gif")
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
