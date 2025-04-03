package dani.m8.uf3.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;

import dani.m8.uf3.helpers.InputHandler;
import dani.m8.uf3.utils.Settings;
import dani.m8.uf3.actors.Asteroid;
import dani.m8.uf3.actors.ScrollHandler;
import dani.m8.uf3.actors.Spacecraft;
import dani.m8.uf3.helpers.AssetManager;



public class GameScreen implements Screen {
    private Stage stage;
    private Spacecraft spacecraft;
    private ScrollHandler scrollHandler;
    private ShapeRenderer shapeRenderer;
    private Batch batch;

    // Para controlar la animación de la explosión
    private float explosionTime = 0;
    private boolean gameOver = false;

    private GlyphLayout textLayout;

    public GameScreen() {
        AssetManager.music.play();
        shapeRenderer = new ShapeRenderer();

        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        camera.setToOrtho(true);

        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        stage = new Stage(viewport);
        batch = stage.getBatch();


        spacecraft = new Spacecraft(Settings.SPACECRAFT_STARTX, Settings.SPACECRAFT_STARTY, Settings.SPACECRAFT_WIDTH, Settings.SPACECRAFT_HEIGHT);
        scrollHandler = new ScrollHandler();

        spacecraft.setName("spacecraft");
        stage.addActor(scrollHandler);
        stage.addActor(spacecraft);

        Gdx.input.setInputProcessor(new InputHandler(this));
        textLayout = new GlyphLayout();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        stage.draw();
        stage.act(delta);

        if (!gameOver) {
            if (scrollHandler.collides(spacecraft)) {
                AssetManager.explosionSound.play();
                stage.getRoot().findActor("spacecraft").remove();
                gameOver = true;
            }
        } else {
            batch.begin();
            batch.draw(AssetManager.explosionAnim.getKeyFrame(explosionTime, false),
                (spacecraft.getX() + spacecraft.getWidth() / 2 - 32),
                spacecraft.getY() + spacecraft.getHeight() / 2 - 32, 64, 64);
            textLayout.setText(AssetManager.font, "Game over");
            AssetManager.font.draw(batch, textLayout, Settings.GAME_WIDTH/2 - textLayout.width/2, Settings.GAME_HEIGHT/2 - textLayout.height/2);
            batch.end();

            explosionTime += delta;
        }

        //drawElements();
    }

    private void drawElements() {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(1, 0, 0, 1));
        ArrayList<Asteroid> asteroids = scrollHandler.getAsteroids();
        Asteroid asteroid;
        for (int i = 0; i < asteroids.size(); i++) {
            asteroid = asteroids.get(i);
            switch (i) {
                case 0:
                    shapeRenderer.setColor(1, 0, 0, 1);
                    break;
                case 1:
                    shapeRenderer.setColor(0, 1, 0, 1);
                    break;
                case 2:
                    shapeRenderer.setColor(0, 0, 1, 1);
                    break;
                default:
                    shapeRenderer.setColor(1, 1, 1, 1);
                    break;
            }
            shapeRenderer.circle(asteroid.getX() + asteroid.getWidth() / 2, asteroid.getY() + asteroid.getWidth() / 2, asteroid.getWidth() / 2);
        }
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    public Stage getStage() {
        return stage;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }
}
