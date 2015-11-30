package com.golenko.gameshop.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.golenko.gameshop.model.Game;

public class GameDAOTest {

	@Test
	public void testDao() {
		GameDAO.getInstance().deleteAll();
		assertEquals(0, GameDAO.getInstance().selectAll().size());

		Game b = new Game("abc", "bcd", new Date());
		GameDAO.getInstance().insert(b);

		List<Game> games = GameDAO.getInstance().selectAll();
		assertEquals(1, games.size());
		assertEquals(b.getGamedev(), games.get(0).getGamedev());
		assertEquals(b.getName(), games.get(0).getName());
		assertEquals(b.getDate(), games.get(0).getDate());
		GameDAO.getInstance().delete(games.get(0));
		assertEquals(0, GameDAO.getInstance().selectAll().size());

		GameDAO.getInstance().insert(b);
		GameDAO.getInstance().deleteAll();
		assertEquals(0, GameDAO.getInstance().selectAll().size());
	}
}
