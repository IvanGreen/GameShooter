package com.greencode.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.Sprite;
import com.greencode.game.math.Rect;
import com.greencode.game.math.Rnd;
import java.util.Random;

public class Asteroid extends Sprite {

    private Vector2 v;
    private Rect worldBounds;

    public Asteroid(TextureAtlas atlas, String type) {
        super(atlas.findRegion(type));
        float vx = Rnd.nextFloat(-0.005f, 0.005f);
        float vy = Rnd.nextFloat(-0.5f, -0.1f);
        v = new Vector2(vx, vy);
        float SIZE = Rnd.nextFloat(0.005f,0.05f);
        setHeightProportion(SIZE);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(),worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(),worldBounds.getTop());
        this.pos.set(posX,posY);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v,delta);
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
    }

    public static String chooseAsteroid(){
        String type = null;
        Random random = new Random();
        int rnd = random.nextInt(4);
        
        if (rnd == 0) type = "aestroid_brown";
        if (rnd == 1) type = "aestroid_dark";
        if (rnd == 2) type = "aestroid_gay";
        if (rnd == 3) type = "aestroid_gray";
        
        return type;
    }
}
