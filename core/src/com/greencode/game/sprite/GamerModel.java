package com.greencode.game.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.Pool.BulletsPool;
import com.greencode.game.base.Sprite;
import com.greencode.game.math.Rect;


public class GamerModel extends Sprite {

    private BulletsPool bulletsPool;
    private TextureRegion bulletRegion;
    private Vector2 bulletV = new Vector2(0,0.5f);

    private Vector2 v = new Vector2();
    private Vector2 v0 = new Vector2(0.5f,0);

    private boolean pressedRight;
    private boolean isPressedLeft;

    private static final int INVALID_POINTER = -1;
    private int rightPointer = INVALID_POINTER;
    private int leftPointer = INVALID_POINTER;

    private float SIZE = 0.15f;
    private static int choose = 1;
    private static String type;
    private Rect worldBounds;

    private static boolean isGame = false;
    private static boolean isShoot = false;


    public GamerModel(TextureAtlas atlas,String type, BulletsPool bulletsPool) {
        super(atlas.findRegion(type));
        this.bulletsPool = bulletsPool;
        this.bulletRegion = atlas.findRegion("zGoodBullet");
        this.bulletsPool = bulletsPool;
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
        this.pos.set(0f, -0.4f);
        this.worldBounds = worldBounds;
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
            if (touch.x < worldBounds.pos.x) {
                if (leftPointer != INVALID_POINTER) {
                    return false;
                }
                leftPointer = pointer;
                moveLeft();
            } else {
                if (rightPointer != INVALID_POINTER) {
                    return false;
                }
                rightPointer = pointer;
                moveRight();
            }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
            if (pointer == leftPointer) {
                leftPointer = INVALID_POINTER;
                if (rightPointer != INVALID_POINTER) {
                    moveRight();
                } else {
                    stop();
                }
            } else if (pointer == rightPointer) {
                rightPointer = INVALID_POINTER;
                if (leftPointer != INVALID_POINTER) {
                    moveLeft();
                } else {
                    stop();
                }
            }

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
                shoot();
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
        if (choose == 1) type = "backBleu"; //ошибка в имени при сборке атласа
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
            setIsShoot(false);
        }
    }

    private void moveRight(){
        v.set(v0);
    }

    private void moveLeft(){
        v.set(v0).rotate(180);
    }

    private void stop(){
        v.setZero();
    }

    public static void setGame(boolean game) {
        isGame = game;
    }
}
