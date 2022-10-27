package de.hhn.it.devtools.apis.candycrush;
/**
 * This class models the user profile.
 */
public class Profile {
    // Profile ID
    private final long id;
    /**
     * Create a new user profile.
     *
     * @param name the name of the profile
     */
    public Profile(String name) {
        id = 0;
    }
    /**
     * Get the ID of the user profile. (return)
     */
    public long getId() {
        return id;
    }

}
