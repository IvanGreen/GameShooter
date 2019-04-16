package com.greencode.game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;
import com.greencode.game.screen.GameScreen;

public class ButtonStart extends ScaledTouchUpButton {

    private Game game;

    public ButtonStart(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("play_buttons"));
        this.game = game;
        setHeightProportion(0.3f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setTop(worldBounds.getTop() - 0.15f);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
    }
}
