import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JButton;

public class Window extends JFrame {

    private static final long serialVersionUID = -2545695383117923190L;
    private static Timer timer;
    private static JButton button;

    public Window(int x, int y, int width, int height, String title) {

        this.setSize(width, height);
        this.setLocation(x, y);
        this.setTitle(title);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        timer = new Timer();
        timer.schedule(new TimerTick(), 35);

        button = new JButton("Button");
        button.setVisible(true);
        button.setLocation(50, 50);
        button.setSize(120, 35);
        this.add(button);
    }

    public void gameLoop() {

        // Button does not move on timer tick.
        button.setLocation( button.getLocation().x + 1, button.getLocation().y );

    }

    public class TimerTick extends TimerTask {

        @Override
        public void run() {
            gameLoop();
        }
    }
}