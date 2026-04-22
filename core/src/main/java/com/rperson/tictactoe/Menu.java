package com.rperson.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/** First screen of the application. Displayed after the application is created. */
public class Menu implements Screen {
    Main main;
    TextButton d2s3b;
    Stage stage;
    int width;
    int height;

    public Menu(Main main){
        this.main = main;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        d2s3b = new TextButton("2D 3x3 game", main.style);
        d2s3b.pack();
        float x = (stage.getWidth() - d2s3b.getWidth()) / 2;
        float y = (stage.getHeight() - d2s3b.getHeight()) / 2;
        d2s3b.setPosition(x, y);

        stage.addActor(d2s3b);

        d2s3b.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new Game(main)); // switch screen
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if(width <= 0 || height <= 0) return;
        main.viewport.update(width, height, true);
        this.width = width;
        this.height = height;
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
