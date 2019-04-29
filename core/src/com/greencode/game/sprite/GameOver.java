package com.greencode.game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.Sprite;
import com.greencode.game.math.Rect;

public class GameOver extends Sprite {

    private GamerModel gamerModel;

    public GameOver(TextureAtlas atlas, GamerModel gamerModel) {
        super(atlas.findRegion("gameOver"));
        this.gamerModel = gamerModel;
        setHeightProportion(0.10f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setTop(worldBounds.getTop() - 0.35f);
        gamerModel.setIsPressedLeft(false);
        gamerModel.setPressedRight(false);
    }
}
