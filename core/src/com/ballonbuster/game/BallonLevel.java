package com.ballonbuster.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class BallonLevel extends BaseScreen {

    protected final int mapWidth = 640;
    protected final int mapHeight = 640;

    private BaseActor bg;

    private float spawnTimer;
    private float spawnInterval;

    private int popped;
    private int escaped;
    private int clicked;

    private Label poppedLabel;
    private Label escapedLabel;
    private Label hitRatioLabel;

    public BallonLevel(Game game) {
        super(game);
    }

    @Override
    protected void init() {

        bg = ActorManger.createActor(mainStage, "sky.png", viewWidth / 2, viewHeight / 2);

        spawnTimer = 0;
        spawnInterval = 0.5f;

        BitmapFont font = new BitmapFont();
        LabelStyle style  = new LabelStyle(font, Color.NAVY);

        popped = 0;
        poppedLabel = new Label("Popped: 0", style);
        poppedLabel.setFontScale(2);
        poppedLabel.setPosition(20, 440);
        uiStage.addActor(poppedLabel);

        escaped = 0;
        escapedLabel = new Label("Escaped: 0", style);
        escapedLabel.setFontScale(2);
        escapedLabel.setPosition(220, 440);
        uiStage.addActor(escapedLabel);

        clicked = 0;
        hitRatioLabel = new Label("Hit ratio: ---", style);
        hitRatioLabel.setFontScale(2);
        hitRatioLabel.setPosition(420,440);
        uiStage.addActor(hitRatioLabel);

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        clicked++;
        return false;

    }

    @Override
    protected void update(float delta) {

        spawnTimer += delta;

        if (spawnTimer > spawnInterval) {

            spawnTimer -= spawnInterval;
            final Ballon ballon = new Ballon(this);
            ballon.addListener(
                    new InputListener(){
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            popped++;
                            ballon.remove();
                            return true;
                        }
                    }
            );
            mainStage.addActor(ballon);

        }


        poppedLabel.setText("Popped: " + popped);
        escapedLabel.setText("Escaped: " + escaped);
        if (clicked > 0) {
            int percent = (int) (100 * popped / clicked);
            hitRatioLabel.setText("Hit ratio: " + percent + "%");
        }

    }

    public void escapedInc() {
        escaped++;
    }
}
