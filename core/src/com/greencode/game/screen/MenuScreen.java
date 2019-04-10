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

    @Override
    public void show() {
        super.show();
        touch = new Vector2();
        v = new Vector2(0,0);
        pos = new Vector2();
        end = new Vector2();
        img = new Texture("C:\\Users\\Ivan Green\\Desktop\\Geek Brains\\AndroidDevelop\\GameShooter\\android\\assets\\badlogic.jpg");

        //если пишу короткий путь, то выдает ошибку, хз почему, просьба помочь.
    }

    @Override
    public void render(float delta) {
        super.render(delta);
		batch.begin();
		pos.add(v);
		batch.draw(img, pos.x,pos.y);
		batch.end();
		if (pos.y > end.y && pos.x > end.x){
		    v.setZero();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);

        /**
         * Как я себе это представлял, но это не работает
         */

            end.x = screenX;
            end.y = Gdx.graphics.getHeight() - screenY;

            float s1 = screenX - pos.x; //определяем сколько точек надо пройти по оси Х
            float s2 = (Gdx.graphics.getHeight() - screenY) - pos.y; //определяем сколько точек надо пройти по оси У

            v = new Vector2(s1 / time, s2 / time); //делим расстояние на 60 кадров и получаем скорость


        /**
         * А дальше рабочий, но не тот метод.
         */
//        pos.x = screenX;
//        pos.y = Gdx.graphics.getHeight() - screenY;

        //реализовал "моментальное перемещение", а вот как анимировать все это дело - я хз (о_О)

        System.out.println("TouchDown screenX = " + touch.x + " screenY = " + touch.y);
        return false;
    }
}
