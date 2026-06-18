// Note.java

/**
 * Note is an enum of piano notes that are
 * played when the corresponding piano key is pressed.
 *
 * @author Redeate Seife
 * @since 2026-05-15
 * @version 2.0
 */

public enum Note {

    // Note values
    C4(false), C_SHARP4(true), D4(false), D_SHARP4(true), E4(false), F4(false),
    F_SHARP4(true), G4(false), G_SHARP4(true), A4(false), A_SHARP4(true), B4(false);

    private static final Note[] NOTES = Note.values();
    private final boolean isSharp;    // State of note sharp


    /**
     * Internal enum constructor
     *
     * @param isSharp    true if sharp note, false if natural note
     */
    Note(boolean isSharp) {
        this.isSharp = isSharp;
    }

    /**
     * Fetches a note by its index.
     *
     * @param index    The index of the note.
     * @return         The note at that index.
     */
    public static Note getNote(int index) {
        return NOTES[index];
    }

    /**
     * Fetches the number of notes defined in the enum.
     *
     * @return The number of notes.
     */
    public static int getNoteCount() {
        return NOTES.length;
    }

    /**
     * Determines if note is sharp or natural
     *
     * @return isSharp    true if sharp note, false if natural note
     */
    public boolean isSharp() {
        return isSharp;
    }
}

