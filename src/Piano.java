// Piano.java

/**
 * Piano notes the keys.
 *
 * @author Redeate Seife
 * @since 2026-05-15
 * @version 2.0
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Piano extends JLayeredPane {

    // Instance variables
    private PianoHUD HUD;
    private ArrayList<PianoKey> pianoKeys;
    private int numberOfPianoKeys;
    private int width;     // Piano width
    private int height;    // Piano height


    public Piano() {
        this(Note.getNoteCount());
    }

    // Constructs a piano key for each note
    public Piano(int numberOfPianoKeys) {
        this.numberOfPianoKeys = numberOfPianoKeys;

        HUD = new PianoHUD();             // Initialize piano HUD
        pianoKeys = new ArrayList<>();    // Initialize piano keys array list

        // Declare black piano key data
        int numberOfBlackPianoKeys = 0;
        int blackPianoKeyWidth = 0;
        int blackPianoKeyHeight = 0;

        // Declare white piano key data
        int numberOfWhitePianoKeys = 0;
        int whitePianoKeyWidth = 0;
        int whitePianoKeyHeight = 0;

        // Array of keyboard keys to label piano keys
        String[] keyboardKeys = {
            "A", "W", "S", "E",
            "D", "F", "U", "J",
            "I", "K", "O", "L"
        };

        // Create a piano key for each note
        for(int i = 0; i < numberOfPianoKeys; i++) {

            Note note = Note.getNote(i);
            String keyboardKey = keyboardKeys[i];
            PianoKey pianoKey = new PianoKey(keyboardKey, note);

            // Get piano key data
            if (!note.isSharp()) {
                // White piano key
                whitePianoKeyWidth = pianoKey.getPreferredSize().width;
                whitePianoKeyHeight = pianoKey.getPreferredSize().height;
                numberOfWhitePianoKeys++;
                // pianoKeys.add(pianoKey);
            }
            else {
                // Black piano key
                blackPianoKeyWidth = pianoKey.getPreferredSize().width;
                blackPianoKeyHeight = pianoKey.getPreferredSize().height;
                numberOfBlackPianoKeys++;
                // pianoKeys.add(pianoKey);
            }

            pianoKeys.add(pianoKey);
        }

        // Calculate piano dimensions
        width = whitePianoKeyWidth * numberOfWhitePianoKeys;
        height = whitePianoKeyHeight;

        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        int whiteX = 0;    // x-coordinate of white piano key
        int y = 0;         // y-coordinate of piano keys

        for(int i = 0; i < numberOfPianoKeys; i++) {

            PianoKey pianoKey = pianoKeys.get(i);
            Note note = pianoKey.getNote();

            if(!note.isSharp()) {

                pianoKey.setBounds(whiteX, y, whitePianoKeyWidth, whitePianoKeyHeight);
                add(pianoKey, JLayeredPane.DEFAULT_LAYER);    // Add white piano key to piano

                whiteX += whitePianoKeyWidth;
            }

            if(note.isSharp()) {

                int blackX = whiteX - (blackPianoKeyWidth / 2);

                pianoKey.setBounds(blackX, y, blackPianoKeyWidth, blackPianoKeyHeight);
                add(pianoKey, JLayeredPane.PALETTE_LAYER);    // Add black piano key to piano
            }
        }
    }


    /**
     * Returns piano note HUD panel.
     *
     * @return    Piano note HUD panel.
     */
    public PianoHUD getHUD() {
        return HUD;
    }

    /**
     * Returns all the piano keys in the piano.
     *
     * @return    ArrayList of PianoKey objects.
     */
    public ArrayList<PianoKey> getPianoKeys() {
        return pianoKeys;
    }


    /**
     * Returns height of the piano.
     *
     * @return    Height of piano.
     */
    public int getHeight() {
        return height;
    }


    /**
     * Returns width of the piano.
     *
     * @return    Width of piano.
     */
    public int getWidth() {
        return width;
    }


    /**
     * Updates the piano note being displayed upon each
     * piano click or press.
     *
     * @param note    Piano note to output in piano HUD.
     */
    public void updateHUD(Note note) {
        this.HUD.setDisplayNote(note);
    }


    /**
     * Inner class - PianoHUD creates a heads-up display (HUD)
     * that outputs information to the user.
     *
     * @author Redeate Seife
     * @since 2026-05-16
     * @version 2.0
     */
    private class PianoHUD extends JPanel {

        // Instance variables
        private JPanel noteDisplay;
        private JLabel displayNote;

        public PianoHUD() {
            setLayout(new GridBagLayout());    // Set grid bag layout for HUD

            // Label for piano note
            displayNote = new JLabel("NOTE: ###", SwingConstants.CENTER);    // Create piano note label
            Dimension displayNoteSize = new Dimension(100, 25);    // Set size to reduce visual jittering
            displayNote.setPreferredSize(displayNoteSize);
            displayNote.setMinimumSize(displayNoteSize);
            displayNote.setMaximumSize(displayNoteSize);
            displayNote.setFont(JKT.PIANO_KEY);

            // Display panel for piano note label
            noteDisplay = new JPanel();
            noteDisplay.setBorder(BorderFactory.createEtchedBorder(JKT.BLUE, JKT.BLACK));
            noteDisplay.setBackground(JKT.GOLD);
            noteDisplay.add(displayNote);

            // Grid bag constraints for note display
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.anchor = GridBagConstraints.WEST;

            add(noteDisplay, gbc);    // Add to HUD
        }

        /**
         * Updates the piano note being displayed upon each
         * piano click or press.
         *
         * @param note    Piano note to output in piano HUD.
         */
        public void setDisplayNote(Note note) {

            int noteIndex = note.ordinal();

            String[] pianoNotes = {
                "C4", "C#4", "D4", "D#4",
                "E4", "F4", "F#4", "G4",
                "G#4", "A4", "A#4", "B4"
            };

            displayNote.setText("NOTE: " + pianoNotes[noteIndex]);
        }
    }
}

















