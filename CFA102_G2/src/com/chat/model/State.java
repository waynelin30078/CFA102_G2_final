package com.chat.model;

import java.util.Set;

//跟VO很像, 不是對到資料庫, 但都是拿來包裝資料重複使用

public class State {
	private String type;  //action, 識別上線, 下線等動作
	// the user changing the state
	private String user;
	// total users
	private Set<String> users;  //在線使用者
	private String daccount;

	public State(String type, String user, Set<String> users) {
		super();
		this.type = type;
		this.user = user;
		this.users = users;
	}

	public State(String type, String user, String daccount) {
		super();
		this.type = type;
		this.user = user;
		this.daccount = daccount;
	}
	
	
	public String getDaccount() {
		return daccount;
	}

	public void setDaccount(String daccount) {
		this.daccount = daccount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Set<String> getUsers() {
		return users;
	}

	public void setUsers(Set<String> users) {
		this.users = users;
	}

}
