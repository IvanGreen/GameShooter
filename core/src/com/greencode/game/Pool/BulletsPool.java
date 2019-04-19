package com.greencode.game.Pool;

import com.greencode.game.base.Sprite;
import com.greencode.game.base.SpritesPool;
import com.greencode.game.sprite.Bullet;

public class BulletsPool extends SpritesPool {
    @Override
    protected Sprite newObject() {
        return new Bullet();
    }
}
