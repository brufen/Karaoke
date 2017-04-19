package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SongBook {
	private List<Song> mSongs;

	public SongBook() {
		mSongs = new ArrayList<Song>();
	}

	public void exportTo(String fileName) throws FileNotFoundException, IOException {
		try (FileOutputStream fos = new FileOutputStream(fileName); PrintWriter writer = new PrintWriter(fos);) {
			for (Song song : mSongs) {
				writer.printf("%s|%s|%s%n", song.getmArtist(), song.getmTitle(), song.getmVideoUrl());
			}
		}
	}

	public void importFile(String fileName) {
		try (FileInputStream fis = new FileInputStream(fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] arrStrings = line.split("\\|");
				addSong(new Song(arrStrings[0], arrStrings[1], arrStrings[2]));
			}
		} catch (IOException ioe) {
			System.out.printf("Problems loading %s %n", fileName);
			ioe.printStackTrace();
		}
	}

	public void addSong(Song song) {
		mSongs.add(song);
	}

	public int getSongCount() {
		return mSongs.size();
	}

	// FIXME: This should be cached
	private Map<String, List<Song>> byArtist() {
		Map<String, List<Song>> byArtist = new HashMap<>();
		for (Song song : mSongs) {
			List<Song> artistSongs = byArtist.get(song.getmArtist());
			if (artistSongs == null) {
				artistSongs = new ArrayList<>();
				byArtist.put(song.getmArtist(), artistSongs);

			}
			artistSongs.add(song);
		}
		return byArtist;
	}

	public Set<String> getArtists() {
		return byArtist().keySet();
	}

	public List<Song> getSongsForArtist(String artistName) {
		List<Song> songs = byArtist().get(artistName);
		songs.sort(new Comparator<Song>() {

			@Override
			public int compare(Song song1, Song song2) {
				if (song1.equals(song2)) {
					return 0;
				}
				
				return song1.mTitle.compareTo(song2.mTitle);
			}
			
		});
				
		return songs;
	}

}
