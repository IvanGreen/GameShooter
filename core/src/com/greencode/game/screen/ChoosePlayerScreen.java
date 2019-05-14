package com.greencode.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.BaseScreen;
import com.greencode.game.math.Rect;
import com.greencode.game.sprite.Background;
import com.greencode.game.sprite.ButtonLeft;
import com.greencode.game.sprite.ButtonOk;
import com.greencode.game.sprite.ButtonRight;
import com.greencode.game.sprite.GamerModel;

public class ChoosePlayerScreen extends BaseScreen {

    private Game game;

    private Texture bg;
    private Background background;
    private GamerModel gm; //spaceShip GamerModel
    private TextureAtlas atlas;
    private ButtonLeft buttonLeft;
    private ButtonRight buttonRight;
    private ButtonOk buttonOk;

    public ChoosePlayerScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        GamerModel.setGame(false);
        bg = new Texture("textures/Background/backgroundGame.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("cuteTextures/atlas/allpack.pack");
        gm = new GamerModel(atlas);
        buttonLeft = new ButtonLeft(atlas,game,gm);
        buttonRight = new ButtonRight(atlas,game,gm);
        buttonOk = new ButtonOk(atlas,game);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        gm.resize(worldBounds);
        gm.pos.set(0f,0f);
        gm.setHeightProportion(0.3f);
        buttonLeft.resize(worldBounds);
        buttonRight.resize(worldBounds);
        buttonOk.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta){
        gm.update(delta);
    }

    private void draw(){
        batch.begin();
        background.draw(batch);
        gm.draw(batch);
        buttonLeft.draw(batch);
        buttonRight.draw(batch);
        buttonOk.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        buttonLeft.touchDown(touch,pointer);
        buttonRight.touchDown(touch,pointer);
        buttonOk.touchDown(touch,pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        buttonLeft.touchUp(touch,pointer);
        buttonRight.touchUp(touch,pointer);
        buttonOk.touchUp(touch,pointer);
        return false;
    }
}
