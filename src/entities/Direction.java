package entities;

import java.util.Random;

import static jdk.internal.net.http.http3.frames.AbstractHttp3Frame.RANDOM;

public enum Direction {UP, DOWN, LEFT, RIGHT, NONE;

    public Direction opposite() {
        switch (this) {
            case UP:    return DOWN;
            case DOWN:  return UP;
            case LEFT:  return RIGHT;
            case RIGHT: return LEFT;
            default:    return NONE;
        }
    }

    public static Direction random() {
        Random random = new Random();
        Direction[] dirs = { UP, DOWN, LEFT, RIGHT,NONE,NONE,NONE,NONE,NONE,NONE };
        return dirs[random.nextInt(dirs.length)];
    }
}

