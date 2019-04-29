package com.greencode.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.Pool.BulletsPool;
import com.greencode.game.Pool.EnemiesPool;
import com.greencode.game.Pool.ExplosionsPool;
import com.greencode.game.base.BaseScreen;
import com.greencode.game.math.Rect;
import com.greencode.game.sprite.Asteroid;
import com.greencode.game.sprite.Background;
import com.greencode.game.sprite.Bullet;
import com.greencode.game.sprite.ButtonLeft;
import com.greencode.game.sprite.ButtonNewGame;
import com.greencode.game.sprite.ButtonRight;
import com.greencode.game.sprite.ButtonShoot;
import com.greencode.game.sprite.ButtonToMenu;
import com.greencode.game.sprite.Enemy;
import com.greencode.game.sprite.GameOver;
import com.greencode.game.sprite.GamerModel;
import com.greencode.game.utils.EnemiesGenerator;

import java.util.List;

public class GameScreen extends BaseScreen {

    private enum  State {PLAYING,PAUSE,GAME_OVER}
    private State state;

    private Game game;

    private Texture bg;
    private Background background;

    private GamerModel gm;
    private TextureAtlas atlas;

    private Asteroid asteroidList[];

    private ButtonToMenu buttonToMenu;
    private ButtonShoot buttonShoot;
    private ButtonRight buttonRight;
    private ButtonLeft buttonLeft;
    private ButtonNewGame buttonNewGame;
    private GameOver gameOver;

    private ExplosionsPool explosionsPool;
    private BulletsPool bulletsPool;

    private EnemiesPool enemiesPool;
    private EnemiesGenerator enemiesGenerator;

    private Music music = Gdx.audio.newMusic(Gdx.files.internal("music_assets/music/game.mp3"));
    private Sound shootSound = Gdx.audio.newSound(Gdx.files.internal("music_assets/sound/bullet.wav"));
    private Sound enemiesShootSound = Gdx.audio.newSound(Gdx.files.internal("music_assets/sound/bullet.wav"));
    private Sound explosionSound = Gdx.audio.newSound(Gdx.files.internal("music_assets/sound/explosion.wav"));

    public GameScreen(Game game) {
        this.game = game;
    }


    @Override
    public void show() {
        super.show();
        GamerModel.setGame(true);
        state = State.PLAYING;
        bg = new Texture("textures/Background/backgroundGame.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("cuteTextures/atlas/allpack.pack");
        bulletsPool = new BulletsPool();
        explosionsPool = new ExplosionsPool(atlas,explosionSound);
        gm = new GamerModel(atlas,GamerModel.choosePlayModel(),bulletsPool,explosionsPool,shootSound);
        enemiesPool = new EnemiesPool(bulletsPool,explosionsPool,enemiesShootSound,worldBounds,gm);
        enemiesGenerator = new EnemiesGenerator(atlas,enemiesPool,worldBounds);
        asteroidList = new Asteroid[20];
        buttonToMenu = new ButtonToMenu(atlas,game);
        for (int i = 0; i < asteroidList.length; i++){
            asteroidList[i] = new Asteroid(atlas,Asteroid.chooseAsteroid());
        }
        buttonShoot = new ButtonShoot(atlas);
        buttonRight = new ButtonRight(atlas);
        buttonNewGame = new ButtonNewGame(atlas,game);
        buttonLeft = new ButtonLeft(atlas);
        gameOver = new GameOver(atlas);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Asteroid asteroid : asteroidList){
            asteroid.resize(worldBounds);
        }
        if(state == State.PLAYING) {
            gm.resize(worldBounds);
            buttonToMenu.resize(worldBounds);
            buttonRight.resize(worldBounds);
            buttonShoot.resize(worldBounds);
            buttonLeft.resize(worldBounds);
            buttonNewGame.resize(worldBounds);
            gameOver.resize(worldBounds);
        }
    }

    @Override
    public void pause() {
        super.pause();
        if (state == State.PLAYING){
            state = State.PAUSE;
        }
    }

