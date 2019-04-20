package com.greencode.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;

public class ButtonShoot extends ScaledTouchUpButton {

        public ButtonShoot(TextureAtlas atlas) {
            super(atlas.findRegion("shoot"));
            setHeightProportion(0.09f);
        }

        @Override
        public void resize(Rect worldBounds) {
            super.resize(worldBounds);
            setBottom(worldBounds.getBottom() + 0.02f);
            setRight(worldBounds.getRight() - 0.02f);
        }

        @Override
        public void action() {

        }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        super.touchDown(touch, pointer);
        if(isMe(touch)){
            GamerModel.setIsShoot(true);
        }
        return false;
    }
}


