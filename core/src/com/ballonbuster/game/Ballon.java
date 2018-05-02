package com.ballonbuster.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Ballon extends BaseActor {

    private float speed;
    private float amplitude;
    private float oscillation;
    private float initY;
    private float time;
    private int offsetX;
    private boolean isPopped = false;

    BallonLevel ballonLevel;

    public Ballon(BallonLevel ballonLevel) {

        this.ballonLevel = ballonLevel;

        speed = 80 * MathUtils.random(0.5f, 2.0f);
        amplitude = 50 * MathUtils.random(0.5f, 2.0f);
        oscillation = 0.01f * MathUtils.random(0.5f, 2.0f);
        initY = 120 * MathUtils.random(0.5f, 2.0f);
        time = 0;
        offsetX = -100;

        setTexture(new Texture(Gdx.files.internal("grey-ballon.png")));
        setColor(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);

        setX(offsetX);

    }

    @Override
    public void act(float dt) {

        super.act(dt);

        time += dt;
        float xPos = speed * time + offsetX;
        float yPos = amplitude * MathUtils.sin(oscillation * xPos) + initY;
        setPosition(xPos, yPos);

        if (!isPopped && getX() > ballonLevel.mapWidth) {
            ballonLevel.escapedInc();
            remove();
        }

    }

    public void popBullon() {

        isPopped = true;

        Action popped = Actions.sequence(
                Actions.parallel(
                Actions.fadeOut(1),
                Actions.scaleTo(0,0, 2)),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        remove();
                    }
                })
        );

        addAction(popped);

    }

}
