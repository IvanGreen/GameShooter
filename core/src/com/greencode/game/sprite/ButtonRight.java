package com.greencode.game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;
import com.greencode.game.screen.ChoosePlayerScreen;

public class ButtonRight extends ScaledTouchUpButton {

    private Game game;
    private GamerModel gamerModel;

    public ButtonRight(TextureAtlas atlas, Game game, GamerModel gamerModel) {
        super(atlas.findRegion("right_normal"));
        this.game = game;
        this.gamerModel = gamerModel;
        setHeightProportion(0.12f);
    }

    public ButtonRight(TextureAtlas atlas) {
        super(atlas.findRegion("right_normal"));
            setHeightProportion(0.08f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        if (!GamerModel.isGame()) {
            setRight(worldBounds.getRight() - 0.04f);
        } else if (GamerModel.isGame()){
            setLeft(worldBounds.getLeft() + 0.12f);
            setBottom(worldBounds.getBottom() + 0.02f);
        }
    }

    @Override
    public void action() {
        if (!GamerModel.isGame()) {
            gamerModel.chooseNextPlayer();
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        super.touchDown(touch, pointer);
        if (GamerModel.isGame() && isMe(touch)) {
            GamerModel.setPressedRight(true);
            GamerModel.moveRight();

        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        super.touchUp(touch, pointer);
        if (GamerModel.isGame() && isMe(touch)) {
            GamerModel.setPressedRight(false);
            if(GamerModel.isIsPressedLeft()){
                GamerModel.moveLeft();
            } else {
                GamerModel.stop();
            }

        }
            return false;
        }
}
