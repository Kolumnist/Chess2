package de.hhn.it.devtools.apis.spacefighter;

    public interface SpaceFighterListener {

        void changeScore();

        void spaceFighterPosition(int xPos) ;

        void enemyPosition(int xPos, Enemy position);

        void hittedEnemy(Enemy amount);

        void updateScore(CurrentScore score);

        void gameOver();
    }

