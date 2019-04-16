package com.greencode.game;


import com.badlogic.gdx.Game;
import com.greencode.game.screen.MenuScreen;

public class GameShooter extends Game {

	@Override
	public void create () {
		setScreen(new MenuScreen(this));

	}

}
