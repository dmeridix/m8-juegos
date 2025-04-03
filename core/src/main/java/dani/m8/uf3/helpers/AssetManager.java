package dani.m8.uf3.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class AssetManager {
    public static Texture sheet;

    public static TextureRegion spacecraft, spacecraftDown, spacecraftUp, background;
    public static TextureRegion[] asteroid;
    public static Animation<TextureRegion> asteroidAnim;
    public static Sound explosionSound;
    public static Music music;
    public static TextureRegion[] explosion;
    public static Animation<TextureRegion> explosionAnim;
    public static BitmapFont font;

    public static void load() {
        sheet = new Texture(Gdx.files.internal("sheet.png"));
        sheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        spacecraft = new TextureRegion(sheet, 0,0,36,15);
        spacecraft.flip(false, true);
        spacecraft = new TextureRegion(sheet, 36,0,36,15);
        spacecraft.flip(false, true);
        spacecraft = new TextureRegion(sheet, 72,0,36,15);
        spacecraft.flip(false, true);
        asteroid = new TextureRegion[16];
        for(int i = 0; i < asteroid.length; i++){
            asteroid[i] = new TextureRegion(sheet, i * 34, 15,34,34);
            asteroid[i].flip(false, true);
        }
        asteroidAnim = new Animation(0.04f, asteroid);
        asteroidAnim.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
        explosion = new TextureRegion[16];
        int index = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 8; j++){
                explosion[index++] = new TextureRegion(sheet, j*64, i*64 + 49, 64, 64);
                explosion[index - 1].flip(false, true);
            }
        }
        explosionAnim = new Animation(0.04f,explosion);
        explosionAnim.setPlayMode(Animation.PlayMode.NORMAL);
        background = new TextureRegion(sheet, 0, 177, 480, 135);
        background.flip(false, true);
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Afterburner.ogg"));
        music.setVolume(0.2f);
        music.setLooping(true);
        FileHandle fontFile = Gdx.files.internal("fonts/fuente.fnt");
        font = new BitmapFont (fontFile, true);
        font.getData().setScale(0.4f);
    }
    public static void dispose() {
        sheet.dispose();
        explosionSound.dispose();
        music.dispose();
    }
}
