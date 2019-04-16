package com.greencode.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.BaseScreen;
import com.greencode.game.math.Rect;
import com.greencode.game.sprite.Background;
import com.greencode.game.sprite.ButtonExit;
import com.greencode.game.sprite.ButtonOptions;
import com.greencode.game.sprite.ButtonStart;
import com.greencode.game.sprite.GameName;

public class MenuScreen extends BaseScreen {

    private Game game;
    private Texture bg;
    private Background background;
    private ButtonExit buttonExit;
    private ButtonStart buttonStart;
    private GameName gameName;
    private TextureAtlas buttonsAtlas;
    private ButtonOptions buttonOptions;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/Background/background.jpg");
        background = new Background(new TextureRegion(bg));
        buttonsAtlas = new TextureAtlas("textures/Atlas/menu.pack");
        buttonExit = new ButtonExit(buttonsAtlas);
        buttonStart = new ButtonStart(buttonsAtlas,game);
        gameName = new GameName(buttonsAtlas);
        buttonOptions = new ButtonOptions(buttonsAtlas);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        buttonExit.resize(worldBounds);
        buttonStart.resize(worldBounds);
        gameName.resize(worldBounds);
        buttonOptions.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta){

    }

    private void draw(){
        batch.begin();
        background.draw(batch);
        buttonExit.draw(batch);
        buttonStart.draw(batch);
        gameName.draw(batch);
        buttonOptions.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        buttonExit.touchDown(touch,pointer);
        buttonStart.touchDown(touch,pointer);
        buttonOptions.touchDown(touch,pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        buttonExit.touchUp(touch,pointer);
        buttonStart.touchUp(touch,pointer);
        buttonOptions.touchUp(touch,pointer);
        return false;
    }
}
