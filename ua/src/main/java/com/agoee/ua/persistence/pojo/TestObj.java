package com.agoee.ua.persistence.pojo;

import java.io.Serializable;

public class TestObj implements Serializable {

	private static final long serialVersionUID = -8806785348790167393L;

	private String name = "key";

	private String value = "value";

	public TestObj() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
