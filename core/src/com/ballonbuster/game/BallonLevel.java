package com.ballonbuster.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class BallonLevel extends BaseScreen {

    private final int mapWidth = 640;
    private final int mapHeight = 640;

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

    }

    @Override
    protected void update(float delta) {

    }
}
