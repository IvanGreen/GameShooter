package com.greencode.game.Pool;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.SpritesPool;
import com.greencode.game.sprite.Explosion;

public class ExplosionsPool extends SpritesPool<Explosion> {

    private TextureAtlas atlas;
    private Sound explosionSound;

    public ExplosionsPool(TextureAtlas atlas, Sound explosionSound) {
        this.atlas = atlas;
        this.explosionSound = explosionSound;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(atlas,explosionSound);
    }
}
