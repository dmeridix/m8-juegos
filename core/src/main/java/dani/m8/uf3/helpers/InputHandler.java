package dani.m8.uf3.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import dani.m8.uf3.actors.Spacecraft;
import dani.m8.uf3.screens.GameScreen;

public class InputHandler implements InputProcessor {

    private Spacecraft spacecraft;
    private GameScreen screen;

    private Stage stage;


    private Vector2 stageCoord;
    int previousY = 0;

    public InputHandler(GameScreen screen) {
        this.screen = screen;
        spacecraft = screen.getSpacecraft();

        stage = screen.getStage();
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        stageCoord = stage.screenToStageCoordinates(new Vector2(screenX,screenY));
        Actor actorHit = stage.hit(stageCoord.x, stageCoord.y, true);
        if (actorHit != null)
            Gdx.app.log("HIT", actorHit.getName());
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        spacecraft.goStraight();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (Math.abs(previousY - screenY) > 2) {
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
    public boolean scrolled(float amountX, float amountY) {
        // Manejador para eventos de scroll
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}
