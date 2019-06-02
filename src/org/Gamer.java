package org;

public abstract class Gamer {
    protected String sign;
    abstract boolean shoot(int x, int y);
    abstract boolean win();
}