package de.hhn.it.devtools.apis.candycrush;
/**
 * This class models the user profile.
 */
public class Profile {
    // Profile ID
    private String userName;
    private int score;
    /**
     * Create a new user profile.
     *
     * @param userName the name of the profile
     */
    public Profile(String userName) {
        this.userName = userName;
        score = 0;
    }
    /**
     * Get the UserName of the user profile.
     * @return userName.
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the score.
     */
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }




}
