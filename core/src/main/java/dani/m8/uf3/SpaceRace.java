package dani.m8.uf3;

import com.badlogic.gdx.Game;

import dani.m8.uf3.helpers.AssetManager;
import dani.m8.uf3.screens.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SpaceRace extends Game {
    @Override
    public void create() {
        AssetManager.load();
        setScreen(new GameScreen());
    }
    @Override
    public void dispose() {
        super.dispose();
        AssetManager.dispose();
    }
}

