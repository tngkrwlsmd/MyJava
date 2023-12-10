package java7_5;

class Student {
  private String name, major; //이름, 학과
  private int num; //학번
  private double average; //학점 평균

  Student (String name, String major, int num, double average) {
    this.name = name; this.major = major; 
    this.num = num; this.average = average;
  }

  String getName() { return name; }
  String getMajor() { return major; }
  int getNum() { return num; }
  double getAverage() { return average; }
}
