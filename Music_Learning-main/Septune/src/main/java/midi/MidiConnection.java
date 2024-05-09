package main.java.midi;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;
import javax.sound.midi.MidiDevice.*;


/** ConnexionMidi établit la connexion avec un keyboard midi externe. Il permet de récupérer et de rediriger les informations midi.
 * @author  Lucas Bolbènes
 */
public class MidiConnection extends Thread
{
	/** keyboard auquel la connexion est associée */
	private Keyboard keyboard;
	
    /** Périphérique représentant notre keyboard MIDI */
    private MidiDevice keyboardDevice;

    /** Emetteur associé à notre keyboard MIDI qui sera utilisé pour afficher et transmettre les notes jouées */
    private Transmitter transmitter;
    
    /** Emetteur associé à notre keyboard MIDI qui sera utilisé avec pour produire du son à l'aide d'un Synthesizer */
    private Transmitter transmitterSynth;

    /** Synthetiseur par défaut */
    private Synthesizer synth;
    
    /**
     * Construction d'une ConnexionMidi à partir d'une interaction avec l'utilisateur. 
     * mode 1 : choix du clavier via la console
     * mode 0 : choix automatique du clavier, on part de la fin et on prend le premier device possédant un émetteur
     */
    MidiConnection(Keyboard keyboard, int mode)
    {

    	try {
    		synth = MidiSystem.getSynthesizer();
	    	this.keyboard = keyboard;
	    	
	        // On récupère les différents périphériques
	        Info[] infos = MidiSystem.getMidiDeviceInfo();
	        MidiDevice device;

			for(int i=0;i<infos.length;i++)
			{
				// Récupération du périphérique courant
				device = MidiSystem.getMidiDevice(infos[i]);
	
				// Affichage de la description du périphérique courant
				//System.out.print(i + " : " + infos[i].getName() + " - " + infos[i].getDescription());
	
				// Le périphérique courant a-t-il un émétteur ?
				if(device.getMaxTransmitters()!=0) 
				{
					//System.out.print(" : Ce peripherique possede un emetteur.");
				}
	
				//System.out.println("");
			}
		
	        switch(mode) {
    			case 1: 	
			
			        // Choix du périphérique
			        System.out.println("\nVeuillez choisir le peripherique correspondant a votre keyboard MIDI externe. (Il doit posseder un emetteur.)");
			        Scanner sc = new Scanner(System.in);
		            int choice = sc.nextInt();
		            System.out.println("Vous avez choisi le peripherique : "+infos[choice].getName());
		
		            // Récupération du keyboard externe
		            keyboardDevice = MidiSystem.getMidiDevice(infos[choice]);
		            sc.close();
		            
		            break;
		            
    			case 0 :
    	    		
					List<String> wellKnownNames = Arrays.asList("UM-ONE", "Arturia KeyStep 37");
					keyboardDevice = null;

					int i = 0;
					while(i < infos.length && !(wellKnownNames.contains(infos[i].getName()) && MidiSystem.getMidiDevice(infos[i]).getMaxTransmitters() != 0)) {
						i++;
					}

					if(i == infos.length) {
						System.out.println("Aucun des périphériques actuels n'est connu.");
						i = 0;
						while(i < infos.length && !(MidiSystem.getMidiDevice(infos[i]).getMaxTransmitters() != 0)) {
							i++;
						}

						if(i == infos.length) {
							System.out.println("Aucun périphérique MIDI ne possède d'émetteur.");
							System.exit(0);
						}

					} else {
						System.out.println("Périphérique connu trouvé : " + infos[i].getName());
					}

					keyboardDevice = MidiSystem.getMidiDevice(infos[i]);


					System.out.println("Périphérique choisi : " + infos[i].getName());

					break;

					
    	    		
		    	} 

				
		    	
    	} catch (MidiUnavailableException e) {
    		e.printStackTrace();
    	}
        
    } 
    
    MidiConnection(Keyboard keyboard) {
    	this(keyboard, 0);
    }

    public void openConnection() 
    {
    	try {
	    	// On récupère l'émetteur de notre périphérique dans nos deux attributs de type Transmitter
	        transmitter = keyboardDevice.getTransmitter();
	        transmitterSynth = keyboardDevice.getTransmitter();
	
	        // On associe un récepteur défini par notre classe RecepteurMidi au premier émetteur
	        transmitter.setReceiver(new MidiReceiver(keyboard));
	
	        // On ouvre le synthé
	        synth.open();
	
	        // On associe le récepteur du synthé à notre second émetteur
	        transmitterSynth.setReceiver(synth.getReceiver());
	        
	        keyboardDevice.open();
	        
	        System.out.println("Connexion MIDI etablie.");
    	} catch (MidiUnavailableException e) {
    	}
    }
    
    public void closeConnection() 
    {
        keyboardDevice.close();
		System.out.println("Fermeture de la connexion MIDI.");
    }
}
