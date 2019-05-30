import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MForm extends JFrame {
    public MForm()
    {
        setTitle("Welcome to Tic-Tac-Toe game!");
        setBounds(300, 300, 455, 525);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final GameField gameField = GameField.getInstance();
        JPanel buttonPanel = new JPanel(new GridLayout());
        add(gameField, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        final JButton Start = new JButton("Новая игра");
        final JButton End = new JButton("Завершить игру...");
        buttonPanel.add(Start);
        buttonPanel.add(End);
        End.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        Start.addActionListener(new ActionListener()
        {
            @Override

            public void actionPerformed(ActionEvent e)
            {
                System.out.println(Start.getText());
                gameField.startNewGame();
            }
        });
        setVisible(true);
    }
}