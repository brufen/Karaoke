import java.io.FileNotFoundException;
import java.io.IOException;

import model.Song;
import model.SongBook;
import ui.KaraokeMachine;


public class Karaoke {

	private static final String SONG_TXT = "song.txt";

	public static void main(String[] args) {
		//Song song = new Song("Michael Jackson", "Beat it", "https://www.youtube.com/watch?v=pvdrN3GJFZo");
		SongBook songBook = new SongBook();
		songBook.importFile(SONG_TXT);
		//System.out.printf("Adding %s %n", song);
		//songBook.addSong(song);
		//System.out.printf("There are %d songs", songBook.getSongCount());
		KaraokeMachine machine = new KaraokeMachine(songBook);
		machine.run();
		System.out.println("Saving song book....");
		try {
			songBook.exportTo(SONG_TXT);
		} catch (FileNotFoundException e) {
			System.out.println("File song.txt not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
