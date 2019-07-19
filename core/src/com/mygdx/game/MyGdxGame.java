package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screen.MainMenuScreen;

public class MyGdxGame extends Game {
	public static final int V_Width=400;
	public static final int V_Height=208;
	public static final float PPM=100;
	//public static int HEIGHT=480  ;
	//public static int WIDTH=720;
	public SpriteBatch batch;
	public MainMenuScreen menuScreen;

	@Override
	public void create () {
		menuScreen=new MainMenuScreen(this);
		batch = new SpriteBatch();
		setScreen(menuScreen);
	}

	@Override
	public void render () {
		super.render();
	}

}