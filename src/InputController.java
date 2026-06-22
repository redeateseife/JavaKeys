import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class InputController {

    private Piano piano;
    private AudioEngine audioEngine;

    public InputController(Piano piano, AudioEngine audioEngine) {
        this.piano = piano;
        this.audioEngine = audioEngine;

        ArrayList<PianoKey> pianoKeys = piano.getPianoKeys();

        String inputKeys = "AWSEDFUJIKOL";
        InputMap inputMap = piano.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = piano.getActionMap();

        for(int i = 0; i < pianoKeys.size(); i++) {
            PianoKey pianoKey = pianoKeys.get(i);
            Note note = pianoKey.getNote();
            char inputKey = inputKeys.charAt(i);

            // Action on <MOUSE CLICKED> and <MOUSE RELASED>
            pianoKey.addMouseListener(new MouseAdapter() {

                public void mousePressed(MouseEvent e) {
                    if(!pianoKey.isClicked()) {
                        pianoKey.setClicked(true);
                        audioEngine.play(note);
                        piano.updateHUD(note);
                    }
                }

                public void mouseReleased(MouseEvent e) { pianoKey.setClicked(false); }
            });

            // Action on <KEY PRESSED>
            KeyStroke press = KeyStroke.getKeyStroke("pressed " + inputKey);
            String pressId = "PRESS_" + inputKey;
            inputMap.put(press, pressId);
            actionMap.put(pressId, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!pianoKey.isPressed()) {
                        pianoKey.setPressed(true);
                        audioEngine.play(note);
                        piano.updateHUD(note);
                    }
                }
            });

            // Action on <KEY RELEASED>
            KeyStroke release = KeyStroke.getKeyStroke("released " + inputKey);
            String releaseId = "RELEASE_" + inputKey;
            inputMap.put(release, releaseId);
            actionMap.put(releaseId, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) { pianoKey.setPressed(false); }
            });
        }
    }
}







