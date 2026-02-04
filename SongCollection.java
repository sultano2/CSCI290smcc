
package student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * SongCollection.java 
 * Reads the specified data file and build an array of songs.
 * @author boothe
 */
public class SongCollection {

    private Song[] songs;

    /**
     * Note: in any other language, reading input inside a class is simply not
     * done!! No I/O inside classes because you would normally provide
     * precompiled classes and I/O is OS and Machine dependent and therefore 
     * not portable. Java runs on a virtual machine that IS portable. So this 
     * is permissable because we are programming in Java and Java runs on a 
     * virtual machine not directly on the hardware.
     *
     * @param filename The path and filename to the datafile that we are using
     * must be set in the Project Properties as an argument.
     */
    public SongCollection(String filename) {

        ArrayList<Song> list = new ArrayList<>();

        try {
            String data = Files.readString(Path.of(filename));
            // Splitting by quotes to capture fields between them
            String[] parts = data.split("\"");

            for (int i = 1; i < parts.length; i += 6) {
                String artist = parts[i];
                String title = parts[i + 2];
                String lyrics = parts[i + 4];
                list.add(new Song(artist, title, lyrics));
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        songs = list.toArray(new Song[0]);
        Arrays.sort(songs);
    }

    /**
     * unit testing method
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("usage: prog songfile");
            return;
        }

        SongCollection sc = new SongCollection(args[0]);

        // show song count and first 10 songs
        System.out.println("Total songs: " + sc.songs.length);
        Stream.of(sc.songs).limit(10).forEach(System.out::println);
    }
}