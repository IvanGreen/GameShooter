package com.greencode.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.BaseScreen;

public class MenuScreen extends BaseScreen{

    private Texture img;
    private Vector2 touch;
    private Vector2 pos;
    private Vector2 v;
    private Vector2 end;
    private final float time = 60; //60 кадров в секунду
    private Texture bg;

    @Override
    public void show() {
        super.show();
        touch = new Vector2();
        v = new Vector2(0,0);
        pos = new Vector2();
        end = new Vector2();
        img = new Texture("badlogic.jpg");
        bg = new Texture("background.jpg");

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        pos.add(v);
        batch.draw(bg,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(img, pos.x, pos.y, 120,120);
        batch.end();
        checkFinal();

    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == 19){
            pos.y += 10;
        }else if(keycode == 22){
            pos.x += 10;
        }else if(keycode == 21){
            pos.x -= 10;
        }else if(keycode == 20){
            pos.y -= 10;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        if (character == 'w'){
            pos.y += 10;
        } else if(character == 's'){
            pos.y -= 10;
        } else if(character == 'a'){
            pos.x -= 10;
        } else if(character == 'd'){
            pos.x += 10;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);

        sliceToPoint(screenX,screenY); //запускаем в путь

        System.out.println("TouchDown screenX = " + touch.x + " screenY = " + touch.y);
        return false;
    }

    public void sliceToPoint (int screenX,int screenY) {
        end.x = screenX;
        end.y = Gdx.graphics.getHeight() - screenY;

        float s1 = screenX - pos.x; //определяем сколько точек надо пройти по оси Х
        float s2 = (Gdx.graphics.getHeight() - screenY) - pos.y; //определяем сколько точек надо пройти по оси У

        v = new Vector2(s1 / time, s2 / time);//делим расстояние на 60 кадров в сек. и получаем скорость
    }

    public void checkFinal(){
        if (v.x >= 0 && v.y >= 0) {

            if (pos.y >= end.y && pos.x >= end.x) {
                v.setZero();
            }
        } else if(v.x < 0 && v.y < 0){
            if (pos.y <= end.y && pos.x <= end.x) {
                v.setZero();
            }
        } else if (pos.y == end.y && pos.x == end.x){
            v.setZero();
        } else if (v.x >= 0 && v.y <= 0){
            if (pos.y <= end.y && pos.x >= end.x){
                v.setZero();
            }
        } else if (v.x <= 0 && v.y >= 0){
            if (pos.y >= end.y && pos.x <= end.x){
                v.setZero();
            }
        }
    }
}
