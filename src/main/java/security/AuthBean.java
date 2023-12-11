package security;

import jakarta.ejb.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class AuthBean{

    private Map<String, String> loginSession = new HashMap<>();

    public void addLoginSession(String username, String sessionId){

        loginSession.put(username, sessionId);
    }

    public String getSessionId(String username){

        return loginSession.get(username);
    }

    public void removeLogin(String username){

        loginSession.remove(username);
    }

}
