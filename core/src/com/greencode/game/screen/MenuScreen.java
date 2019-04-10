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

    @Override
    public void show() {
        super.show();
        touch = new Vector2();
        v = new Vector2();
        pos = new Vector2();
        img = new Texture("C:\\Users\\Ivan Green\\Desktop\\Geek Brains\\AndroidDevelop\\GameShooter\\android\\assets\\badlogic.jpg");

        //если пишу короткий путь, то выдает ошибку, хз почему, просьба помочь.
    }

    @Override
    public void render(float delta) {
        super.render(delta);
		batch.begin();
		batch.draw(img, pos.x,pos.y);
		batch.end();
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
//
//        float s1 = screenX - pos.x; //определяем сколько точек надо пройти по оси Х
//        float s2 = (Gdx.graphics.getHeight() - screenY) - pos.y; //определяем сколько точек надо пройти по оси У
//
//        v = new Vector2(s1/60,s2/60); //делим расстояние на 60 кадров и получаем скорость
//
//        do{
//            pos.add(v); //делаем пока
//        } while (pos.x != screenX && pos.y != screenY); //координаты не совпадут с местом назначения

        /**
         * А дальше рабочий, но не тот метод.
         */
        pos.x = screenX;
        pos.y = Gdx.graphics.getHeight() - screenY;

        //реализовал "моментальное перемещение", а вот как анимировать все это дело - я хз (о_О)

        System.out.println("TouchDown screenX = " + touch.x + " screenY = " + touch.y);
        return false;
    }
}
