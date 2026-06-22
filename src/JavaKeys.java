import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JavaKeys extends JFrame {

    // Instance variables
    private static long startTime;    // Program start time
    private static long endTime;      // Program end time

    public static void main(String[] args) {
        startTimer();    // Start run time timer
        SwingUtilities.invokeLater(() -> {
            initJavaKeys();    // Initialize JavaKeys
        });
    }

    public JavaKeys() {
        this("JAVA KEYS");    // Call 1-arg constructor
    }

    public JavaKeys(String title) {
        super(title);    // Frame title
        
        Piano piano = new Piano();                                                    // Create piano
        JPanel pianoHUD = piano.getHUD();                                             // Get piano HUD
        AudioEngine audioEngine = new AudioEngine();                                  // Create audio engine
        InputController inputController = new InputController(piano, audioEngine);    // Create input controller

        add(piano, BorderLayout.CENTER);                   // Add piano to frame center
        add(pianoHUD, BorderLayout.NORTH);                 // Add piano HUD to frame north
        pack();                                            // Pack frame around contents
        setLocationRelativeTo(null);                       // Place frame at center of screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Program exit on close
        setVisible(true);                                  // Make frame visible

        piano.requestFocusInWindow();    // Give piano window focus

        endTimer();    // End run time timer
        runTime();     // Output run time
    }

    public static void startTimer() {
        startTime = System.nanoTime();    // Initialize program start time
    }

    public static void endTimer() {
        endTime = System.nanoTime();    // Initialize program end time
    }

    public static void runTime() {
        long durationInNanoseconds = endTime - startTime;                       // Calculate run time (ns)
        double durationInMilliseconds = durationInNanoseconds / 1_000_000.0;    // Convert run time (ms)
        double durationInSeconds = durationInMilliseconds / 1_000.0;            // Convert run time (s)

        System.out.printf("\n%4sRun time:\n\t%.2f s\n\t%.2f ms\n\n", "", durationInSeconds, durationInMilliseconds);    // Run time
    }

    public static JavaKeys initJavaKeys() {
        return new JavaKeys();    // Create JavaKeys
    }
}
