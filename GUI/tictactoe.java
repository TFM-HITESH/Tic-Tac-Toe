import java.util.*;
import java.io.*;


public class tictactoe
{
    public static void main(String args[])
    {
        // 0 - BLANK
        // 1 - X
        // 2 - O

        int board[][] = new int[3][];

        for(int i=0; i<3; i++)
        {
            board[i] = new int[3];
        }

        for(int i =0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                board[i][j] = 0;
            }
        }

        print(board);

        int t = 1;

        for(int i=0; i<9; i++)
        {
            if(t == 1)
            {
                Scanner sc = new Scanner(System.in);
                int xpos = sc.nextInt();
                int ypos = sc.nextInt();

                board[ypos][xpos] = 1;

                t++;
            }
            else
            {
                Scanner sc = new Scanner(System.in);
                int xpos = sc.nextInt();
                int ypos = sc.nextInt();

                board[ypos][xpos] = 2;

                t--;
            }

            print(board);

            if(checkWin(board))
            {
                if(t==1)
                {
                    System.out.println("X has won");
                }
                else
                {
                    System.out.println("O has won");
                }
                break;
            }
        }
    }

    public static void print(int board[][])
    {
        for(int i =0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                if(board[i][j] == 0)
                {
                    System.out.print("_ ");
                }
                else if(board[i][j] == 1)
                {
                    System.out.print("X ");
                }
                else if(board[i][j] == 2)
                {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }


    public static boolean checkWin(board)
    {
        if(board[0][0] == board[0][1] && board[0][1] == board[0][2]) return true;
        if(board[1][0] == board[1][1] && board[1][1] == board[1][2]) return true;
        if(board[2][0] == board[2][1] && board[2][1] == board[2][2]) return true;

        if(board[0][0] == board[0][1] && board[0][1] == board[0][2]) return true;
        if(board[0][0] == board[0][1] && board[0][1] == board[0][2]) return true;
        if(board[0][0] == board[0][1] && board[0][1] == board[0][2]) return true;
        
        if(board[0][0] == board[0][1] && board[0][1] == board[0][2]) return true;
        if(board[0][0] == board[0][1] && board[0][1] == board[0][2]) return true;

        return false;
    }
}
