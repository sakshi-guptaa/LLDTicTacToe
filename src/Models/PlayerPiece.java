package Models;

public class PlayerPiece {
    private PieceType pieceType;

    public PlayerPiece(PieceType type){
        this.pieceType = type;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
