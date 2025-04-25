import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Model: Game Logic
class TicTacToeGame {
    private char[][] board;
    private char currentPlayer;

    public TicTacToeGame() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col] == '_') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        // Rows and Columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != '_' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) ||
                (board[0][i] != '_' && board[0][i] == board[1][i] && board[1][i] == board[2][i]))
                return true;
        }
        // Diagonals
        if ((board[0][0] != '_' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
            (board[0][2] != '_' && board[0][2] == board[1][1] && board[1][1] == board[2][0]))
            return true;

        return false;
    }

    public boolean isBoardFull() {
        for (char[] row : board)
            for (char cell : row)
                if (cell == '_') return false;
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void resetGame() {
        initializeBoard();
        currentPlayer = 'X';
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = '_';
    }
}

// View + Controller: GUI
public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private TicTacToeGame game;
    private JLabel statusLabel;

    public TicTacToeGUI() {
        game = new TicTacToeGame();
        setTitle("Tic Tac Toe - GUI");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 60);

        // Create buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(buttonFont);
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("Player X's Turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        add(statusLabel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clicked == buttons[i][j]) {
                    if (buttons[i][j].getText().equals("") && game.makeMove(i, j)) {
                        buttons[i][j].setText(String.valueOf(game.getCurrentPlayer()));
                        if (game.checkWin()) {
                            statusLabel.setText("Player " + game.getCurrentPlayer() + " Wins!");
                            disableButtons();
                            return;
                        } else if (game.isBoardFull()) {
                            statusLabel.setText("It's a Draw!");
                            return;
                        } else {
                            game.switchPlayer();
                            statusLabel.setText("Player " + game.getCurrentPlayer() + "'s Turn");
                        }
                    }
                }
            }
        }
    }

    private void disableButtons() {
        for (JButton[] row : buttons)
            for (JButton button : row)
                button.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}
