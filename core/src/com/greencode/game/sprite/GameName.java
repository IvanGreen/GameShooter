package com.greencode.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.Sprite;
import com.greencode.game.math.Rect;

public class GameName extends Sprite {

    public GameName(TextureAtlas atlas) {
        super(atlas.findRegion("top_banner"));
        setHeightProportion(0.1f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setTop(worldBounds.getTop());
    }
}
