import java.awt.*;
import javax.swing.*;

public class PianoKey extends JButton {

    // Instance variables
    private Note note;
    private Color color;
    private boolean pressed;    // Piano key state of being pressed.
    private boolean clicked;


    /**
     * Constructs a piano key with the default 'C' note.
     */
    public PianoKey() {
        this("A", Note.C4);
    }

    /**
     * Constructs a piano key with the passed note.
     *
     * @param note    Note of the piano key
     */
    public PianoKey(String keyboardKey, Note note) {
        super(keyboardKey);
        this.note = note;

        // Color and dimensions
        if (note.isSharp()) {

            // Black piano key
            setPreferredSize(new Dimension(37, 200));    // width, height
            color = JKT.BLACK;
            setForeground(JKT.WHITE);
        }
        else {

            // White piano key
            setPreferredSize(new Dimension(65, 300));    // width, height
            color = (JKT.WHITE);
            setForeground(JKT.BLACK);
        }

        // Other piano key attributes
        setBorder(BorderFactory.createEtchedBorder(JKT.SILVER, JKT.BLACK));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.BOTTOM);
        setFont(JKT.PIANO_KEY);
        setBackground(color);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(true);
    }


    /**
     * Returns the note of the piano key.
     *
     * @return    Note of the piano key
     */
    public Note getNote() {
        return note;
    }


    /**
     * Returns whether the piano key is clicked or not.
     *
     * @return    true if piano key clicked, false otherwise.
     */
    public boolean isClicked() {
        return clicked;
    }


    /**
     * Returns whether the piano key is pressed or not.
     *
     * @return    true if piano key pressed, false otherwise.
     */
    public boolean isPressed() {
        return pressed;
    }


    /**
     * Sets the note of the piano key
     * to the note passed.
     *
     * @param    Note to set piano key to
     */
    public void setNote(Note note) {
        this.note = note;
    }


    /**
     * Changes piano key color while the piano key is clicked,
     * serving as visual feedback of a piano key click.
     *
     * @param clicked    true if clicked, false otherwise
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;

        if(clicked || pressed) { setBackground(JKT.GOLD); }
        else { setBackground(color); }
        repaint();
    }


    /**
     * Changes piano key color while the piano key is pressed,
     * serving as visual feedback of a piano key press.
     *
     * @param pressed    true if pressed, false otherwise
     */
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
        if(pressed || clicked) { setBackground(JKT.GOLD); }
        else { setBackground(color); }
        repaint();
    }
}

