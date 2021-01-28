package com.korea.springdi;

public class Greeting {
	private final long id;
	private final String content;
	
	public Greeting(long id, String content) {
		super();
		System.out.println("Greeting Constructor");
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	// \t : 탭 하나 띄우는것
	public void printInfo() {
		System.out.println("id : " + id + "\tcontent : " + content);
	}
}
