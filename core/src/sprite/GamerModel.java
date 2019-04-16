package sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.greencode.game.base.Sprite;
import com.greencode.game.math.Rect;

public class GamerModel extends Sprite {

    private float SIZE = 0.2f;
    private Vector2 touch;
    private Vector2 v;
    private Vector2 buf;
    private Vector2 buf2;

    public GamerModel(TextureRegion region) {
        super(region);
        setHeightProportion(SIZE);
        touch = new Vector2();
        buf = new Vector2();
        buf2 = new Vector2();
        v = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(SIZE);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        buf.set(touch);
        buf2.set(v);
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
        v.set(touch.cpy().sub(pos)).setLength(0.05f);
        return false;
    }
}
