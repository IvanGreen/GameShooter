package com.greencode.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.Sprite;
import com.greencode.game.math.Rect;

public class Enemy extends Sprite {

    private Rect worldBounds;
    private Vector2 v = new Vector2();
    private Vector2 buf = new Vector2();
    private int HP;


    public Enemy(){regions = new TextureRegion[1];}

    public void set(
            Rect worldBounds,
            TextureRegion region,
            int HP,
            Vector2 pos0,
            Vector2 v0,
            float height
    ){
        this.worldBounds = worldBounds;
        this.regions[0] = region;
        this.HP = HP;
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v,delta);
        if (isOutside(worldBounds)){
            destroy();
        }
        if (getRight() > worldBounds.getRight() || getLeft() < worldBounds.getLeft()){
            buf.x = v.x;
            buf.rotate(90);
            v.x = buf.x;
        }
    }
}
