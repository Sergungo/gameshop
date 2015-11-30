package com.golenko.gameshop.model;

import java.util.Date;

public class Game implements Comparable<Game> {
	private Long id;
	private String name;
	private String gamedev;
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGamedev() {
		return gamedev;
	}

	public void setGamedev(String autor) {
		this.gamedev = autor;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Game() {
		super();
	}

	public Game(String name, String gamedev, Date date) {
		super();
		this.name = name;
		this.gamedev = gamedev;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Game [name=" + name + ", gamedev=" + gamedev + "]";
	}

	@Override
	public int compareTo(Game o) {
		return this.name.compareTo(o.getName());
	}
	
	public boolean isNew() {
		return id == null;
	}
}
