package com.greencode.game.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.Pool.BulletsPool;
import com.greencode.game.base.Ship;
import com.greencode.game.base.Sprite;
import com.greencode.game.math.Rect;


public class GamerModel extends Ship {

    private static boolean pressedRight;
    private static boolean isPressedLeft;

    private static int choose = 1;
    private static String type;

    private static boolean isGame = false;
    private static boolean isShoot = false;

    private static Vector2 v = new Vector2();
    private static Vector2 v0 = new Vector2(0.5f,0);

    public GamerModel(TextureAtlas atlas, String type, BulletsPool bulletsPool, Sound shootSound) {
        super(atlas,type);
        this.bulletRegion = atlas.findRegion("zGoodBullet");
        this.bulletsPool = bulletsPool;
        this.shootSound = shootSound;
        setHeightProportion(0.13f);
        this.bulletV.set(0f,0.5f);
        this.bulletHeight = 0.15f;
        this.damage = 1;
    }

    public GamerModel(TextureAtlas atlas,String type) {
        super(atlas,type);
        setHeightProportion(0.13f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.pos.set(0f, -0.33f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        controlBarrier();
        pos.mulAdd(v,delta);
        shoot();
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
            shootSound.play();
            setIsShoot(false);
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
