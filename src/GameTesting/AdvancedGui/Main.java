package GameTesting.AdvancedGui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Objects;

public class Main extends Canvas implements Runnable {

    public static int width = 512;
    public static int height = width / 16 * 9;

    public static int scale = 1;

    private boolean running = false;
    private final boolean resizable = false;
    private static final String gameTitle = "Game";

    private JFrame frame;
    private Thread gameThread;

    public static void main(String[] args) {
        Main main = new Main();

    }

    public Main() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        frame = new JFrame();
        frame.setResizable(resizable);
        frame.setTitle(gameTitle);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        start();
    }


    @Override
    public synchronized void run() {
        while (running) {
            render();
        }
    }

    private void update() {

    }

    private void render() {
        BufferStrategy strategy = getBufferStrategy();
        if (Objects.isNull(strategy)) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = strategy.getDrawGraphics();

        g.setColor(Color.GREEN);
        g.drawRect(0,0, getWidth(), getHeight());


        strategy.show();
        g.dispose();
    }

    public synchronized void start() {
        running = true;
        gameThread = new Thread(this, "MainGameThread");
        gameThread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
