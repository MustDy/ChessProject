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
 * Class for storing players data.
 * @author MustDy
 */
public class Players {
    
    /**
     * name of the player.
     */
    private String name;
    
    /**
     * number of moves made.
     */
    private int moves;
    
    /**
     * variable to store current date in {@code String} representation.
     */
    private String date;

    /**
     * @param name name of the player
     * @param moves number of moves made
     * @param date variable to store current date in {@code String} representation
     */
    public Players(String name, int moves, String date) {
        this.name = name;
        this.moves = moves;
        this.date = date;
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the moves
     */
    public int getMoves() {
        return moves;
    }

    /**
     * @param moves the moves to set
     */
    public void setMoves(int moves) {
        this.moves = moves;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    
}
