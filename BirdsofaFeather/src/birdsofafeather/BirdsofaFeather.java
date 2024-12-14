package birdsofafeather;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BirdsofaFeather extends JFrame{
    private JLabel lyricsLabel;
    private String[] lyrics = {
        "Birds of a feather, we should stick together, I know",
        "I said I'd never think I wasn't better alone",
        "Can't change the weather, might not be forever",
        "But if it's forever, it's even better",
        "And I don't know what I'm cryin' for",
        "I don't think I could love you more",
        "It might not be long, but baby, I",
        "I'll love you 'til the day that I die",
        "'Til the day that I die",
        "'Til the light leaves my eyes",
        "'Til the day that I die"
    };
    private int index = 0;

    public BirdsofaFeather() {
        setTitle("BirdsofaFeather Lyrics");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        lyricsLabel = new JLabel("", SwingConstants.CENTER);
        lyricsLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        add(lyricsLabel, BorderLayout.CENTER);

        Timer timer = new Timer(4500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < lyrics.length) {
                    fadeOut(lyricsLabel, () -> {
                        displayLyrics(lyrics[index]);
                        fadeIn(lyricsLabel);
                        index++;
                    });
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    private void displayLyrics(String lyric) {
        lyricsLabel.setText(lyric);
        lyricsLabel.setForeground(new Color(0, 0, 0, 0));
    }

    private void fadeOut(JLabel label, Runnable onComplete) {
        Timer fadeOutTimer = new Timer(30, new ActionListener() {
            int alpha = 255;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (alpha > 0) {
                    label.setForeground(new Color(0, 0, 0, alpha));
                    alpha -= 15; 
                } else {
                    ((Timer) e.getSource()).stop();
                    onComplete.run();
                }
            }
        });
        fadeOutTimer.start();
    }

    private void fadeIn(JLabel label) {
        Timer fadeInTimer = new Timer(30, new ActionListener() {
            int alpha = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (alpha < 255) {
                    label.setForeground(new Color(0, 0, 0, alpha));
                    alpha += 15;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        fadeInTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BirdsofaFeather frame = new BirdsofaFeather();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setLocation(frame.getX(), Toolkit.getDefaultToolkit().getScreenSize().height - frame.getHeight());
        });
    }
}
