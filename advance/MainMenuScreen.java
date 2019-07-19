package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Sprites.Tmenu;

public class MainMenuScreen implements Screen {
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private MyGdxGame game;
    private boolean t=true;
    Texture img;
    public  static Sprite sprite,highscoresprite,loadgamesprite,newgamesprite,stagesprite,exitsprite,bgsprite,coversprite,horsesprite, ubb;
    SpriteBatch batch;
    Integer pos = 1,los=1;
    PlayScreen playScreen;

    public MainMenuScreen(final MyGdxGame game)
    {
        this.game=game;
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new StretchViewport(MyGdxGame.V_Width / MyGdxGame.PPM * 1.4f,MyGdxGame.V_Height / MyGdxGame.PPM*1.4f,gamecam);
        gamecam.position.set(gameport.getWorldWidth()/2.3f,gameport.getWorldHeight()/2.3f,0   );
        batch = new SpriteBatch();

        newgamesprite = new Sprite(new Texture("NewGame.jpg"));
        bgsprite = new Sprite(new Texture("back.jpg"));
        coversprite = new Sprite(new Texture("cover.png"));
        //sprite = new Sprite(new Texture("Menu/metalslug1.png"));
        loadgamesprite = new Sprite(new Texture("Tutorial.jpg"));
        exitsprite = new Sprite(new Texture("Exit.jpg"));
        horsesprite = new Sprite(new Texture("horse1.png"));

        newgamesprite.setPosition(60,100);
        //loadgamesprite.setPosition(60,250);
        loadgamesprite.setPosition(260,100);
        exitsprite.setPosition(460,100);
        coversprite.setPosition(newgamesprite.getX()-5,newgamesprite.getY()-5);
        horsesprite.setPosition(-180,-20);

        bgsprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        horsesprite.setScale(.2f);

        ubb= new Sprite(new Texture("U.jpg"));
        ubb.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


    }

    @Override
    public void show() {

    }

    public int handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            if (pos == 1) {
                playScreen = new PlayScreen(game);
                game.setScreen(playScreen);
            }
            if (pos == 3) {
                Gdx.app.exit();
                return 0;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            //los--;
            pos++;
            if (pos>3)
                pos =1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            pos--;
            if (pos<1)
                pos=3;
        }
        if (pos == 1)
            coversprite.setPosition(newgamesprite.getX() - 5, newgamesprite.getY() - 5);
        if (pos == 2)
            coversprite.setPosition(loadgamesprite.getX() - 5, loadgamesprite.getY() - 5);
        if (pos == 3) {
            coversprite.setPosition(exitsprite.getX() - 5, exitsprite.getY() - 5);

        }return 0;
    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.begin();
        bgsprite.draw(batch);
        newgamesprite.draw(batch);
        loadgamesprite.draw(batch);
        exitsprite.draw(batch);
        coversprite.draw(batch);
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
        {
            if (pos == 2) {
                    game.setScreen(new Tmenu(batch,game));
            }
        }
        horsesprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}