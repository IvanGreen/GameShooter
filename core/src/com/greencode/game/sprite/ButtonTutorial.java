package com.greencode.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;

public class ButtonTutorial extends ScaledTouchUpButton {

    public ButtonTutorial(TextureAtlas atlas) {
        super(atlas.findRegion("tutorial_normal"));
        setHeightProportion(0.11f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setTop(worldBounds.getTop() - 0.08f);
    }

    @Override
    public void action() {

    }

}
