package com.greencode.game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;
import com.greencode.game.screen.MenuScreen;

public class ButtonOptions extends ScaledTouchUpButton {

    private Game game;

    public ButtonOptions(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("optionst_buttons"));
        this.game = game;
        setHeightProportion(0.25f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setTop(worldBounds.getTop() + 0.065f);
        setLeft(worldBounds.getLeft() - 0.04f);
    }

    @Override
    public void action() {
        game.setScreen(new MenuScreen(game));
    }
}
