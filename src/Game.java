import Models.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    Deque<Player> players;
    Board gameBoard;

    public void Game(){
        initializeGame();
    }

    public void initializeGame(){
        players = new LinkedList<>();
        //get player names
        String playerNames = "";
        System.out.println("Enter Player 1, Player 2 names: ");
        Scanner inputScanner = new Scanner(System.in);
        playerNames = inputScanner.nextLine();
        String[] names = playerNames.split(",");
        Player player1 = new Player(names[0], new PieceTypeX());
        Player player2 = new Player(names[1], new PieceTypeO());
        players.add(player1);
        players.add(player2);

        //get board size, initialise board
        this.gameBoard = new Board(3);
    }

    public String startgame(){
        boolean noWinner = true;

        while(noWinner){
            //check if there's any empty space
            boolean hasEmptySpace = this.gameBoard.hasEmptySpace();
            if(!hasEmptySpace){
                noWinner = false;
                continue;
            }

            //get top player from deque, input cell coordinates, add piece to board
            Player currentPlayer = players.removeFirst();
            System.out.println("Player " + currentPlayer.getName() +" Enter row,col: ");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            String values[] = s.split(",");
            int row = Integer.valueOf(values[0]);
            int col = Integer.valueOf(values[1]);
            boolean pieceAdded = gameBoard.addPiece(currentPlayer.getPiece(),row,col);

            if(!pieceAdded){
                players.addFirst(currentPlayer);
                System.out.println("Incorrect position, try again!");
            }
            else{
                players.addLast(currentPlayer);
                this.gameBoard.printBoard();
            }

            //check for winner
            boolean foundWinner = checkWinner(row,col,currentPlayer.getPiece());
            if(foundWinner){
                return currentPlayer.getName();
            }
        }
        return "tie";
    }

    public boolean checkWinner(int row, int col, PlayerPiece piece){
        boolean win = true;
        //check row
        for(int i=0; i<gameBoard.size; i++){
            if (gameBoard.board[row][i] != piece){
                win=false;
                break;
            }
        }
        if(win) return true;

        //check col
        win=true;
        for(int i=0; i<gameBoard.size; i++){
            if (gameBoard.board[i][col] != piece){
                win=false;
                break;
            }
        }
        if(win) return true;

        //check major diagonal
        win=true;
        if (row==col){
            for(int i=0; i<gameBoard.size; i++){
                if (gameBoard.board[i][i] != piece){
                    win=false;
                    break;
                }
            }
            if(win) return true;
        }

        //check minor diagonal
        win=true;
        if (row+col == gameBoard.size){
            for(int i=0; i<gameBoard.size; i++){
                if (gameBoard.board[i][i] != piece){
                    win=false;
                    break;
                }
            }
            if(win) return true;
        }

        return false;
    }
}
