package com.enums;

public enum Role {

	TUTOR("TUTOR"),
	STUDENT("STUDENT"),
	ADMIN("ADMIN");

	private String roleVal;
	
	Role(String val) {
		this.roleVal = val;
	}
	
	public String getRole() {
		return this.roleVal;
	}
}
