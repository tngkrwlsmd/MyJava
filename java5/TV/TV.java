package TV;

class TV{
   private int size;
   public TV(int size) { this.size = size; }
   protected int getSize() { return size; }
}

class ColorTV extends TV {
  protected int color;
  ColorTV(int size, int color) { super(size); this.color = color; }
  protected int getColor() { return color; }
  void printProperty() {
    System.out.println(getSize() + "인치 " + color + "컬러");
  }
}

class IPTV extends ColorTV {
  protected String ip;
  IPTV (String ip, int size, int color) { super(size, color); this.ip = ip; }
  protected String getIP() { return ip; }
  void printProperty() {
    System.out.println("나의 IPTV는 " + getIP() + " 주소의 " 
      + getSize() + "인치 " + getColor() + "컬러");
  }
}

class TV_Exam {
  public static void main(String[] args) {
    IPTV iptv = new IPTV("192.1.1.2", 32, 2048); //"192.1.1.2" 주소에 32인치, 2048컬러
   iptv.printProperty();
  }
}

