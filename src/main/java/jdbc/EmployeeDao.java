package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EmployeeDao {

	public static void main(String[] args) throws Exception {
		int id = 4;
		String name = "Amy Yu";
		String account = "amy";
		String phone = "13218188181";
		String email = "amy.yu@ecrisson.com";
		String address = "Qing Pu";
		Employee employee = new Employee(id, name, account, phone, email, address);

		int result = updateData(employee);
		System.out.print(result);
	}

	/**
	 * 对employee表添加数据
	 */
	public static int addData(Employee employee) throws Exception {
		Connection conn = DbUtil.getCon();
		String sql = "insert into t_user(`name`,`account`,`phone`,`email`,`address`) values(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getAccount());
		pstmt.setString(3, employee.getPhone());
		pstmt.setString(4, employee.getEmail());
		pstmt.setString(5, employee.getAddress());

		int result = pstmt.executeUpdate();
		DbUtil.close(pstmt, conn);
		return result;
	}

	/**
	 * 对数据库表内容进行修改
	 */
	public static int updateData(Employee employee) throws Exception {
		Connection conn = DbUtil.getCon();
		String sql = "update t_user set name=?,account=?,phone=?,email=?,address=? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getAccount());
		pstmt.setString(3, employee.getPhone());
		pstmt.setString(4, employee.getEmail());
		pstmt.setString(5, employee.getAddress());
		pstmt.setInt(6, employee.getId());

		int result = pstmt.executeUpdate();
		DbUtil.close(pstmt, conn);
		return result;
	}

}
