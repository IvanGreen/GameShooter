package com.greencode.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.BaseScreen;
import com.greencode.game.math.Rect;
import com.greencode.game.sprite.Asteroid;
import com.greencode.game.sprite.Background;
import com.greencode.game.sprite.ButtonToMenu;
import com.greencode.game.sprite.GamerModel;

public class GameScreen extends BaseScreen {

    private Game game;

    private Texture bg;
    private Background background;
    private GamerModel gm; //spaceShip GamerModel
    private TextureAtlas textureAtlas;
    private TextureAtlas atlas;
    private Asteroid asteroidList[];
    private TextureAtlas buttonsAtlas;
    private ButtonToMenu buttonToMenu;

    public GameScreen(Game game) {
        this.game = game;
    }


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/Background/backgroundGame.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("cuteTextures/atlas/char.pack");
        gm = new GamerModel(atlas,GamerModel.choosePlayModel());
        textureAtlas = new TextureAtlas("cuteTextures/atlas/assets.pack");
        asteroidList = new Asteroid[20];
        buttonsAtlas = new TextureAtlas("cuteTextures/atlas/buttons.pack");
        buttonToMenu = new ButtonToMenu(buttonsAtlas,game);
        for (int i = 0; i < asteroidList.length; i++){
            asteroidList[i] = new Asteroid(textureAtlas,Asteroid.chooseAsteroid());
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Asteroid asteroid : asteroidList){
            asteroid.resize(worldBounds);
        }
        gm.resize(worldBounds);
        buttonToMenu.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta){
        for (Asteroid asteroid : asteroidList){
            asteroid.update(delta);
        }
        gm.update(delta);
    }

    private void draw(){
        batch.begin();
        background.draw(batch);
        for (Asteroid asteroid : asteroidList){
            asteroid.draw(batch);
        }
        gm.draw(batch);
        buttonToMenu.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        textureAtlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        gm.touchDown(touch,pointer);
        buttonToMenu.touchDown(touch,pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        buttonToMenu.touchUp(touch,pointer);
        return false;
    }
}
