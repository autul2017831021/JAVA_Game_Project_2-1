package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

import javax.swing.text.LabelView;
import java.awt.*;

public class Hud  implements Disposable {
public Stage stage;
private Viewport viewport;

private Integer worldTimer;
private float timeCount;
private Integer score;

com.badlogic.gdx.scenes.scene2d.ui.Label countdownLabel;
com.badlogic.gdx.scenes.scene2d.ui.Label scoreLabel;
com.badlogic.gdx.scenes.scene2d.ui.Label timeLabel;
com.badlogic.gdx.scenes.scene2d.ui.Label levelLabel;
com.badlogic.gdx.scenes.scene2d.ui.Label worldLabel;
com.badlogic.gdx.scenes.scene2d.ui.Label playerLabel;


public Hud(SpriteBatch sb)
{
    worldTimer = 500;
    timeCount =0;
    score =0;

    viewport = new FitViewport(MyGdxGame.V_Width, MyGdxGame.V_Height, new OrthographicCamera());
    stage =new Stage(viewport,sb);

    Table table =new Table();
    table.top();
    table.setFillParent(true);

    countdownLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label(String.format("%03d",worldTimer), new LabelStyle(new BitmapFont(),Color.WHITE));
    scoreLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label(String.format("%06d",score), new LabelStyle(new BitmapFont(),Color.WHITE));
    timeLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label(String.format("TIME",worldTimer), new LabelStyle(new BitmapFont(),Color.WHITE));
    levelLabel =new com.badlogic.gdx.scenes.scene2d.ui.Label(String.format("1-1",worldTimer), new LabelStyle(new BitmapFont(),Color.WHITE));
    worldLabel =new com.badlogic.gdx.scenes.scene2d.ui.Label(String.format("WORLD",worldTimer), new LabelStyle(new BitmapFont(),Color.WHITE));
    playerLabel =new com.badlogic.gdx.scenes.scene2d.ui.Label(String.format("Mr.Panda",worldTimer), new LabelStyle(new BitmapFont(),Color.WHITE));

    table.add(playerLabel).expandX().padTop(10);
    table.add(worldLabel).expandX().padTop(10);
    table.add(timeLabel).expandX().padTop(10);
    table.row();
    table.add(scoreLabel).expandX();
    table.add(levelLabel).expandX();
    table.add(countdownLabel).expandX();

    stage.addActor(table);

}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
