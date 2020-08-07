package tech.codingclub.utility;

public class SongResult {
    String album;
    String duration;
    String singers;
    String lyricist;
    String music_director;
   public SongResult()
   {

   }
    public SongResult(String album, String duration, String singers, String lyricist, String music_director) {
        this.album = album;
        this.duration = duration;
        this.singers = singers;
        this.lyricist = lyricist;
        this.music_director = music_director;
    }
}
