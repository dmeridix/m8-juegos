package dani.m8.uf3.helpers;

import com.badlogic.gdx.InputProcessor;

import dani.m8.uf3.actors.Spacecraft;
import dani.m8.uf3.screens.GameScreen;

public class InputHandler implements InputProcessor {

    private Spacecraft spacecraft;
    private GameScreen screen;

    int previousY = 0;
    public InputHandler(GameScreen screen){
        this.screen = screen;
        spacecraft = screen.getSpacecraft();
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }



    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        spacecraft.goStraight();

        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        previousY = screenY;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // Posem un llindar per evitar gestionar events quan el dit està quiet
        if (Math.abs(previousY - screenY) > 2) {

            // Si la Y és major que la que tenim
            // guardada és que va cap avall
            if (previousY < screenY) {
                spacecraft.goDown();
            } else {
                // En cas contrari cap amunt
                spacecraft.goUp();
            }
        }

        // Guardem la posició de la Y
        previousY = screenY;
        return true;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }



    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
