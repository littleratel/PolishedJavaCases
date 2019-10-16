package cn.bin.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum UserStatus {
	normal(1),//正常
	forbidden(0);//禁用
	
	private static final Map<Integer,String> descMap = new HashMap<>();
	static{
		descMap.put(normal.id, "正常");
		descMap.put(forbidden.id, "禁用");
	}
	
	private Integer id;
	
	public int id(){
		return this.id;
	}
	
	private UserStatus(int id){
		this.id = id;
	}
}