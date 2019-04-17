package com.greencode.game.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.Sprite;
import com.greencode.game.math.Rect;

public class GamerModel extends Sprite {

    private float SIZE = 0.15f;
    private Vector2 touch;
    private Vector2 v;
    private Vector2 buf;
    private Vector2 buf2;
    private Vector2 buf3;

    public GamerModel(TextureAtlas atlas) {
        super(atlas.findRegion("backRed"));
        setHeightProportion(SIZE);
        touch = new Vector2();
        buf = new Vector2();
        buf2 = new Vector2();
        v = new Vector2();
        buf3 = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(SIZE);
        this.pos.set(0f, -0.4f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        touch.y = -0.4f;
        buf.set(touch);
        v.y = 0f;
        buf2.set(v);
        buf.y = -0.4f;
        buf2.scl(delta);
        if (buf.sub(pos).len() <= buf2.len()) pos.set(touch);
        else pos.mulAdd(v, delta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        this.touch = touch;
        buf3.x = touch.x;
        buf3.y = -0.4f;
        v.set(buf3.cpy().sub(pos)).setLength(0.2f);
        return false;
    }
}
