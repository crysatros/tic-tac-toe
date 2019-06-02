package org;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class GameField extends JPanel {
    private static GameField instance = null;
    public static final int FIELD_SIZE = 450;
    public final String NOT_SIGN = "*";
    boolean gameOver = false;
    String gameOverMessage = "";
    static int linesCount = 3;
    int cellSize, x, y;
    boolean nextTurn = false;
    Player p1;
    Player p2;
    public String[][] cell;
    public static synchronized GameField getInstance() {
        if (instance == null)
            instance = new GameField();
        return instance;
    }
    void startNewGame() {
        gameOver = false;
        gameOverMessage = "";
        cellSize = FIELD_SIZE / linesCount;
        cell = new String[linesCount][linesCount];
        repaint();
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                cell[i][j] = NOT_SIGN;
            }
        }
        setVisible(true);
    }
    private GameField() {
        setVisible(false);
        p1 = new Player("X");
        p2 = new Player("O");
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                x = e.getX() / cellSize;
                y = e.getY() / cellSize;
                System.out.println("" + e.getX() + " " + e.getY());
                if (!gameOver) {
                    GameStart();
                }
            }
        });
    }
    void GameStart() {
        if (p1.isShotReady == 1) {
            nextTurn = true;
            p2.isShotReady = 0;
            System.out.println("Игрок 1:");
            p1.shoot(x,y);
        }
        if (p1.win()) {
            System.out.println("Победил Игрок1");
            gameOver = true;
            gameOverMessage = "Победил Игрок1";
        }
        repaint();
        if (isFieldFull() && !p1.win() && !p2.win()) {
            gameOver = true;
            gameOverMessage = "НИЧЬЯ";
        }
        if (p2.isShotReady == 1) {
            nextTurn = false;
            p1.isShotReady = 0;
            System.out.println("Игрок 2:");
            p2.shoot(x,y);
        }
        if (!gameOver) {
            p2.shoot(x, y);
        }
        if (p2.win()) {
            System.out.println("Победил Игрок2");
            gameOver = true;
            gameOverMessage = "Победил Игрок2";
        }
        repaint();
        if (isFieldFull() && !p2.win() && !p1.win()) {
            gameOver = true;
            gameOverMessage = "НИЧЬЯ";
        }
        if (nextTurn) {
            p1.isShotReady = 0;
            p2.isShotReady = 1;
        }
        else {
            p1.isShotReady = 1;
            p2.isShotReady = 0;
        }
    }
    boolean isCellBusy(int x, int y) {
        if (x < 0 || y < 0 || x > linesCount - 1 || y > linesCount - 1) {
            return false;
        }
        return cell[x][y] != NOT_SIGN;
    }
    public boolean isFieldFull() {
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if (cell[i][j] == NOT_SIGN)
                    return false;
            }
        }
        return true;
    }
    public boolean checkLine(int start_x, int start_y, int dx, int dy, String sign) {
        for (int i = 0; i < linesCount; i++) {
            if (cell[start_x + i * dx][start_y + i * dy] != sign)
                return false;
        }
        return true;
    }
    public boolean checkWin(String sign) {
        for (int i = 0; i < linesCount; i++) {
            if (checkLine(i, 0, 0, 1, sign)) return true;
            if (checkLine(0, i, 1, 0, sign)) return true;
        }
        if (checkLine(0, 0, 1, 1, sign)) return true;
        if (checkLine(0, linesCount - 1, 1, -1, sign)) return true;
        return false;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        final BasicStroke stroke = new BasicStroke(2.0f);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(stroke);
        for (int i = 0; i <= this.linesCount; i++) {
            g2.drawLine(0, i * this.cellSize, FIELD_SIZE, i * this.cellSize);
            g2.drawLine(i * this.cellSize, 0, i * this.cellSize, FIELD_SIZE);
        }
        for (int i = 0; i < linesCount; i++) {
            for (int j = 0; j < linesCount; j++) {
                if (cell[i][j] != NOT_SIGN) {
                    if (cell[i][j] == "X") {
                        g2.setColor(Color.RED);
                        g2.drawLine((i * cellSize), (j * cellSize), (i + 1) * cellSize, (j + 1) * cellSize);
                        g2.drawLine((i + 1) * cellSize, (j * cellSize), (i * cellSize), (j + 1) * cellSize);
                    }
                    if (cell[i][j] == "O") {
                        g2.setColor(Color.BLUE);
                        g2.drawOval((i * cellSize), (j * cellSize), cellSize, cellSize);
                    }
                }
            }
        }
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial Black", Font.BOLD, 30));
            g.drawString(gameOverMessage, 68, 240);
        }
    }
}