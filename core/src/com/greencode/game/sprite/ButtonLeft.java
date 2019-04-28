package com.greencode.game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;

public class ButtonLeft extends ScaledTouchUpButton {

    private Game game;
    private GamerModel gamerModel;

    public ButtonLeft(TextureAtlas atlas, Game game, GamerModel gamerModel) {
        super(atlas.findRegion("left_normal"));
        this.game = game;
        this.gamerModel = gamerModel;
        setHeightProportion(0.12f);
    }

    public ButtonLeft(TextureAtlas atlas) {
        super(atlas.findRegion("left_normal"));
        setHeightProportion(0.08f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        if (!GamerModel.isGame()) {
            setLeft(worldBounds.getLeft() + 0.04f);
        } else if (GamerModel.isGame()) {
            setLeft(worldBounds.getLeft() + 0.02f);
            setBottom(worldBounds.getBottom() + 0.02f);
        }
    }


    @Override
    public void action() {
        if (!GamerModel.isGame()) {
            gamerModel.choosePrevPlayer();
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        super.touchDown(touch, pointer);
        if (GamerModel.isGame() && isMe(touch)) {
            GamerModel.setIsPressedLeft(true);
            GamerModel.moveLeft();

        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        super.touchUp(touch, pointer);
        if (GamerModel.isGame() && isMe(touch)) {
            GamerModel.setIsPressedLeft(false);
            if (GamerModel.isPressedRight()){
                GamerModel.moveRight();
            } else {
                GamerModel.stop();
            }

        }
        return false;
    }
}
