package com.greencode.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;

public class ButtonShoot extends ScaledTouchUpButton {

        public ButtonShoot(TextureAtlas atlas) {
            super(atlas.findRegion("shoot"));
            setHeightProportion(0.1f);
        }

        @Override
        public void resize(Rect worldBounds) {
            super.resize(worldBounds);
            setBottom(worldBounds.getBottom() + 0.2f);
            setRight(worldBounds.getRight() - 0.02f);
        }

        @Override
        public void action() {
            GamerModel.setIsShoot(true);
        }
}


