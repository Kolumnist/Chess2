package de.hhn.it.devtools.apis.spacefighter;

import de.hhn.it.devtools.apis.spacefighter.Exception.MissTheEnemieException;

public interface SpaceFighterListener {

        void changeScore();

        void spaceFighterPosition(int xPos) ;

        void enemyPosition(int xPos, Enemy position);

        void hittedEnemy(Enemy amount);

        void updateScore(CurrentScore score)throws MissTheEnemieException;

        void gameOver();
    }

