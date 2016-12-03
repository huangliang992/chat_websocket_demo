package com.hainan.cs.singleton;

public class UserSingleton {
	private String username;
	private String password;
	private String userid;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private UserSingleton(){}
	private static UserSingleton instance;
	public static UserSingleton getInstance(){
		if(instance==null){
			instance=new UserSingleton();
		}
			return instance;
	}
}
