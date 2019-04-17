package com.greencode.game.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.ScaledTouchUpButton;
import com.greencode.game.math.Rect;
import com.greencode.game.screen.ChoosePlayerScreen;

public class ButtonRight extends ScaledTouchUpButton {

    private Game game;

    public ButtonRight (TextureAtlas atlas, Game game) {
        super(atlas.findRegion("right_normal"));
        this.game = game;
        setHeightProportion(0.12f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setRight(worldBounds.getRight() - 0.04f);
    }

    @Override
    public void action() {
        int choose = GamerModel.getChoose();
        if (choose >= 0 && choose < 4){
            choose++;
        } else if (choose == 4){
            choose = 0;
        }
        GamerModel.setChoose(choose);
        game.setScreen(new ChoosePlayerScreen(game));
    }
}