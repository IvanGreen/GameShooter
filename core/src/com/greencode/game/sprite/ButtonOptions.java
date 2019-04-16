package com.greencode.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;

public class ButtonOptions extends ScaledTouchUpButton {

    public ButtonOptions(TextureAtlas atlas) {
        super(atlas.findRegion("optionst_buttons"));
        setHeightProportion(0.3f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.33f);
    }

    @Override
    public void action() {

    }
}
