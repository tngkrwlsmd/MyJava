package java4.java4_3;

class Song {
  private String title, artist, country;
  private int year;
  Song() {
    this("title", "artist", 0000, "country");
  }
  Song(String title, String artist, int year, String country) {
    this.title = title;
    this.artist = artist;
    this.year =year;
    this.country = country;
  }
  void show() {
    System.out.println(year + "년 " + country + "국적의 " + artist + "가 부른 " + title);
  }
}

class java4_3 {
  public static void main(String[] args) {
    Song s = new Song("Dancing Queen", "ABBA", 1978, "스웨덴");
    s.show();
  }
}
