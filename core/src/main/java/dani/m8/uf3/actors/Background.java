package dani.m8.uf3.actors;

import com.badlogic.gdx.graphics.g2d.Batch;

import dani.m8.uf3.helpers.AssetManager;

public class Background extends Scrollable {
    public Background(float x, float y, float width, float height, float velocity){
        super(x,y,width,height,velocity);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.disableBlending();

        batch.draw(AssetManager.background,
            position.x,
            position.y,
            width,
            height);

        batch.enableBlending();
    }
}
