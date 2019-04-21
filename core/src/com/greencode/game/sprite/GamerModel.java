package com.greencode.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.Pool.BulletsPool;
import com.greencode.game.Pool.EnemiesPool;
import com.greencode.game.base.Sprite;
import com.greencode.game.math.Rect;
import com.greencode.game.math.Rnd;


public class GamerModel extends Sprite {

    private BulletsPool bulletsPool;
    private TextureRegion bulletRegion;
    private Vector2 bulletV = new Vector2(0,0.5f);

    private EnemiesPool enemiesPool;
    private TextureRegion enemiesRegion;
    private Vector2 posEnemies = new Vector2(Rnd.nextFloat(-0.3f,0.3f),0.55f);
    private Vector2 enemiesV = new Vector2(Rnd.nextFloat(-0.2f,0.2f),-0.1f);
    private float enemiesInterval = 5f;
    private float enemiesTimer;

    private static Vector2 v = new Vector2();
    private static Vector2 v0 = new Vector2(0.5f,0);

    private static boolean pressedRight;
    private static boolean isPressedLeft;

    private float SIZE = 0.12f;
    private static int choose = 1;
    private static String type;
    private Rect worldBounds;

    private static boolean isGame = false;
    private static boolean isShoot = false;

    Sound sound = Gdx.audio.newSound(Gdx.files.internal("music_assets/sound/bullet.wav"));


    public GamerModel(TextureAtlas atlas,String type, BulletsPool bulletsPool,EnemiesPool enemiesPool) {
        super(atlas.findRegion(type));
        this.bulletRegion = atlas.findRegion("zGoodBullet");
        this.bulletsPool = bulletsPool;
        this.enemiesRegion = atlas.findRegion("psy");
        this.enemiesPool = enemiesPool;
        setHeightProportion(SIZE);
    }

    public GamerModel(TextureAtlas atlas,String type) {
        super(atlas.findRegion(type));
        setHeightProportion(SIZE);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(SIZE);
        this.pos.set(0f, -0.33f);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        controlBarrier();
        pos.mulAdd(v,delta);
        shoot();
        enemiesTimer += delta;
        if(enemiesTimer >= enemiesInterval) {
            enemiesTimer = 0;
            startEnemies();
        }
    }

    public void controlBarrier(){
        if (getRight() > worldBounds.getRight()){
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()){
            setLeft(worldBounds.getLeft());
            stop();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.A:
            case Input.Keys.LEFT:
                isPressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
            case Input.Keys.SPACE:
                setIsShoot(true);
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.A:
            case Input.Keys.LEFT:
                isPressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (isPressedLeft){
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
        return false;
    }


    public static int getChoose() {
        return choose;
    }

    public static void setChoose(int playerSet) {
        choose = playerSet;
    }

    public static String choosePlayer(){

        int choose = getChoose();

        if (choose == 0) type = "moreGreen";
        if (choose == 1) type = "moreBlue";
        if (choose == 2) type = "moreLightBlue";
        if (choose == 3) type = "morePurple";
        if (choose == 4) type = "moreRed";

        return type;
    }

    public static String choosePlayModel(){

        int choose = getChoose();

        if (choose == 0) type = "backGreen";
        if (choose == 1) type = "backBlue";
        if (choose == 2) type = "backLightBlue";
        if (choose == 3) type = "backPurple";
        if (choose == 4) type = "backRed";

        return type;
    }

    public static void setIsShoot(boolean isShoot) {
        GamerModel.isShoot = isShoot;
    }

    public void shoot(){
        if (isShoot) {
            Bullet bullet = (Bullet) bulletsPool.obtain();
            bullet.set(this, bulletRegion, pos, bulletV, 0.09f, worldBounds, 1);
            sound.play();
            setIsShoot(false);
        }
    }

    public void startEnemies() {
        if (isGame) {
            Enemy enemy = (Enemy) enemiesPool.obtain();
            posEnemies = new Vector2(Rnd.nextFloat(-0.3f,0.3f),0.55f);
            enemiesV = new Vector2(Rnd.nextFloat(-0.2f,0.2f),-0.1f);
            enemy.set(worldBounds, enemiesRegion, 3, posEnemies, enemiesV, 0.1f);
        }
    }

    public static boolean isGame() {
        return isGame;
    }

    public static void setGame(boolean game) {
        isGame = game;
    }

    public static void moveRight(){
        v.set(v0);
    }

    public static void moveLeft(){
        v.set(v0).rotate(180);
    }

    public static void stop(){
        v.setZero();
    }

    public static boolean isPressedRight() {
        return pressedRight;
    }

    public static void setPressedRight(boolean pressedRight) {
        GamerModel.pressedRight = pressedRight;
    }

    public static boolean isIsPressedLeft() {
        return isPressedLeft;
    }

    public static void setIsPressedLeft(boolean isPressedLeft) {
        GamerModel.isPressedLeft = isPressedLeft;
    }
}
