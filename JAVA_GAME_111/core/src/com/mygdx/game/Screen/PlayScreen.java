package com.mygdx.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Panda;

public class PlayScreen implements Screen {

    private MyGdxGame game;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    //sprites
    private Panda player;



    public PlayScreen(MyGdxGame game) {
        this.game = game;
    //create cam used to follow mario through cam world
        gamecam = new OrthographicCamera();
    //create a fitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(MyGdxGame.V_Width/MyGdxGame.PPM, MyGdxGame.V_Height/MyGdxGame.PPM, gamecam);

    // create our game HUD for scores/times/level info
        hud = new Hud(game.batch);
    //Load our map and setup our map render
        mapLoader =new TmxMapLoader();
        map =mapLoader.load("Panda1.tmx");
        renderer =new OrthogonalTiledMapRenderer(map,1 /MyGdxGame.PPM);

    //initially set our gamcam to be centered correctly at the start of map
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        world = new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();


        player =new Panda(world);

        BodyDef bdef =new BodyDef();
        PolygonShape shape =new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //create ground bodies / fixtures

        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){

            Rectangle rect =((RectangleMapObject) object).getRectangle();

            bdef.type =BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/MyGdxGame.PPM,(rect.getY()+rect.getHeight()/2)/MyGdxGame.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/MyGdxGame.PPM,rect.getHeight()/2/MyGdxGame.PPM);
            fdef.shape =shape;
            body.createFixture(fdef);
        }
    }

    @Override
    public void show() {

    }
    public void handleInput(float dt){

     //If our user is holding down mouse move our camera through the game world.
            //if(Gdx.input.isTouched())
            //gamecam.position.x+=100*dt;
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
           player.b2body.applyLinearImpulse(new Vector2(0,4f),player.b2body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <=2)
        {
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0),player.b2body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
        {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0),player.b2body.getWorldCenter(),true);
        }
    }

    public void update(float dt){

     //handle user input first
        handleInput(dt);

       world.step(1/60f,6,2);

       gamecam.position.x=player.b2body.getPosition().x;
     //update the gamecam with correct coordinates after changes
        gamecam.update();
     //tell our renderer to draw only what our camera can see in our game world
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
     //seperate our update logic from render
           update(delta);

     //clear the game screeen with Black
           Gdx.gl.glClearColor(0,0,0,1);
           Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


           //render our game map
           renderer.render();

           //renderer our Box2DDebugLines
           b2dr.render(world,gamecam.combined);

           game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
           hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
       gamePort.update(width,height);
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
