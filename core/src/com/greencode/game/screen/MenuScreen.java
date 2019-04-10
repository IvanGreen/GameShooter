package com.greencode.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.greencode.game.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }
}
