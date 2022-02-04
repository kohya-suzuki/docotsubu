package model;

import java.io.Serializable;

public class User implements Serializable {
	private String name;
	private String pass;

	public User() {

	}

	public User(String name, String pass) {
		super();
		this.pass = pass;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}
}
