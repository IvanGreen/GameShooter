package com.greencode.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.BaseScreen;
import com.greencode.game.math.Rect;
import com.greencode.game.sprite.Asteroid;
import com.greencode.game.sprite.Background;
import com.greencode.game.sprite.GamerModel;

public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private GamerModel gm; //spaceShip GamerModel
    private TextureAtlas textureAtlas;
    private TextureAtlas atlas;
    private Asteroid asteroidList[];


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/Background/background.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/Atlas/gameModel.pack");
        gm = new GamerModel(atlas);
        textureAtlas = new TextureAtlas("textures/Atlas/asteroids.pack");
        asteroidList = new Asteroid[30];
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
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }
}
