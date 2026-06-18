// AudioEngine.java

/**
 * AudioEngine provides sound to JavaKeys.
 *
 * @author Redeate Seife
 * @since 2026-05-15
 * @version 2.0
 */

import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

public class AudioEngine {

    protected ArrayList<Clip> audioClips;

    public AudioEngine() {

        audioClips = new ArrayList<>();
        Note[] notes = Note.values();

        for(Note note : notes) {
            try {
                File noteFile = new File("audio/" + note + ".wav");
                AudioInputStream ais = AudioSystem.getAudioInputStream(noteFile);

                Clip audioClip = AudioSystem.getClip();
                audioClip.open(ais);
                audioClips.add(audioClip);
            }
            catch(IOException | LineUnavailableException | UnsupportedAudioFileException e) {e.printStackTrace();}
        }
    }


    /**
     * Plays the corresponding audio clip of
     * the piano note.
     *
     * @param note    Piano note to be played.
     */
    public void play(Note note) {

        Clip audioClip = audioClips.get(note.ordinal());    // Get corresponding piano note
        audioClip.setFramePosition(0);                      // Reset audio clip start position
        audioClip.start();                                  // Play piano note
    }
}

