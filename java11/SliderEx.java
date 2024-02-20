package java11;
import javax.swing.*;
import java.awt.*;

public class SliderEx extends JFrame {
  public SliderEx() {
    setTitle("슬라이더 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());

    //수평 슬라이더 생성. 0 ~ 200까지 선택 가능하며, 초기값 100
    JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
    slider.setPaintLabels(true); //슬라이더의 라벨(눈금에 새겨진 문자열)
    slider.setPaintTicks(true); // 슬라이더의 눈금
    slider.setPaintTrack(true); //슬라이더의 트랙(bar)
    slider.setMajorTickSpacing(50); //큰 눈금 간격
    slider.setMinorTickSpacing(10); //작은 눈금 간격
    c.add(slider);

    setSize(300, 100);
    setVisible(true);
  }

  public static void main(String[] args) {
    new SliderEx();
  }
}
