package com.greencode.game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;
import com.greencode.game.screen.MenuScreen;

public class ButtonToMenu extends ScaledTouchUpButton {

    private Game game;

    public ButtonToMenu(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("close_normal"));
        this.game = game;
        setHeightProportion(0.07f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setTop(worldBounds.getTop());
        setLeft(worldBounds.getLeft());
    }

    @Override
    public void action() {
        game.setScreen(new MenuScreen(game));
    }

}
