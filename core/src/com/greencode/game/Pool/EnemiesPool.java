package com.greencode.game.Pool;

import com.badlogic.gdx.audio.Sound;
import com.greencode.game.base.SpritesPool;
import com.greencode.game.math.Rect;
import com.greencode.game.sprite.Enemy;
import com.greencode.game.sprite.GamerModel;

public class EnemiesPool extends SpritesPool {

    private Rect worldBounds;
    private BulletsPool bulletsPool;
    private Sound shootSound;
    private GamerModel gamerModel;

    public EnemiesPool(BulletsPool bulletPool, Sound shootSound, Rect worldBounds, GamerModel gamerModel) {
        this.bulletsPool = bulletPool;
        this.shootSound = shootSound;
        this.worldBounds = worldBounds;
        this.gamerModel = gamerModel;

    }

    @Override
    protected Enemy newObject() { return new Enemy(bulletsPool, shootSound, worldBounds, gamerModel); }
}
