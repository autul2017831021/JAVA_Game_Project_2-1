package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screen.PlayScreen;

public class Panda extends Sprite {
    public enum State { FALLING,JUMPING,STANDING,RUNNING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion pandaStand;
    private Animation pandaRun;
    private Animation pandaJump;

    private float stateTimer;
    private boolean runningRight;


    public Panda(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("Panda"));//little_mario
        this.world=world;
        currentState =State.STANDING;
        previousState=State.STANDING;
        stateTimer = 0;
        runningRight =true;

        //Animations
        Array<TextureRegion> frames =new Array<TextureRegion>();
        for(int i=1;i<4;i++)
          frames.add(new TextureRegion(getTexture(),i*10,10,90,80));//i*16,10,16,16
        //setBounds(310,10,90/MyGdxGame.PPM,80/MyGdxGame.PPM);//0,0,16,16
        pandaRun= new Animation(0.1f,frames);//.1
        frames.clear();

        for(int i=4;i<=6;i++)
            frames.add(new TextureRegion(getTexture(),340,10,90,80));//i*16,10,16,16

        pandaJump= new Animation(0.1f,frames);


        pandaStand = new TextureRegion(getTexture(),0,100,100,70);//0,0,16,16
        definePanda();
        setBounds(10,10,50/MyGdxGame.PPM,50/MyGdxGame.PPM);//0,0,16,16
        setRegion(pandaStand);
    }

    public void update (float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState=getState();

        TextureRegion region;
        switch (currentState){
            case JUMPING:
                region = (TextureRegion) pandaJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion) pandaRun.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
            case STANDING:
                default:
                    region =pandaStand;
                  break;

        }

      if((b2body.getLinearVelocity().x<0 || !runningRight) && !region.isFlipX()){
          region.flip(true,false);
          runningRight =false;
      }
      else if((b2body.getLinearVelocity().x>0|| runningRight) && region.isFlipX()){
          region.flip(true, false);
          runningRight =true;
      }
      stateTimer = currentState == previousState ? stateTimer +dt :0;
      previousState =currentState;
      return region;
    }

    public State getState(){
        if(b2body.getLinearVelocity().y>0 || (b2body.getLinearVelocity().y<0 && previousState==State.JUMPING))
            return State.JUMPING;
        else if(b2body.getLinearVelocity().y<0)
            return State.FALLING;
        else if(b2body.getLinearVelocity().x!=0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

    public void definePanda(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ MyGdxGame.PPM,320/MyGdxGame.PPM);
        bdef.type =BodyDef.BodyType.DynamicBody;

        b2body= world.createBody(bdef);

        FixtureDef fdef =new FixtureDef();
        CircleShape shape =new CircleShape();
        shape.setRadius(15/MyGdxGame.PPM);

        fdef.shape=shape;
        b2body.createFixture(fdef);
    }

}
