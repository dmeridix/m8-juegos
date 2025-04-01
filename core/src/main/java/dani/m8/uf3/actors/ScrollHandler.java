package dani.m8.uf3.actors;

import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.Random;

import dani.m8.uf3.utils.Methods;
import dani.m8.uf3.utils.Settings;

public class ScrollHandler extends Group {
    Background bg, bg_back;
    int numAsteroids;
    ArrayList<Asteroid> asteroids;
    Random r;

    public ScrollHandler(){
        bg = new Background(0, 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);
        bg_back = new Background (bg.getTailX(), 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);

        addActor (bg);
        addActor (bg_back);

        r = new Random();
        numAsteroids = 3;

        asteroids = new ArrayList<Asteroid>();


        float newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID) * 34;

        Asteroid asteroid = new Asteroid (Settings.GAME_WIDTH, r.nextInt( Settings.GAME_HEIGHT - (int) newSize), newSize, newSize, Settings. ASTEROID_SPEED);
        asteroids.add(asteroid);
        addActor(asteroid);

        for (int i=1; i < numAsteroids; i++) {
            newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID) * 34; // Afegim l'asteroide
            asteroid = new Asteroid (asteroids.get(asteroids.size()-1).getTailX() + Settings.ASTEROID_GAP, r.nextInt(Settings.GAME_HEIGHT - (int) newSize), newSize, newSize, Settings.ASTEROID_SPEED);
            asteroids.add(asteroid);
            addActor (asteroid);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (bg.isLeftOfScreen()) {
            bg.reset(bg_back.getTailX());
        }else if (bg_back.isLeftOfScreen()) {
            bg_back.reset(bg.getTailX());
        }
        for (int i = 0; i < asteroids.size(); i++){
            Asteroid asteroid = asteroids.get(i);
            if (asteroid.isLeftOfScreen()){
                if (i == 0) {
                    asteroid.reset(asteroids.get(asteroids.size() - 1).getTailX() + Settings.ASTEROID_GAP);
                } else {
                    asteroid.reset(asteroids.get(i - 1).getTailX() + Settings.ASTEROID_GAP);
                }
            }
        }
    }
    public boolean collides(Spacecraft nau) {
        // Comprovem les col·lisions entre cada asteroide i la nau

        for (Asteroid asteroid : asteroids) {
            if (asteroid.collides(nau)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Asteroid> getAsteroids(){
        return asteroids;
    }
}
