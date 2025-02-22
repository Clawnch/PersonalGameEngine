package GameTesting.AdvancedGui;

import GameTesting.AdvancedGui.PongGame.Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Main extends Canvas implements Runnable {

    public static int width = 768;
    public static int height = width / 16 * 9;

    public static int scale = 1;

    private boolean running = false;
    private final boolean resizable = false;
    private static final String gameTitle = "Game";
    private BufferedImage image;
    private int[] pixels;

    //private Screen screen;

    private JFrame frame;
    private Thread gameThread;

    private long lastUpdate = 0L, lastRender = 0L;
    private static float upsLimit = 1f / 30;
    private static int fpsLimit = 120;

    private Pong pong;

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

        createBufferStrategy(3);

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        //screen = new Screen(width, height);

        pong = new Pong(getWidth(), getHeight());

        lastUpdate = System.currentTimeMillis();
        lastRender = System.currentTimeMillis();

        start();
    }


    @Override
    public synchronized void run() {
        while (running) {

            long currentTime = System.currentTimeMillis();
            if ((currentTime - lastUpdate) >= (1000 * upsLimit)) {
                update();
                lastUpdate = currentTime;
            }
            if ((currentTime - lastRender) >= (1000 / fpsLimit)) {
                render();
                lastRender = currentTime;
            }
        }
    }

    private void update() {
        pong.update();
    }

    private void render() {
        BufferStrategy strategy = getBufferStrategy();

        Graphics g = strategy.getDrawGraphics();
        
//        screen.render();
//
//        for (int i = 0; i < pixels.length; i++) {
//            pixels[i] = screen.getPixels()[i];
//        }
        pong.render(pixels);


        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();
        strategy.show();
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
