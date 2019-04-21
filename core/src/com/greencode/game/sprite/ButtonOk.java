package com.greencode.game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;
import com.greencode.game.screen.GameScreen;
import com.greencode.game.screen.MenuScreen;

public class ButtonOk extends ScaledTouchUpButton {

    private Game game;

    public ButtonOk(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("okl_normal"));
        this.game = game;
        setHeightProportion(0.12f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.1f);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
        MenuScreen.stopMusic();
    }

}
