package com.greencode.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.Pool.BulletsPool;
import com.greencode.game.Pool.ExplosionsPool;
import com.greencode.game.base.Ship;
import com.greencode.game.math.Rect;


public class Enemy extends Ship {

    private enum State {DESCENT, FIGHT}
    private State state;
    private Vector2 descentV;

    private GamerModel gamerModel;

    public Enemy(BulletsPool bulletPool, ExplosionsPool explosionsPool, Sound shootSound, Rect worldBounds, GamerModel gamerModel) {
        this.gamerModel = gamerModel;
        this.bulletsPool = bulletPool;
        this.explosionsPool = explosionsPool;
        this.worldBounds = worldBounds;
        this.shootSound = shootSound;
        this.descentV = new Vector2(0, -0.3f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getTop() <= worldBounds.getTop()) {
            state = State.FIGHT;
            v.set(v0);
        }
        if (state == State.FIGHT) {
            reloadTimer += delta;
            if (reloadTimer >= reloadInterval) {
                reloadTimer = 0f;
                shoot();
            }
        }
        if (getBottom() < worldBounds.getBottom()){
            destroy();
            gamerModel.damage(damage);
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int bulletDamage,
            float reloadInterval,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.damage = bulletDamage;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
        this.hp = hp;
        v.set(descentV);
        reloadTimer = reloadInterval;
        state = State.DESCENT;
    }

    public boolean isBulletCollision(Rect bullet){
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y
                );
    }
}
