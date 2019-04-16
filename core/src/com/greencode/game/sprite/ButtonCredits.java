package com.greencode.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;

public class ButtonCredits extends ScaledTouchUpButton {

    public ButtonCredits(TextureAtlas atlas) {
        super(atlas.findRegion("credits_normal"));
        setHeightProportion(0.13f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.40f);
    }

    @Override
    public void action() {

    }
}
