package com.ballonbuster.game;

import com.badlogic.gdx.Game;

public class BallonBuster extends Game {


    @Override
    public void create() {

        setScreen(new BallonLevel(this));

    }

}