    @Override
    public void resume() {
        super.resume();
        if (state == State.PAUSE){
            state = State.PLAYING;
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyedSprites();
        checkCollisions();
        draw();
    }

    private void update(float delta){
        for (Asteroid asteroid : asteroidList){
            asteroid.update(delta);
        }
        explosionsPool.updateActiveSprites(delta);
        updateMusic();
        if (state == State.PLAYING){
        gm.update(delta);
        enemiesGenerator.generate(delta);
        enemiesPool.updateActiveSprites(delta);
        bulletsPool.updateActiveSprites(delta);
        }


    }

    private void freeAllDestroyedSprites(){
        bulletsPool.freeAllDestroyedActiveSprites();
        enemiesPool.freeAllDestroyedActiveSprites();
        explosionsPool.freeAllDestroyedActiveSprites();
    }

    private void checkCollisions() {
        if (state != State.PLAYING){
            return;
        }
        List<Enemy> enemyList = enemiesPool.getActiveObjects();

        for (Enemy enemy : enemyList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            float minDist = enemy.getHalfWidth() + gm.getHalfWidth();
            if (enemy.pos.dst(gm.pos) < minDist) {
                enemy.destroy();
                gm.destroy();
                state = State.GAME_OVER;
                return;
            }
        }

        List<Bullet> bulletList = bulletsPool.getActiveObjects();

        for (Bullet bullet : bulletList) {
            if (bullet.isDestroyed()){
                continue;
            }
            if (bullet.getOwner() == gm) {
                for (Enemy enemy : enemyList) {
                    if (enemy.isDestroyed()) {
                        continue;
                    }

                    if (enemy.isBulletCollision(bullet)) {
                        enemy.damage(bullet.getDamage());
                        bullet.destroy();
                        return;
                    }
                }
            } else {
                if (gm.isBulletCollision(bullet)){
                    gm.damage(bullet.getDamage());
                    if (gm.isDestroyed()){
                        state = State.GAME_OVER;
                    }
                    bullet.destroy();
                    return;
                }
            }
        }
    }

    private void draw(){
        batch.begin();
        background.draw(batch);
        for (Asteroid asteroid : asteroidList){
            asteroid.draw(batch);
        }
        buttonToMenu.draw(batch);
        explosionsPool.drawActiveSprites(batch);
        if (state == State.PLAYING){
            gm.draw(batch);
            bulletsPool.drawActiveSprites(batch);
            enemiesPool.drawActiveSprites(batch);
            buttonShoot.draw(batch);
            buttonRight.draw(batch);
            buttonLeft.draw(batch);
        }
        if(state == State.GAME_OVER){
            buttonNewGame.draw(batch);
            gameOver.draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        bulletsPool.dispose();
        enemiesPool.dispose();
        music.dispose();
        explosionsPool.dispose();
        explosionSound.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        buttonToMenu.touchDown(touch, pointer);
        if (state == State.PLAYING) {
            gm.touchDown(touch, pointer);
            buttonShoot.touchDown(touch, pointer);
            buttonRight.touchDown(touch, pointer);
            buttonLeft.touchDown(touch, pointer);
        }
        if (state == State.GAME_OVER){
            buttonNewGame.touchDown(touch,pointer);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        buttonToMenu.touchUp(touch, pointer);
        if (state == State.PLAYING) {
            gm.touchUp(touch, pointer);
            buttonShoot.touchUp(touch, pointer);
            buttonRight.touchUp(touch, pointer);
            buttonLeft.touchUp(touch, pointer);
        }
        if (state == State.GAME_OVER){
            buttonNewGame.touchUp(touch,pointer);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING) {
            gm.keyDown(keycode);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING) {
            gm.keyUp(keycode);
        }
        return false;
    }

    public void updateMusic(){
        boolean isPlaying = music.isPlaying();
        if(!isPlaying){
            music.play();
            music.setLooping(true);
        }
    }
}
