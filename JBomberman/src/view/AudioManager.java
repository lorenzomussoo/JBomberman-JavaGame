package view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *La classe AudioManager è la classe che gestisce l'audio
 */
public class AudioManager {

	/**
	 * istanza di AudioManager
	 */
	private static AudioManager instance;

	
	/**
	 * Metodo che restituisce un istanza di AudioManager
	 * @return istanza di AudioManager
	 */
	public static AudioManager getInstance() { 
		if (instance == null) instance = new AudioManager();
		return instance;
	}
	
	/**
	 * Costruttore di AudioManager che crea un sistanza di AudioManager
	 */
	private AudioManager() {

	}

	/**
	 * metodo che esegue una traccia audio
	 * @param filename percorso al file audio
	 */
	public void play(String filename){

		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filename));
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
}