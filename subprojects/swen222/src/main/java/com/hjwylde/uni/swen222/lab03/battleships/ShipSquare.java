package com.hjwylde.uni.swen222.lab03.battleships;


/**
 * A Ship square represents a square that contains a battle ship, but has not yet been bombed. Ship
 * Squares can be either visible or invisible (depending on which side they are).
 * 
 * @author djp
 */
public class ShipSquare extends GridSquare {

    private final BattleShip ship;
    private final Type type;

    /**
     * Construct a ShipSquare representing part of a battle ship (either a middle or end piece).
     * 
     */
    public ShipSquare(Type type, BattleShip ship) {
        this.type = type;
        this.ship = ship;
    };

    /**
     * Get the ship that this square is part of.
     * 
     * @return
     */
    public BattleShip getShip() {
        return ship;
    }

    /**
     * Determine what part of the ship this piece represents.
     * 
     * @return
     */
    public Type getType() {
        return type;
    }

    public enum Type {
        VERTICAL_TOP_END, VERTICAL_BOTTOM_END, VERTICAL_MIDDLE, HORIZONTAL_LEFT_END, HORIZONTAL_RIGHT_END, HORIZONTAL_MIDDLE
    }
}
