package dani.m8.uf3.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import dani.m8.uf3.helpers.AssetManager;
import dani.m8.uf3.utils.Methods;
import dani.m8.uf3.utils.Settings;
import java.util.Random;

public class Asteroid extends Scrollable {

    private float runTime;
    private Circle collisionCircle;
    public Asteroid(float x, float y, float width, float height, float velocity) {
        super(x, y, width, height, velocity);
        runTime = Methods.randomFloat(0, 1);

        collisionCircle = new Circle();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        runTime += delta;

        // Actualitzem el cercle de col·lisions (punt central de l'asteroide i del radi).
        collisionCircle.set(position.x + width / 2.0f, position.y + width / 2.0f, width / 2.0f);
    }
    // Retorna true si hi ha col·lisió
    public boolean collides(Spacecraft nau) {
        if (position.x <= nau.getX() + nau.getWidth()) {
            // Comprovem si han col·lisionat sempre que l'asteroide es trobi a
            // la mateixa alçada que l'spacecraft
            return Intersector.overlaps(collisionCircle, nau.getCollisionRect());
        }
        return false;
    }

    @Override
    public void reset(float newX){
        super.reset(newX);
        float newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID);
        width = height = 34 * newSize;
        position.y = new Random().nextInt(Settings.GAME_HEIGHT - (int) height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.asteroidAnim.getKeyFrame(runTime), position.x, position.y, width, height);
    }

    public float getRunTime(){
        return runTime;
    }
}
