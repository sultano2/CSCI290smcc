/**
 * File: SongCollection.java
 ************************************************************************
 *                     Revision History (newest first)
 ************************************************************************
 * 
 * 8.2016 - Anne Applin - formatting and JavaDoc skeletons added   
 * 2015 -   Prof. Bob Boothe - Starting code and main for testing  
 ************************************************************************
 */

 package student;
 
import java.io.*;
import java.util.*;
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

        ArrayList<Song> songList = new ArrayList<>(); ////task2
        
        try { 
            Scanner sc = new Scanner (new File(filename));
            sc.useDelimiter("\"");
            
               while (sc.hasNext()) {   ///// Task 2
                
                sc.next();
                if (!sc.hasNext()) break;
                String artist = sc.next();
                
                sc.next();
                if (!sc.hasNext()) break;
                String title = sc.next();
                
                sc.next();
                if (!sc.hasNext()) break;
                String lyrics = sc.next();
                
                    songList.add (new Song(artist, title, lyrics));
                
              
            }
            sc.close();
            
        }  catch (FileNotFoundException e) {
            System.out.println("EROROROROOROOROROROROROROROOROROR" + filename);
        }
        
	// use a try catch block
        // read in the song file and build the songs array
        // there are several ways to read in the lyrics correctly.  
        // the line feeds between lines and the blank lines between verses
        // must be retained.
        
          songs = songList.toArray(new Song[0]);
                                                        ///task22
        ///// Task 2
        // sort the array
        Arrays.sort(songs);
        
        
    }
 
    /**
     * this is used as the data source for building other data structures
     * @return the songs array
     */
    public Song[] getAllSongs() {
        return songs;
    }
 
    /**
     * unit testing method
     * @param args
     */
    public static void main(String[] args) {
         SongCollection collection = new SongCollection(args[0]);
        Song[] list = collection.getAllSongs();                             ////taksk 2

        
        System.out.println("total songs: " + list.length);

        
        Stream.of(list)
              .limit(10)
              .forEach(System.out::println);
    }
}
