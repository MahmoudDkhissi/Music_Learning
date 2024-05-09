package main.java.midi;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/** Cette classe implémente la classe Receiver, elle est utilisée
 *  pour récupérer les données produites par un clavier midi externe
 *  et de les rediriger correctement
 */
public class MidiReceiver implements Receiver
{
    private Keyboard keyboard;

    /** Construit un récepteur midi, récupère un gestionnaire de notes dont 
     *  on modifiera l'état en fonction des données récupérées
     */
    public MidiReceiver(Keyboard keyboard)
    {
        this.keyboard = keyboard;
    }

    @Override
    public void send(MidiMessage message, long timeStamp) 
    {
        if(message instanceof ShortMessage) 
        {
            ShortMessage sm = (ShortMessage) message;
            //int channel = sm.getChannel();
            
            if (sm.getCommand() == ShortMessage.NOTE_ON) 
            {
                int key = sm.getData1();
                int velocity = sm.getData2();
                
                if(velocity == 0)
                {
                    keyboard.update(key, false);
                }
                else
                {
                    keyboard.update(key, true);
                }
            } 
            else if (sm.getCommand() == ShortMessage.NOTE_OFF) 
            {

                int key = sm.getData1();
                //int velocity = sm.getData2();
                
                keyboard.update(key, false);
            } 
        }
    }

    @Override
    public void close() {}
}