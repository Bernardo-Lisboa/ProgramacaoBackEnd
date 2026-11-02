package pbe;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

    private final Map<String, String> users = new HashMap<>();
    private final Map<String, String> roles = new HashMap<>();
    private final Map<String, AtomicInteger> counts = new HashMap<>();

    public UserService() {
        users.put("admin", "admin123");
        users.put("operador", "operador123");
        users.put("visitante", "visitante123");

        roles.put("admin", "ROLE_ADMIN");
        roles.put("operador", "ROLE_OPERADOR");
        roles.put("visitante", "ROLE_VISITANTE");

        users.keySet().forEach(u -> counts.put(u,new AtomicInteger(0)));
    }

    public boolean validateUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public String getUserRole(String username) {
        return roles.getOrDefault(username, "ROLE_GUEST");
    }
    public int nextCount(String username) {
        return counts
                .computeIfAbsent(username, k -> new AtomicInteger(0))
                .incrementAndGet();
    }
}