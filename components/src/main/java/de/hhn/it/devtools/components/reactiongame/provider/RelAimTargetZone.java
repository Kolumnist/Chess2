package de.hhn.it.devtools.components.reactiongame.provider;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class AimTargetZone {

    private int x1; // Point A x
    private int y1; // Point A y

    private int x2; // Point B x
    private int y2; // Point B y

    private ArrayList<RelAimTarget> aimTargets;

    public AimTargetZone(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        aimTargets = new ArrayList<>();
    }

    public AimTarget addRandomAimTarget(int id) {

        int x = new Random().nextInt(x1 + x2) + x2;
        int y = new Random().nextInt(y1 + y2) + y2;

        RelAimTarget aimTarget = new RelAimTarget(id, x, y, RelAimTarget.RADIUS, generateRandomKeyforAimTarget());

        aimTargets.add(aimTarget);

        return aimTarget;
    }

    public char generateRandomKeyforAimTarget() {
        char[] chars = {'q', 'w', 'e', 'r'};
        return chars[new Random().nextInt(chars.length)];
    }

}
