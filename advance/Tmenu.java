package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screen.MainMenuScreen;

import static com.mygdx.game.Screen.MainMenuScreen.ubb;

public class Tmenu implements Screen {
    SpriteBatch batch;
    MyGdxGame game;

    public Tmenu(SpriteBatch batch, MyGdxGame game) {
        this.batch = batch;
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        ubb.draw(batch);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {

            game.setScreen(new MainMenuScreen(game));

        }
    }
    @Override
    public void resize ( int width, int height){


    }

    @Override
    public void pause () {


    }

        @Override
        public void resume () {

        }

        @Override
        public void hide () {

        }

        @Override
        public void dispose () {

        }



}