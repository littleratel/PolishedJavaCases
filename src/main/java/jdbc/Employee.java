package jdbc;

/**
 * model包下的employee类,对每个字段进行建模
 */
public class Employee {
	private int id;
	private String name;
	private String account;
	private String phone;
	private String email;
	private String address;

	/**
	 * 带有6个参数的构造方法
	 */
	public Employee(int id, String name, String account, String phone, String email, String address) {
		this.id = id;
		this.name = name;
		this.account = account;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	/**
	 * 重写toString()方法
	 */
	@Override
	public String toString() {
		return "Employee:[id=" + id + ", userName=" + name + ", account=" + account + ", phone=" + phone + ", email="
				+ email + ", address=" + address + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
