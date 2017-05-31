package mainPkg;

/*
 * #%L
 * LipskiyAssignment
 * %%
 * Copyright (C) 2016 Debreceni Egyetem
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */



/**
 * One-method class, existing only for {@link #Checker(int, int) one method}.
 * @author MustDy
 */
public class NeghbourChecker {

    //method that checks if there is an empty space near the given square  

    /**
     * Checks if there are any empty squares around given one; if exists - swaps
     * values of empty one in {@link Chess#ChessOverlay overlay} with initial square; 
     * returns after swapping.
     * For every chess piece has a unique checker - because each type of 
     * chess piece moves by its own rules.
     * @param row row of the given square
     * @param column row of the given square
     */
    public static void Checker(int row, int column) {

        //Recives the type of chesspiece on give square       
        switch (Chess.ChessOverlay[row][column]) {

            //Nothing is needed to be done if square is empty
            case 0:
                return;

            //Checking nearby squares for King (here for() statement works well)
            case 1:
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = column - 1; j <= column + 1; j++) {
                        if (Chess.ChessOverlay[i][j] == 0) {
                            Chess.ChessOverlay[row][column] = 0;
                            Chess.ChessOverlay[i][j] = 1;
                            return;
                        }
                    }
                }
                break;

            //Checking nearby squares for Oficer (sometimes program stopped, if here was for() statement)
            case 2: {
                int i1 = row - 1;
                int j1 = column - 1;
                if (Chess.ChessOverlay[i1][j1] == 0) {
                    Chess.ChessOverlay[row][column] = 0;
                    Chess.ChessOverlay[i1][j1] = 2;
                    return;
                }
                int i2 = row - 1;
                int j2 = column + 1;
                if (Chess.ChessOverlay[i2][j2] == 0) {
                    Chess.ChessOverlay[row][column] = 0;
                    Chess.ChessOverlay[i2][j2] = 2;
                    return;
                }
                int i3 = row + 1;
                int j3 = column - 1;
                if (Chess.ChessOverlay[i3][j3] == 0) {
                    Chess.ChessOverlay[row][column] = 0;
                    Chess.ChessOverlay[i3][j3] = 2;
                    return;
                }
                int i4 = row + 1;
                int j4 = column + 1;
                if (Chess.ChessOverlay[i4][j4] == 0) {
                    Chess.ChessOverlay[row][column] = 0;
                    Chess.ChessOverlay[i4][j4] = 2;
                    return;
                }
                break;

            } //Checking nearby squares for Tower (sometimes program stopped, if here was for() statement)
            case 3: {
                int i1 = row - 1;
                int j1 = column;
                if (Chess.ChessOverlay[i1][j1] == 0) {
                    Chess.ChessOverlay[row][column] = 0;
                    Chess.ChessOverlay[i1][j1] = 3;
                    return;
                }
                int i2 = row + 1;
                int j2 = column;
                if (Chess.ChessOverlay[i2][j2] == 0) {
                    Chess.ChessOverlay[row][column] = 0;
                    Chess.ChessOverlay[i2][j2] = 3;
                    return;
                }
                int i3 = row;
                int j3 = column - 1;
                if (Chess.ChessOverlay[i3][j3] == 0) {
                    Chess.ChessOverlay[row][column] = 0;
                    Chess.ChessOverlay[i3][j3] = 3;
                    return;
                }
                int i4 = row;
                int j4 = column + 1;
                if (Chess.ChessOverlay[i4][j4] == 0) {
                    Chess.ChessOverlay[row][column] = 0;
                    Chess.ChessOverlay[i4][j4] = 3;
                    return;
                }
                break;
            }
        }
    }
}
