package de.hhn.it.devtools.apis.spacefighter;

    public interface SpaceFighterListener {

        void changeScore();

        void spaceFighterPosition(int xPos) ;

        //void enemyPosition(int xPos, Enemy); Diese Klasse gehört dazu nur wird sie erstmal ausgeklammert da ein fehler angezeigt wird

        //void hittedEnemy(Enemy); Diese Klasse gehört dazu nur wird sie erstmal ausgeklammert da ein fehler angezeigt wird

        void updateScore(CurrentScore score);

        void gameOver();
    }

