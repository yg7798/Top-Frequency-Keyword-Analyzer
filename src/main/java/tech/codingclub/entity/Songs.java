package tech.codingclub.entity;

public class Songs {
   public String parentURL;
    public String URL;
    public String album;
    public String duration;
    public String singers;
   public String lyricist;
   public String music_director;
   public String download_128;
    public String getDownload_256;
    public String imageURL;

    public Songs(String parentURL, String URL, String album, String duration, String singers, String lyricist, String music_director, String download_128, String getDownload_256, String imageURL) {
        this.parentURL = parentURL;
        this.URL = URL;
        this.album = album;
        this.duration = duration;
        this.singers = singers;
        this.lyricist = lyricist;
        this.music_director = music_director;
        this.download_128 = download_128;
        this.getDownload_256 = getDownload_256;
        this.imageURL = imageURL;
    }
    public Songs()
    {

    }
}
