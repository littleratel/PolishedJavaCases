package cn.bin.serializable;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private transient String psw;
	// private String waihao = "default";

	public UserInfo(String name, String psw) {
		this.name = name;
		this.psw = psw;
	}

	// public void setWaihao(String waihao) {
	// this.waihao = waihao;
	// }
	// public String getWaihao() {
	// return waihao;
	// }

	public void setName(String name) {
		this.name = name;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String toString() {
		return "name=" + name + ", psw=" + psw;
	}
}