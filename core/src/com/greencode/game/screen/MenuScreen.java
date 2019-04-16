package com.greencode.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.BaseScreen;
import com.greencode.game.math.Rect;

import sprite.Background;
import sprite.GamerModel;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private sprite.Background background;
    private Texture gm; //GamerModel
    private GamerModel gamerModel;

    @Override
    public void show() {
        super.show();
        bg = new Texture("background.jpg");
        background = new Background(new TextureRegion(bg));
        gm = new Texture("badlogic.jpg");
        gamerModel = new GamerModel(new TextureRegion(gm));

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        gamerModel.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta){
        gamerModel.update(delta);
    }

    private void draw(){
        batch.begin();
        background.draw(batch);
        gamerModel.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        gm.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        gamerModel.touchDown(touch,pointer);
        return false;
    }
}
