package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

	/**
	 * 1、通过驱动器管理器获取连接接口（Connection）。 
	 * 2、获得Statement或它的子类。 
	 * 3、指定Statement中的参数。
	 * 4、通过Statement发送SQL语句。 
	 * 5、检查并处理返回的结果。
	 * 6、关闭Statement。
	 * 7、关闭连接接。
	 */
	public static void main(String[] args) {
		String url = "jdbc:mysql://127.0.0.1:3306/ssm?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
		String driver = "com.mysql.cj.jdbc.Driver";
		String user = "root";
		String password = "123456";

		try {
			Class.forName(driver);
			Connection conn = (Connection) DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				System.out.println("Succeeded connecting to the Database!");
			}

			// 获得Statement
			Statement statement = (Statement) conn.createStatement();
			String sql = "select * from t_user where id < 200";
			ResultSet rs = statement.executeQuery(sql);

			System.out.println("--------------这里是展示-----------");
			while (rs.next()) {
				System.out.println(rs.getString("name") + " " + rs.getString("account"));
			}
			rs.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
