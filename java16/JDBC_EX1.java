package java16;
import java.sql.*;

public class JDBC_EX1 {
  public static void main(String[] args) {
    String url = "jdbc:oracle:thin:@localhost:1521/orcl";
    String user = "C##owner";
    String password = "1234";
    
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection conn =  DriverManager.getConnection(url, user, password);
      System.out.println("DB 연결 완료");
      conn.close();
    } catch (ClassNotFoundException e) {
      System.out.println("JDBC 드라이버 로드 에러");
    } catch (SQLException e) {
      System.out.println("DB 연결 에러");
    }
  }
}
