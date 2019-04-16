package com.greencode.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;

public class ButtonExit extends ScaledTouchUpButton {

    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("exit_buttons"));
        setHeightProportion(0.3f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.1f);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }


}
