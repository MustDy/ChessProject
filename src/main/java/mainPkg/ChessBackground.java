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
import javax.swing.ImageIcon;

/**
 * One-method class, existing only for {@link #Background(int, int) one method}.
 * @author MustDy
 */
public class ChessBackground extends Chess {

    /**
     * Finds out which image to print in a square with given coordinates; uses
     * {@link Chess#ChessOverlay ChessOverlay}.
     *
     * @param row row of the given jLabel
     * @param column column of the given jLabel
     * @return {@code ImageIcon} object, which is roughly speaking a
     * {@code String}, containing path to the required image
     */
    public static ImageIcon Background(int row, int column) {

        ImageIcon King = new ImageIcon(ChessBackground.class.getResource("/chess/King.jpg"));
        ImageIcon Oficer = new ImageIcon(ChessBackground.class.getResource("/chess/Oficer.jpg"));
        ImageIcon Tower = new ImageIcon(ChessBackground.class.getResource("/chess/Tower.jpg"));
        ImageIcon Empty = new ImageIcon(ChessBackground.class.getResource("/chess/Empty.jpg"));

        switch (Chess.ChessOverlay[row][column]) {
            case 0:
                return Empty;

            case 1:
                return King;

            case 2:
                return Oficer;

            case 3:
                return Tower;
        }

        //Has to add it - otherwise compiler says there is no return statement. Nuff said.
        return null;
    }
}
