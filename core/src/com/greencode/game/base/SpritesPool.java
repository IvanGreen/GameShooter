package com.greencode.game.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool<T extends Sprite> {

    protected final List<T> activeObjects = new ArrayList<T>();
    protected final List<T> freeObjects = new ArrayList<T>();

    protected abstract T newObject();

    public T obtain(){
        T object;
        if (freeObjects.isEmpty()){
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        activeObjects.add(object);
        System.out.println(this.getClass().getName() + " active/free: " + activeObjects.size() + " / " + freeObjects.size());
        return object;
    }

    public void updateActiveSprites(float delta){
        for (Sprite sprite : activeObjects){
            if(!sprite.isDestroyed()){
                sprite.update(delta);
            }
        }
    }

    public void drawActiveSprites(SpriteBatch spriteBatch){
        for(int i = 0; i < activeObjects.size(); i++){
            Sprite sprite = activeObjects.get(i);
            if(!sprite.isDestroyed()){
                sprite.draw(spriteBatch);
            }
        }
    }

    public void freeAllDestroyedActiveSprites(){
        for(int i = 0; i < activeObjects.size(); i++){
            T sprite = activeObjects.get(i);
            if(sprite.isDestroyed()){
                free(sprite);
                i--;
                sprite.flushDestroy();
            }
        }
    }

    public void free(T object){
        if (activeObjects.remove(object)){
            freeObjects.add(object);
            System.out.println(this.getClass().getName() + " active/free: " + activeObjects.size() + " / " + freeObjects.size());
        }
    }

    public void freeAllActiveSprites(){
        freeObjects.addAll(activeObjects);
        activeObjects.clear();
    }

    public void dispose(){
        freeObjects.clear();
        activeObjects.clear();
    }

    public List<T> getActiveObjects() {
        return activeObjects;
    }
}
