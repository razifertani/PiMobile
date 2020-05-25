package edu.codename1.entities;


public class Session {

    private static user User = null;

    public static void start(user currentUser) {
        User = currentUser;
    }

    public static user getCurrentSession() {
        if (User != null) {
            return User;
        }
        return null;
    }

    public static void close() throws Exception {
        if (User != null) {
            User = null;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }

}
