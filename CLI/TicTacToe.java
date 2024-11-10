import java.io.*;
import java.util.*;

class Board
{
    ArrayList<ArrayList<Character>> board;
    int boardSize;

    Board(int boardSize)
    {
        this.board = new ArrayList<ArrayList<Character>>();
        this.boardSize = boardSize;

        for(int i=0; i<this.boardSize; i++)
        {
            ArrayList<Character> boardRow = new ArrayList<Character>();

            for(int j=0; j<this.boardSize; j++)
            {
                boardRow.add('_');
            }

            this.board.add(boardRow);
        }
    }

    public void printBoard()
    {
        for(int i=0; i<this.board.size(); i++)
        {
            for(int j=0; j<this.board.get(i).size(); j++)
            {
                System.out.print(this.board.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public void playSingleMove(char playerTurn)
    {
        Scanner playerInput = new Scanner(System.in);
        System.out.println("Enter your X Position :");
        int xPosition = playerInput.nextInt();
        System.out.println("Enter your Y Position :");
        int yPosition = playerInput.nextInt();

        // if(board[y][x] != '_') else make a new move
        // Recursion
        // else
        // {
        //     System.out.println("You cannot play on a spot that already has been played !");
        //     playSingleMove(playerTurn);
        // }

        board.get(yPosition).set(xPosition, playerTurn);
    }

    public boolean checkWin()
    {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    public boolean checkRows()
    {
        for(int i=0; i<this.board.size(); i++)
        {
            if(checkSingleArray(this.board.get(i)))
            {
                return true;
            }
        }

        return false;
    }

    public boolean checkColumns()
    {
        for(int i=0; i<this.board.size(); i++)
        {
            ArrayList<Character> boardColumn = new ArrayList<Character>();

            for(int j=0; j<this.board.size(); j++)
            {
                boardColumn.add(this.board.get(j).get(i));
            }

            if(checkSingleArray(boardColumn))
            {
                return true;
            }
        }

        return false;
    }

    public boolean checkDiagonals()
    {
        ArrayList<Character> leftDiagonal = new ArrayList<Character>();
        ArrayList<Character> rightDiagonal = new ArrayList<Character>();

        for(int i=0; i<this.board.size(); i++)
        {
            for(int j=0; j<this.board.get(i).size(); j++)
            {
                if(i==j)
                {
                    leftDiagonal.add(this.board.get(i).get(j));
                }
                if(i + j + 1 == this.board.size())
                {
                    rightDiagonal.add(this.board.get(i).get(j));
                }
            }
        }

        return checkSingleArray(leftDiagonal) || checkSingleArray(rightDiagonal);
    }

    public boolean checkSingleArray(ArrayList<Character> boardArray)
    {
        for(int i=0;  i<boardArray.size(); i++)
        {
            if(boardArray.get(i) == '_')
            {
                return false;
            }
        }

        for(int i=0;  i<boardArray.size(); i++)
        {
            if(boardArray.get(i) == boardArray.get(0))
            {
                continue;
            }
            else
            {
                return false;
            }
        }
        return true;
    }
}


public class TicTacToe
{
    public static void main(String args[])
    {
        int BOARD_SIZE = 3;
        Board playerBoard = new Board(BOARD_SIZE);
        char playerTurn = 'X';


        for(int i=0; i<(playerBoard.board.size()*playerBoard.board.size()); i++)
        {
            playerBoard.playSingleMove(playerTurn);
            playerBoard.printBoard();

            if(playerBoard.checkWin())
            {
                System.out.println("Winner of the game is : " + playerTurn);
                break;
            }

            playerTurn = (playerTurn == 'X') ? 'O' : 'X';
        }
    }

    public static boolean isPositionPlayed(int xPosition, int yPosition)
    {
        // Keep a data Struct (TreeMap).
        // Whenever a new move is made, add to the Treemap
        // if move exists in TreeMap, return false
        return false;
    }
}