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

public class Game implements Screen {
    Main main;
    AssetManager manager;
    ShapeRenderer shapeRenderer;
    Texture xTexture;
    Texture oTexture;
    int width;
    int height;
    char board[][];
    boolean isXTurn;
    SpriteBatch batch;
    char winner;

    public Game(Main main){
        this.main = main;
        manager = new AssetManager();
        shapeRenderer = new ShapeRenderer();
        board = new char[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        isXTurn = true;
        Gdx.graphics.setTitle("Tic Tac Toe v0.0.0 - X turn");
        batch = new SpriteBatch();
        winner = 0;
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

                if (col < 0 || col > 2 || row < 0 || row > 2) return false;

                if (board[col][row] == 0 && winner == 0){
                    board[col][row] = isXTurn ? 'X' : 'O';
                    if (board[0][row] == board[1][row] && board[0][row] == board[2][row]) winner = isXTurn ? 'X' : 'O';
                    if (board[col][0] == board[col][1] && board[col][0] == board[col][2]) winner = isXTurn ? 'X' : 'O';
                    if (col == row && board[0][0] == board[1][1] && board[0][0] == board[2][2]) winner = isXTurn ? 'X' : 'O';
                    if (Math.min(col, row) == Math.max(col, row) - 2 && board[0][2] == board[1][1] && board[0][2] == board[2][0])
                        winner = isXTurn ? 'X' : 'O';
                    isXTurn = !isXTurn;
                }

                if (winner == 0)
                    Gdx.graphics.setTitle("Tic Tac Toe v0.0.0 - " + (isXTurn ? "X" : "O") + " turn");
                else
                    Gdx.graphics.setTitle("Tic Tac Toe v0.0.0 - " + winner + " has won");

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
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == 'X') batch.draw(xTexture, width * i / 3, height * j / 3);
                if (board[i][j] == 'O') batch.draw(oTexture, width * i / 3, height * j / 3);
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
