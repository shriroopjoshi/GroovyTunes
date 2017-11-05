package player;

import org.json.simple.*;
import java.util.*;
import com.opencsv.*;
import java.util.stream.Collectors;
import java.io.*;

public class Song{
	public String title;
	public String albumartist;
	public String album;
	public String genre;
	public double duration;
	public String songId;
	private static final String SONGS_DATABASE = "./databases/songs.csv";

	public Song(String title, String albumartist, String album, String genre, double duration){
		this.title = title;
		this.albumartist = albumartist;
		this.album = album;
		this.genre = genre;
		this.duration = duration;
		this.songId = UUID.randomUUID().toString();
	}

	public Song(String title, String albumartist, String album, String genre, double duration, String songId){
		this.title = title;
		this.albumartist = albumartist;
		this.album = album;
		this.genre = genre;
		this.duration = duration;
		this.songId = songId;
	}


	public JSONObject getMetaData(){
		JSONObject obj = new JSONObject();
		obj.put("title", this.title);
		obj.put("albumartist", this.albumartist);
		obj.put("album", this.album);
		obj.put("genre", this.genre);
		obj.put("duration", this.duration);
		obj.put("songId", this.songId);
		return obj;
	}


	public void addToDb(String path) throws Exception{
		CSVWriter writer = new CSVWriter(new FileWriter(SONGS_DATABASE, true));
		String[] record = (
			"songId," + songId +
			",title," + title +
			",albumartist," + albumartist +
			",album," + album +
			",genre," + genre +
			",duration," + duration +
			",path," + path.replace("\\", "\\\\")
		).split(",");
		writer.writeNext(record);
		writer.flush();
		writer.close();
	}



}