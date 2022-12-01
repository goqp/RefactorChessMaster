import javax.swing.JLabel;
import javax.swing.JPanel;
// -------------------------------------------------------------------------
/**
 * Represents a cell on the chess board. Holds a game piece.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class BoardSquare
    extends JPanel{
    private int            row;
    private int            col;
    private ChessGamePiece piece;
    private JLabel         imageLabel;
    // ----------------------------------------------------------
    /**
     * Create a new BoardSquare object.
     *
     * @param row
     *            the row
     * @param col
     *            the column
     * @param piece
     *            the game piece
     */
    public BoardSquare( int row, int col, ChessGamePiece piece ){
        super();
        this.row = row;
        this.col = col;
        this.piece = piece;
        updateImage();
    }
    /**
     * Updates the image for this BoardSquare.
     */
    private void updateImage(){
        if ( imageLabel != null ){
            removeAll();
        }
        if ( piece != null ){
            imageLabel = new JLabel();
            imageLabel.setIcon( piece.getImage() );
            add( imageLabel );     
        }
        revalidate(); // repaint wasn't working, gotta force the window manager
        // to redraw...
    }
    // ----------------------------------------------------------
    /**
     * Gets the row number.
     *
     * @return int the row number
     */
    public int getRow(){
        return row;
    }
    // ----------------------------------------------------------
    /**
     * Gets the column number.
     *
     * @return int the column number
     */
    public int getColumn(){
        return col;
    }
    // ----------------------------------------------------------
    /**
     * Gets the piece on this square
     *
     * @return GamePiece the piece
     */
    public ChessGamePiece getPieceOnSquare(){
        return piece;
    }
    // ----------------------------------------------------------
    /**
     * Sets the piece on this square
     *
     * @param p
     *            the piece
     */
    public void setPieceOnSquare( ChessGamePiece p ){
        piece = p;
        updateImage();
    }
    // ----------------------------------------------------------
    /**
     * Clears this square, removing the icon and the piece.
     */
    public void clearSquare(){
        piece = null;
        removeAll();
    }
}
