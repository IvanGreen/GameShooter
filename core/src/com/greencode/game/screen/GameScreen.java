package com.greencode.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.Pool.BulletsPool;
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

    private BulletsPool bulletsPool;

    public GameScreen(Game game) {
        this.game = game;
    }


    @Override
    public void show() {
        super.show();
        GamerModel.setGame(true);
        bg = new Texture("textures/Background/backgroundGame.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("cuteTextures/atlas/char.pack");
        bulletsPool = new BulletsPool();
        gm = new GamerModel(atlas,GamerModel.choosePlayModel(),bulletsPool);
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
        freeAllDestroyedSprites();
        draw();
    }

    private void update(float delta){
        for (Asteroid asteroid : asteroidList){
            asteroid.update(delta);
        }
        gm.update(delta);
        bulletsPool.updateActiveSprites(delta);
    }

    private void freeAllDestroyedSprites(){
        bulletsPool.freeAllDestroyedActiveSprites();
    }

    private void draw(){
        batch.begin();
        background.draw(batch);
        for (Asteroid asteroid : asteroidList){
            asteroid.draw(batch);
        }
        gm.draw(batch);
        buttonToMenu.draw(batch);
        bulletsPool.drawActiveSprites(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        textureAtlas.dispose();
        bulletsPool.dispose();
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
        gm.touchUp(touch,pointer);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        gm.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        gm.keyUp(keycode);
        return false;
    }
}
