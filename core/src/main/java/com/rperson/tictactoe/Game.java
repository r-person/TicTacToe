package com.rperson.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rperson.tictactoe.Board;

public class Game implements Screen {
    Main main;
    AssetManager manager;
    ShapeRenderer shapeRenderer;
    Texture xTexture;
    Texture oTexture;
    int width;
    int height;
    SpriteBatch batch;
    Board board = new Board();

    public Game(Main main){
        this.main = main;
        manager = new AssetManager();
        shapeRenderer = new ShapeRenderer();
        Gdx.graphics.setTitle("Tic Tac Toe v0.0.1- X turn");
        this.batch = main.batch;
    }

    @Override
    public void show() {
        manager.load("x.bmp", Texture.class);
        manager.load("o.bmp", Texture.class);
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button != Input.Buttons.LEFT) return false;

                int y = height - screenY;

                int col = screenX / (width / 3);
                int row = y / (height / 3);
                board.turn(col, row);

                if (board.getWinner() == 0)
                    Gdx.graphics.setTitle("Tic Tac Toe v0.0.1 - " + (board.isXTurn() ? "X" : "O") + " turn");
                else
                    Gdx.graphics.setTitle("Tic Tac Toe v0.0.1 - " + board.getWinner() + " has won");

                return true;
            }
        });
        manager.finishLoading(); // Blocks the thread until done
        this.xTexture = manager.get("x.bmp", Texture.class);
        this.oTexture = manager.get("o.bmp", Texture.class);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(width / 3, 0, width / 3, height);
        shapeRenderer.line(2 * width / 3, 0, 2 * width / 3, height);
        shapeRenderer.line(0, height / 3, width, height / 3);
        shapeRenderer.line(0, 2 * height / 3, width, 2 * height / 3);
        shapeRenderer.end();
        batch.begin();
        for (int i = 0; i < board.getLength(); i++){
            for (int j = 0; j < board.getInnerLength(); j++){
                if (board.getPlace(i, j) == 'X') batch.draw(xTexture, width * i / 3, height * j / 3);
                if (board.getPlace(i, j) == 'O') batch.draw(oTexture, width * i / 3, height * j / 3);
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        if(width <= 0 || height <= 0) return;
        main.viewport.update(width, height, true);
        this.width = width;
        this.height = height;
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        manager.dispose();
        shapeRenderer.dispose();
    }
}
