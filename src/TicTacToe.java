import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel label = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean firstPlayerTurn;

    // Constructor
    TicTacToe() {

        // Set a Frame
        frame.setDefaultCloseOperation(3);
        frame.setSize(600, 700);
        frame.getContentPane().setBackground(new Color(100, 100, 100));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        // Set a label
        label.setBackground(new Color(25, 25, 25));
        label.setForeground(new Color(25, 255, 0));
        label.setFont(new Font("Ink Free", Font.BOLD, 40));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Welcome to TicTacToe!");
        label.setOpaque(true);

        // Set the title panel
        title_panel.setLayout(new BorderLayout());
        title_panel.add(label);

        // Set the button panel
        button_panel.setLayout(new GridLayout(3, 3));

        // Add the buttons in the button panel
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        // Add the title and button panels to our main frame
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        // Calling the method to check which player goes first.
        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        for (int i = 0; i < buttons.length; i++) {
            if (arg0.getSource() == buttons[i]) {
                if (firstPlayerTurn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        firstPlayerTurn = false;
                        label.setText("O's turn");
                        checkWinner("X");
                    }
                }
                else {
                    buttons[i].setForeground(new Color(0, 0, 255));
                    buttons[i].setText("O");
                    firstPlayerTurn = true;
                    label.setText("X's turn");
                    checkWinner("O");
                }
            }
        }
    }

    public void firstTurn() {

        disableButtons();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        enableButtons();

        if (random.nextInt(2) == 0) {
            firstPlayerTurn = true;
            label.setText("X goes first!");
        } else {
            firstPlayerTurn = false;
            label.setText("O goes first!");
        }
    }

    public void checkWinner(String player) {

        
        int[][] winningCombinations = {
                { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 },
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
                { 0, 4, 8 }, { 2, 4, 6 }
        };

        for (int[] combinations : winningCombinations) {
            if (buttons[combinations[0]].getText().equals(player) && buttons[combinations[1]].getText().equals(player)
                    && buttons[combinations[2]].getText().equals(player)) {
                declareWinner(combinations, player);
                disableButtons();
                return;
            }
        }

        boolean isDraw = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                isDraw = false;
                break;
            }
        }
        if (isDraw) {
            label.setText("It's a draw :(");
            disableButtons();
            return;
        }
    }

    public void declareWinner(int[] combinations, String player) {
        for (int index : combinations) {
            buttons[index].setBackground(Color.green);
            label.setText(player + " won, ggs");
        }
    }

    public void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    public void enableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }
}