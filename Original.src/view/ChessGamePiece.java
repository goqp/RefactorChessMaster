import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
// -------------------------------------------------------------------------
/**
 * Abstract class that is used to represent a game piece on the chess board.
 * Classes to extend this piece are Rook, Bishop, Knight, Queen, King and Pawn.
 * Also contains a large number of methods to determine information about cells
 * around this piece.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public abstract class ChessGamePiece{
    private boolean             skipMoveGeneration;
    private int                 pieceColor;
    private ImageIcon           pieceImage;
    /**
     * The list of possible moves for this piece. Updated when actions involving
     * this piece occur. (created, moved, selected, etc)
     */
    protected ArrayList<String> possibleMoves;
    /**
     * The game piece's row.
     */
    protected int               pieceRow;
    /**
     * The game piece's column.
     */
    protected int               pieceColumn;
    /**
     * Represents a black piece as an int
     */
    static final int            BLACK      = 0;
    /**
     * Represents a white piece as an int
     */
    static final int            WHITE      = 1;
    /**
     * Represents a piece that has not been assigned a color
     */
    static final int            UNASSIGNED = -1;
    // ----------------------------------------------------------
    /**
     * Create a new GamePiece object.
     *
     * @param board
     *            the board to create this piece on
     * @param row
     *            row of the GamePiece
     * @param col
     *            column of the GamePiece
     * @param pieceColor
     *            either GamePiece.WHITE, BLACK, or UNASSIGNED
     */
    public ChessGamePiece(
        ChessGameBoard board,
        int row,
        int col,
        int pieceColor ){
        skipMoveGeneration = false;
        this.pieceColor = pieceColor;
        pieceImage = createImageByPieceType();
        pieceRow = row;
        pieceColumn = col;
        if ( board.getCell( row, col ) != null ){
            board.getCell( row, col ).setPieceOnSquare( this );
        }
        possibleMoves = calculatePossibleMoves( board );
    }
    // ----------------------------------------------------------
    /**
     * Create a new GamePiece object. This constructor is used for special
     * pieces like pawn, which require other actions to occur before moves are
     * generated. (the pawn can move twice on its initialization, for example)
     *
     * @param board
     *            the board to place the piece on
     * @param row
     *            the row to place the piece on
     * @param col
     *            the column to place the piece on
     * @param skipMoveGeneration
     *            if true, moves will not be generated in the constructor
     * @param pieceColor
     *            either GamePiece.BLACK, WHITE, or UNASSIGNED
     */
    public ChessGamePiece(
        ChessGameBoard board,
        int row,
        int col,
        int pieceColor,
        boolean skipMoveGeneration ){
        this.skipMoveGeneration = skipMoveGeneration;
        this.pieceColor = pieceColor;
        pieceImage = this.createImageByPieceType();
        pieceRow = row;
        pieceColumn = col;
        if ( board.getCell( row, col ) != null ){
            board.getCell(row, col).setPieceOnSquare( this );
        }
        if ( !this.skipMoveGeneration ){
            possibleMoves = calculatePossibleMoves( board );
        }
    }
    // ----------------------------------------------------------
    /**
     * Generates and returns a list of Strings that represent possible move
     * locations for the piece, in the following format: ["xloc_0,yloc_0",
     * "xloc_1,yloc_1", ...] It is recommended to use the helper methods below
     * to implement this method. (calculateNorth, calculateWest, ...)
     *
     * @param board
     *            the board to check moves on
     * @return ArrayList<String> the list of moves
     */
    protected abstract ArrayList<String> calculatePossibleMoves(
        ChessGameBoard board );
    /**
     * Calculates and returns moves in the south direction relative to this
     * piece.
     *
     * @param board
     *            the board to calculate the moves on
     * @param numMoves
     *            the number of moves to calculate
     * @return ArrayList<String> the calculated moves.
     */
    protected ArrayList<String> calculateSouthMoves(
        ChessGameBoard board,
        int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = pieceRow + 1; i < 8 && count < numMoves; i++ ){
                if ( ( board.getCell( i, pieceColumn ).getPieceOnSquare()
                    == null || isEnemy( board, i, pieceColumn ) ) ){
                    moves.add( i + "," + pieceColumn );
                    count++;
                    if ( isEnemy( board, i, pieceColumn ) ){
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------
    /**
     * Calculates and returns moves in the north direction relative to this
     * piece.
     *
     * @param board
     *            the board to calculate the moves on
     * @param numMoves
     *            the number of moves to calculate
     * @return ArrayList<String> the moves in this direction
     */
    protected ArrayList<String> calculateNorthMoves(
        ChessGameBoard board,
        int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = pieceRow - 1; i >= 0 && count < numMoves; i-- ){
                if ( ( board.getCell( i, pieceColumn ).getPieceOnSquare()
                    == null || isEnemy( board, i, pieceColumn ) ) ){
                    moves.add( i + "," + pieceColumn );
                    count++;
                    if ( isEnemy( board, i, pieceColumn ) ){
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------
    /**
     * Calculates and returns moves in the east direction relative to this
     * piece.
     *
     * @param board
     *            the board to calculate the moves on
     * @param numMoves
     *            the number of moves to calculate
     * @return ArrayList<String> the moves in this direction
     */
    protected ArrayList<String> calculateEastMoves(
        ChessGameBoard board,
        int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = pieceColumn + 1; i < 8 && count < numMoves; i++ ){
                if ( ( board.getCell( pieceRow, i ).getPieceOnSquare()
                    == null || isEnemy( board, pieceRow, i ) ) ){
                    moves.add( pieceRow + "," + i );
                    count++;
                    if ( isEnemy( board, pieceRow, i ) ){
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------
    /**
     * Calculates and returns moves in the west direction relative to this
     * piece.
     *
     * @param board
     *            the board to calculate the moves on
     * @param numMoves
     *            the number of moves to calculate
     * @return ArrayList<String> the moves in this direction
     */
    protected ArrayList<String> calculateWestMoves(
        ChessGameBoard board,
        int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = pieceColumn - 1; i >= 0 && count < numMoves; i-- ){
                if ( ( board.getCell(pieceRow, i ).getPieceOnSquare()
                    == null || isEnemy( board, pieceRow, i ) ) ){
                    moves.add( pieceRow + "," + i );
                    count++;
                    if ( isEnemy( board, pieceRow, i ) ){
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------
    /**
     * Calculates and returns moves in the north-west direction relative to this
     * piece.
     *
     * @param board
     *            the board to calculate the moves on
     * @param numMoves
     *            the number of moves to calculate
     * @return ArrayList<String> the moves in this direction
     */
    protected ArrayList<String> calculateNorthWestMoves(
        ChessGameBoard board,
        int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = 1; i < 8 && count < numMoves; i++ ){
                if ( isOnScreen( pieceRow - i, pieceColumn - i )
                    && ( board.getCell( pieceRow - i,
                        pieceColumn - i ).getPieceOnSquare() == null ) ){
                    moves.add( ( pieceRow - i ) + "," + ( pieceColumn - i ) );
                    count++;
                }
                else if ( isEnemy( board, pieceRow - i, pieceColumn - i ) ){
                    moves.add( ( pieceRow - i ) + "," + ( pieceColumn - i ) );
                    count++;
                    break;
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------
    /**
     * Calculates and returns moves in the north-east direction relative to this
     * piece.
     *
     * @param board
     *            the board to calculate the moves on
     * @param numMoves
     *            the number of moves to calculate
     * @return ArrayList<String> the moves in this direction
     */
    protected ArrayList<String> calculateNorthEastMoves(
        ChessGameBoard board,
        int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = 1; i < 8 && count < numMoves; i++ ){
                if ( isOnScreen( pieceRow - i, pieceColumn + i )
                    && ( board.getCell( pieceRow - i,
                        pieceColumn + i).getPieceOnSquare() == null ) ){
                    moves.add( ( pieceRow - i ) + "," + ( pieceColumn + i ) );
                    count++;
                }
                else if ( isEnemy( board, pieceRow - i, pieceColumn + i ) ){
                    moves.add( ( pieceRow - i ) + "," + ( pieceColumn + i ) );
                    count++;
                    break;
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------
    /**
     * Calculates and returns moves in the south-west direction relative to this
     * piece.
     *
     * @param board
     *            the board to calculate the moves on
     * @param numMoves
     *            the number of moves to calculate
     * @return ArrayList<String> the moves in this direction
     */
    protected ArrayList<String> calculateSouthWestMoves(
        ChessGameBoard board,
        int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = 1; i < 8 && count < numMoves; i++ ){
                if ( isOnScreen( pieceRow + i, pieceColumn - i )
                    && ( board.getCell( pieceRow + i,
                        pieceColumn - i ).getPieceOnSquare() == null ) ){
                    moves.add( ( pieceRow + i ) + "," + ( pieceColumn - i ) );
                    count++;
                }
                else if ( isEnemy( board, pieceRow + i, pieceColumn - i ) ){
                    moves.add( ( pieceRow + i ) + "," + ( pieceColumn - i ) );
                    count++;
                    break;
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    // ----------------------------------------------------------
    /**
     * Calculates and returns moves in the south-east direction relative to this
     * piece.
     *
     * @param board
     *            the board to calculate the moves on
     * @param numMoves
     *            the number of moves to calculate
     * @return ArrayList<String> the moves in this direction
     */
    protected ArrayList<String> calculateSouthEastMoves(
        ChessGameBoard board,
        int numMoves ){
        ArrayList<String> moves = new ArrayList<String>();
        int count = 0;
        if ( isPieceOnScreen() ){
            for ( int i = 1; i < 8 && count < numMoves; i++ ){
                if ( isOnScreen( pieceRow + i, pieceColumn + i )
                    && ( board.getCell( pieceRow + i,
                        pieceColumn + i ).getPieceOnSquare() == null ) ){
                    moves.add( ( pieceRow + i ) + "," + ( pieceColumn + i ) );
                    count++;
                }
                else if ( isEnemy( board, pieceRow + i, pieceColumn + i ) ){
                    moves.add( ( pieceRow + i ) + "," + ( pieceColumn + i ) );
                    count++;
                    break;
                }
                else
                {
                    break;
                }
            }
        }
        return moves;
    }
    /**
     * Creates the ImageIcon by the color of the piece.
     *
     * @return ImageIcon the image that represents this game piece, different
     *         for each piece.
     */
    public abstract ImageIcon createImageByPieceType();
    /**
     * Return the ImageIcon as an Image.
     *
     * @return ImageIcon The ImageIcon as an Image
     */
    public ImageIcon getImage(){
        return pieceImage;
    }
    // ----------------------------------------------------------
    /**
     * Gets the color of this piece.
     *
     * @return int 0 for a black piece, 1 for a white piece, -1 for an
     *         unassigned piece.
     */
    public int getColorOfPiece(){
        return pieceColor;
    }
    // ----------------------------------------------------------
    /**
     * Checks if the requested location is in bounds.
     *
     * @param row
     *            the row to check
     * @param col
     *            the column to check
     * @return boolean true if the location is valid, false if not
     */
    public boolean isOnScreen( int row, int col ){
        if ( row >= 0 && row <= 7 && col >= 0 && col <= 7 ){
            return true;
        }
        else
        {
            return false;
        }
    }
    // ----------------------------------------------------------
    /**
     * Checks if this piece's current location is in bounds. This prevents users
     * from trying to move pieces out of the graveyard.
     *
     * @return true if in bounds, false otherwise
     */
    public boolean isPieceOnScreen(){
        return isOnScreen( pieceRow, pieceColumn );
    }
    /**
     * Update this piece's position.
     *
     * @param board
     *            the game board to move on
     * @param row
     *            the row to move to
     * @param col
     *            the column to move to
     * @return boolean true if the move was successful, false otherwise
     */
    public boolean move( ChessGameBoard board, int row, int col ){
        if ( canMove( board, row, col ) ){
            String moveLog = this.toString() + " -> ";
            board.clearCell( pieceRow, pieceColumn );
            if ( isEnemy( board, row, col ) ){
                ChessGraveyard graveyard;
                ChessGameEngine gameEngine =
                    ( (ChessPanel)board.getParent() ).getGameEngine();
                if ( gameEngine.getCurrentPlayer() == 1 ){
                    graveyard =
                        ( (ChessPanel)board.getParent() ).getGraveyard( 2 );
                }
                else
                {
                    graveyard =
                        ( (ChessPanel)board.getParent() ).getGraveyard( 1 );
                }
                graveyard.addPiece(
                    board.getCell( row, col ).getPieceOnSquare() );
            }
            setPieceLocation( row, col );
            moveLog += " (" + row + ", " + col + ")";
            ( (ChessPanel)board.getParent() ).getGameLog().addToLog( moveLog );
            board.getCell( row, col ).setPieceOnSquare( this );
            if ( !skipMoveGeneration ){
                updatePossibleMoves( board );
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Determines if this piece can move to the specified row and column. Also
     * checks if the current player's king would be put in check by this move.
     *
     * @param board
     *            the board to move on
     * @param row
     *            the row to move to
     * @param col
     *            the column to move to
     * @return boolean true if this piece can make the move, false if it cannot
     */
    public boolean canMove( ChessGameBoard board, int row, int col ){
        updatePossibleMoves( board );
        if ( possibleMoves.indexOf( row + "," + col ) > -1 ){
            return testMoveForKingSafety( board, row, col );
        }
        return false;
    }
    /**
     * Checks if the move that is about to be made would cause the current
     * player's King to be put in check (which is an illegal move).
     *
     * @param board
     *            the game board to check on
     * @param row
     *            the row to move to
     * @param col
     *            the column to move to
     * @return boolean true if the move is safe, false if it is not
     */
    private boolean testMoveForKingSafety(
        ChessGameBoard board,
        int row,
        int col ){
        updatePossibleMoves( board );
        ChessGamePiece oldPieceOnOtherSquare =
            board.getCell( row, col ).getPieceOnSquare();
        ChessGameEngine engine =
            ( (ChessPanel)board.getParent() ).getGameEngine();
        int oldRow = pieceRow;
        int oldColumn = pieceColumn;
        board.clearCell( pieceRow, pieceColumn ); // move us off
        this.setPieceLocation( row, col ); // move us to the new location
        board.getCell( row, col ).setPieceOnSquare( this );
        boolean retVal = !engine.isKingInCheck( true ); // is the current
        // king still in check?
        this.setPieceLocation( oldRow, oldColumn ); // move us back
        board.getCell( oldRow, oldColumn ).setPieceOnSquare( this );
        board.clearCell( row, col ); // ^ move the other piece back
        // to where it was
        board.getCell( row, col ).setPieceOnSquare( oldPieceOnOtherSquare );
        return retVal;
    }
    // ----------------------------------------------------------
    /**
     * Re-calculates the possible moves for this piece. This is called whenever
     * new moves need to be made.
     *
     * @param board
     *            the board to calculate moves on
     */
    protected void updatePossibleMoves( ChessGameBoard board ){
        possibleMoves = calculatePossibleMoves( board );
    }
    // ----------------------------------------------------------
    /**
     * Sets the internal piece location.
     *
     * @param row
     *            the new row of the piece
     * @param col
     *            the new column of the piece
     */
    public void setPieceLocation( int row, int col ){
        pieceRow = row;
        pieceColumn = col;
    }
    // ----------------------------------------------------------
    /**
     * Returns this piece's row location.
     *
     * @return int the row
     */
    public int getRow(){
        return pieceRow;
    }
    // ----------------------------------------------------------
    /**
     * Returns this piece's column.
     *
     * @return int the column
     */
    public int getColumn(){
        return pieceColumn;
    }
    // ----------------------------------------------------------
    /**
     * Shows the legal move locations for this GamePiece.
     *
     * @param board
     *            The board to show the move locations on
     */
    public void showLegalMoves( ChessGameBoard board ){
        updatePossibleMoves( board );
        if ( isPieceOnScreen() ){
            for ( String locStr : possibleMoves ){
                String[] currCoords = locStr.split( "," );
                int row = Integer.parseInt( currCoords[0] );
                int col = Integer.parseInt( currCoords[1] );
                if ( canMove( board, row, col ) ) // only show legal moves
                {
                    if ( isEnemy( board, row, col ) ){
                        board.getCell( row, col ).setBackground(
                            Color.YELLOW );
                    }
                    else
                    {
                        board.getCell( row, col ).setBackground( Color.PINK );
                    }
                }
            }
        }
    }
    // ----------------------------------------------------------
    /**
     * Determines if this piece has legal moves to make.
     *
     * @param board
     *            the game board to check
     * @return true if there are legal moves, false if there are not
     */
    public boolean hasLegalMoves( ChessGameBoard board ){
        updatePossibleMoves( board );
        if ( isPieceOnScreen() ){
            for ( String locStr : possibleMoves ){
                String[] currCoords = locStr.split( "," );
                int row = Integer.parseInt( currCoords[0] );
                int col = Integer.parseInt( currCoords[1] );
                if ( canMove( board, row, col ) ) // only show legal moves
                {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    // ----------------------------------------------------------
    /**
     * Determines if the row and column contains an enemy piece. This is defined
     * in GamePiece and not ChessGameBoard because different pieces have
     * different enemies depending on their colors.
     *
     * @param row
     *            row of the GamePiece
     * @param col
     *            column of the GamePiece
     * @param board
     *            the board to check
     * @return true if it is an enemy piece, false if not
     */
    public boolean isEnemy( ChessGameBoard board, int row, int col ){
        if ( row > 7 || col > 7 || row < 0 || col < 0 ){
            return false;
        }
        ChessGamePiece enemyPiece =
            board.getCell( row, col ).getPieceOnSquare() == null
                ? null
                : board.getCell( row, col ).getPieceOnSquare();
        if ( enemyPiece == null
            || this.getColorOfPiece() == ChessGamePiece.UNASSIGNED
            || enemyPiece.getColorOfPiece() == ChessGamePiece.UNASSIGNED ){
            return false;
        }
        if ( this.getColorOfPiece() == ChessGamePiece.WHITE ){
            if ( enemyPiece.getColorOfPiece() == ChessGamePiece.BLACK ){
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if ( enemyPiece.getColorOfPiece() == ChessGamePiece.WHITE ){
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    // ----------------------------------------------------------
    /**
     * Gets a list of GamePieces that can currently attack this game piece.
     *
     * @param board the game board to check on
     * @return ArrayList<GamePiece> the list of attackers
     */
    public ArrayList<ChessGamePiece> getCurrentAttackers( ChessGameBoard board ){
        ArrayList<ChessGamePiece> attackers = new ArrayList<ChessGamePiece>();
        int enemyColor =
            ( this.getColorOfPiece() == ChessGamePiece.BLACK )
                ? ChessGamePiece.WHITE
                : ChessGamePiece.BLACK;
        this.updatePossibleMoves( board );
        for ( int i = 0; i < board.getCells().length; i++ ){
            for ( int j = 0; j < board.getCells()[0].length; j++ ){
                ChessGamePiece currPiece =
                    board.getCell( i, j ).getPieceOnSquare();
                if ( currPiece != null
                    && currPiece.getColorOfPiece() == enemyColor ){
                    currPiece.updatePossibleMoves( board );
                    if ( currPiece.canMove( board, pieceRow, pieceColumn ) ){
                        attackers.add( currPiece );
                    }
                }
            }
        }
        return attackers;
    }
    /**
     * Returns a string representation of this piece. Includes piece type and
     * location.
     *
     * @return String the string representation
     */
    @Override
    public String toString(){
        return this.getClass().toString().substring( 6 ) + " @ (" + pieceRow
            + ", " + pieceColumn + ")";
    }
}
