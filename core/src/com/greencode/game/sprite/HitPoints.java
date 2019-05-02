package com.greencode.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.greencode.game.base.Sprite;
import com.greencode.game.math.Rect;

public class HitPoints extends Sprite {

    private GamerModel gamerModel;
    private int FULL_HP;
    private int HALF_HP;
    private int FOURTH_HP;

    public HitPoints(TextureAtlas atlas, GamerModel gamerModel) {
        super(atlas.findRegion("hp"),1,4,4);
        this.gamerModel = gamerModel;
        this.FULL_HP = gamerModel.getHp();
        this.HALF_HP = FULL_HP / 2;
        this.FOURTH_HP = HALF_HP / 2;
        setHeightProportion(0.10f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setTop(worldBounds.getTop() - 0.005f);
        setRight(worldBounds.getRight() - 0.005f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (gamerModel.getHp() == FULL_HP)
        {
            frame = 0;
        } else if (gamerModel.getHp() < FULL_HP && gamerModel.getHp() >= (FULL_HP - FOURTH_HP)){
            frame = 1;
        } else if (gamerModel.getHp() < (FULL_HP - FOURTH_HP) && gamerModel.getHp() >= HALF_HP ){
            frame = 2;
        } else {
            frame = 3;
        }
    }
}
