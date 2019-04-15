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
    private Texture bg;
    private Vector2 buf;

    @Override
    public void show() {
        super.show();
        touch = new Vector2();
        v = new Vector2(0.2f,0.5f);
        pos = new Vector2();
        buf = new Vector2();
        img = new Texture("badlogic.jpg");
        bg = new Texture("background.jpg");

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
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
        v.setZero();
        if(keycode == 19){
            pos.y += 10;
        }else if(keycode == 22){
            pos.x += 10;
        }else if(keycode == 21){
            pos.x -= 10;
        }else if(keycode == 20){
            pos.y -= 10;
        }
        v.setZero();
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        v.setZero();
        if (character == 'w' || character == 'W' || character == 'ц' || character == 'Ц'){
            pos.y += 10;
        } else if(character == 's' || character == 'S' || character == 'Ы' || character == 'ы'){
            pos.y -= 10;
        } else if(character == 'a' || character == 'ф' || character == 'Ф' || character == 'A'){
            pos.x -= 10;
        } else if(character == 'd' || character == 'D' || character == 'В' || character == 'в'){
            pos.x += 10;
        }
        v.setZero();
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(touch.cpy().sub(pos)).setLength(0.5f);
        System.out.println("TouchDown screenX = " + touch.x + " screenY = " + touch.y);
        return false;
    }

    public void checkFinal(){
        buf.set(touch);

        if (buf.sub(pos).len() > 0.5f) {
            pos.add(v);
        } else {
            pos.set(touch);
        }
    }

}
