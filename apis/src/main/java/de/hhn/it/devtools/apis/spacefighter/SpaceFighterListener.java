package de.hhn.it.devtools.apis.spacefighter;

import de.hhn.it.devtools.apis.spacefighter.Exception.MissTheEnemieException;
import de.hhn.it.devtools.apis.spacefighter.Exception.NotReachedScoreException;

public interface SpaceFighterListener {

        void spaceFighterPosition(int xPos) ;

        void enemyPosition(int xPos, Enemy position);

        void hittedEnemy(Enemy amount);

        void updateScore(CurrentScore score)throws MissTheEnemieException;

        void gameOver();

        void spwaningEnemies(int score) throws NotReachedScoreException;

        void score();
    }

