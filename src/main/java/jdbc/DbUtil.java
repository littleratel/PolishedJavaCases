package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {
	private static String url = "jdbc:mysql://127.0.0.1:3306/ssm";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String user = "root";
	private static String password = "123456";
	
	public static Connection getCon() throws Exception {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	public static void close(Statement stmt, Connection conn) throws Exception {
		if (stmt != null) {
			stmt.close();
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param cstmt
	 * @param conn
	 * @throws Exception
	 */
	public static void close(CallableStatement cstmt, Connection conn) throws Exception {
		if (cstmt != null) {
			cstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param pstmt
	 * @param conn
	 * @throws SQLException
	 */
	public static void close(PreparedStatement pstmt, Connection conn) throws SQLException {
		if (pstmt != null) {
			pstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 重载关闭方法
	 * 
	 * @param pstmt
	 * @param conn
	 * @throws Exception
	 */
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) throws Exception {
		if (rs != null) {
			rs.close();
			if (pstmt != null) {
				pstmt.close();
				if (conn != null) {
					conn.close();
				}

			}
		}

	}
}
