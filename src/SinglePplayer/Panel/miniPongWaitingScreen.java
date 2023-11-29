package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class miniPongWaitingScreen extends JPanel implements KeyListener, ActionListener {
    private int ballPosX = 50; // Starting position of the ball
    private int ballPosY = 50;
    private int ballDirX = 1; // Direction increments
    private int ballDirY = 2;
    private int paddle1Y = 150; // Starting position of paddles
    private int paddle2Y = 150;
    private final int PADDLE_HEIGHT = 80; // Adjusted paddle height
    private final int PADDLE_WIDTH = 10;
    private final int WINDOW_WIDTH = 420;
    private final int WINDOW_HEIGHT = 400;
    private Timer timer;

    public miniPongWaitingScreen() {
        setFocusable(true);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        addKeyListener(this);
        timer = new Timer(10, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Ball
        g.setColor(Color.WHITE);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        // Paddles
        g.fillRect(10, paddle1Y, PADDLE_WIDTH, PADDLE_HEIGHT);
        g.fillRect(WINDOW_WIDTH - 20, paddle2Y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveBall();
        moveAIPaddle();
        repaint();
    }

    private void moveBall() {
        ballPosX += ballDirX;
        ballPosY += ballDirY;

        if (ballPosY <= 0 || ballPosY >= WINDOW_HEIGHT - 20) { // Adjust for new window height
            ballDirY = -ballDirY;
        }

        if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(10, paddle1Y, PADDLE_WIDTH, PADDLE_HEIGHT))
                || new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(WINDOW_WIDTH - 20, paddle2Y, PADDLE_WIDTH, PADDLE_HEIGHT))) {
            ballDirX = -ballDirX;
        }
    }

    private void moveAIPaddle() {
        if (ballPosX > WINDOW_WIDTH / 2) { // AI reacts when the ball is in its half
            if (ballPosY < paddle2Y && paddle2Y > 0) {
                paddle2Y -= 8; // Move up
            } else if (ballPosY > paddle2Y + PADDLE_HEIGHT && paddle2Y < (WINDOW_HEIGHT - PADDLE_HEIGHT)) {
                paddle2Y += 8; // Move down
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W && paddle1Y > 0) {
            paddle1Y -= 15;
        }
        if (e.getKeyCode() == KeyEvent.VK_S && paddle1Y < (WINDOW_HEIGHT - PADDLE_HEIGHT)) {
            paddle1Y += 15;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Pong with AI");
        miniPongWaitingScreen pong = new miniPongWaitingScreen();
        frame.add(pong);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
