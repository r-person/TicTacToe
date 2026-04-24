package com.rperson.tictactoe;

public class Board {
    private char board[][];
    private boolean isXTurn;
    private char winner;

    public Board(){
        board = new char[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        isXTurn = true;
        winner = 0;
    }

    public boolean isXTurn() {return isXTurn;}
    public char getWinner() {return winner;}
    public char getPlace(int col, int row) {return board[col][row];}
    public int getLength() {return board.length;}
    public int getInnerLength() {return board[0].length;}

    public boolean turn(int col, int row){
        if (col < 0 || col > 2 || row < 0 || row > 2) return false;
        if (board[col][row] == 0 && winner == 0){
            board[col][row] = isXTurn ? 'X' : 'O';
            if (board[0][row] == board[1][row] && board[0][row] == board[2][row]) winner = isXTurn ? 'X' : 'O';
            if (board[col][0] == board[col][1] && board[col][0] == board[col][2]) winner = isXTurn ? 'X' : 'O';
            if (col == row && board[0][0] == board[1][1] && board[0][0] == board[2][2]) winner = isXTurn ? 'X' : 'O';
            if (col + row == 2 && board[0][2] == board[1][1] && board[0][2] == board[2][0])
                winner = isXTurn ? 'X' : 'O';
            isXTurn = !isXTurn;
            return true;
        } else return false;
    }
}
