package java16;
import java.sql.*;

public class JDBC_EX2 {
  public static void main(String[] args) {
    String url = "jdbc:oracle:thin:@localhost:1521/orcl";
    String user = "C##owner";
    String password = "1234";
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection conn = DriverManager.getConnection(url, user, password);
      System.out.println("DB 연결 완료");
      Statement stmt = conn.createStatement();
      ResultSet srs = stmt.executeQuery("select * from 고객");
      printData(srs, "고객아이디", "고객이름", "나이", "등급", "직업", "적립금");
      srs = stmt.executeQuery("select * from 고객 where 나이 >= 30");
      printData(srs, "고객아이디", "고객이름", "나이", "등급", "직업", "적립금");
    } catch (ClassNotFoundException e) {
      System.out.println("JDBC 드라이버 로드 에러");
    } catch (SQLException e) {
      System.out.println("SQL 실행 에러");
    }
  }

  private static void printData(ResultSet srs, String... columns) throws SQLException {
    while (srs.next()) {
        for (String column : columns) {
            String value = srs.getString(column);
            System.out.printf("%-12s", value);
        }
        System.out.println();
    }
    System.out.println();
  }
}
