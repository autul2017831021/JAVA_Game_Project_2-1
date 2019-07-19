package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;

public class Wizard extends Sprite {
    public World world;
    public Body b3body;

    public Wizard(World world)
    {
        this.world=world;
        defineWizard();
    }
    public void defineWizard()
    {
        BodyDef bdef=new BodyDef();
        bdef.position.set(12/ MyGdxGame.PPM,32/MyGdxGame.PPM);
        bdef.type =BodyDef.BodyType.DynamicBody;

        b3body=world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        CircleShape shape=new CircleShape();
        shape.setRadius(6/MyGdxGame.PPM);


        fdef.shape=shape;
        b3body.createFixture(fdef);
    }
}
