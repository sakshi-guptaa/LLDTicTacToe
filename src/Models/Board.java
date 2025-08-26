package Models;

public class Board {
    public int size;
    public PlayerPiece[][] board;

    public Board(int size){
        this.size = size;
        this.board = new PlayerPiece[size][size];
    }

    public boolean addPiece(PlayerPiece piece, int row, int col){
        //check if cell is already filled
        //if not filled, add to (row,col), print the board
        if (board[row][col] != null){
            return false;
        }
        else{
            board[row][col] = piece;
            return true;
        }
    }

    public boolean hasEmptySpace(){
        for(int i=0; i< this.size; i++){
            for (int j=0; j< this.size; j++){
                if (this.board[i][j] == null)
                    return true;
            }
        }
        return false;
    }

    public void printBoard(){
        for(int i=0; i< this.size; i++){
            System.out.print('|');
            for(int j=0; j<this.size; j++){
                if(this.board[i][j] == null)
                    System.out.print("  |");
                else
                    System.out.print(this.board[i][j].getPieceType().name() + " |");
            }
            System.out.print('\n');
        }
    }
}
