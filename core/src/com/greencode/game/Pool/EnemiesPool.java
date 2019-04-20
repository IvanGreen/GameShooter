package com.greencode.game.Pool;

import com.greencode.game.base.Sprite;
import com.greencode.game.base.SpritesPool;
import com.greencode.game.sprite.Enemy;

public class EnemiesPool extends SpritesPool {
    @Override
    protected Sprite newObject() { return new Enemy(); }
}
