package com.rperson.tictactoe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class Main extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public FitViewport viewport;
    public TextButton.TextButtonStyle style;
    public Texture texture;

    public void create() {
        batch = new SpriteBatch();
        // use libGDX's default font
        font = new BitmapFont();
        viewport = new FitViewport(8, 5);

        //font has 15pt, but we need to scale it to our viewport by ratio of viewport height to screen height
        font.setUseIntegerPositions(false);
        font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());

        // Defining style for buttons
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();
        Drawable background = new TextureRegionDrawable(new TextureRegion(texture));
        style = new TextButton.TextButtonStyle();
        style.up = background;
        style.down = background;
        style.font = font;
        style.fontColor = Color.WHITE;
        style.downFontColor = Color.YELLOW;

        this.setScreen(new Menu(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        texture.dispose();
    }
}
