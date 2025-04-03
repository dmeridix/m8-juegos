package dani.m8.uf3.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;

import dani.m8.uf3.helpers.AssetManager;
import dani.m8.uf3.utils.Methods;
import dani.m8.uf3.utils.Settings;
import java.util.Random;

public class Asteroid extends Scrollable {

    private Circle collisionCircle;

    Random r;
    int assetAsteroid;

    public Asteroid(float x, float y, float width, float height, float velocity) {
        super(x, y, width, height, velocity);

        // Creem el cercle
        collisionCircle = new Circle();

        /* Accions */
        r = new Random();
        assetAsteroid = r.nextInt(15);

        this.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.rotateBy(-90f, 0.2f)));
        setOrigin();
    }

    public void setOrigin() {
        this.setOrigin(width / 2 + 1, height / 2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Actualitzem el cercle de col·lisions (punt central de l'asteroide i el radi).
        collisionCircle.set(position.x + width / 2.0f, position.y + width / 2.0f, width / 2.0f);
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);

        // Obtenim un número aleatori entre MIN i MAX
        float newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID);

        // Modificarem l'alçada i l'amplada segons l'aleatori anterior
        width = height * 3.4f * newSize;

        // La posició serà un valor aleatori entre 0 i l'alçada de l'aplicació menys l'alçada de l'asteroide
        position.y = new Random().nextInt(Settings.GAME_HEIGHT - (int) height);

        setOrigin();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.asteroid[assetAsteroid], position.x, position.y,
            this.getOriginX(), this.getOriginY(), width, height, this.getScaleX(),
            this.getScaleY(), this.getRotation());
    }

    // Retorna true si hi ha col·lisió
    public boolean collides(Spacecraft nau) {

        if (position.x <= nau.getX() + nau.getWidth()) {
            // Comprovem si han col·lisionat sempre que l'asteroide estigui a la mateixa alçada que la nau
            return (Intersector.overlaps(collisionCircle, nau.getCollisionRect()));
        }
        return false;
    }
}
