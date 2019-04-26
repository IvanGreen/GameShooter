package com.greencode.game.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.Pool.BulletsPool;
import com.greencode.game.math.Rect;
import com.greencode.game.sprite.Bullet;

public class Ship extends Sprite {


    protected Sound shootSound;
    protected Rect worldBounds;
    protected Vector2 v;
    protected Vector2 v0;
    protected BulletsPool bulletsPool;
    protected TextureRegion bulletRegion;
    protected float bulletHeight;
    protected Vector2 bulletV;
    protected int damage;
    protected int hp;

    protected float reloadInterval;
    protected float reloadTimer;

    protected float damageAnimatedInterval = 0.01f;
    protected float damageAnimatedTimer = damageAnimatedInterval;

    public Ship(TextureRegion region,int rows, int cols, int frames) {
        super(region,rows,cols,frames);
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.bulletV = new Vector2();
    }

    public Ship(){
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.bulletV = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        damageAnimatedTimer += delta;
        if(damageAnimatedTimer >= damageAnimatedInterval){
            frame = 0;
        }
    }

    public void shoot() {
        Bullet bullet = (Bullet) bulletsPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, damage);
        shootSound.play();
    }

    public void damage(int damage){
        frame = 1;
        damageAnimatedTimer = 0f;
        hp -= damage;
        if (hp <= 0){
            destroy();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        hp = 0;
    }
}

