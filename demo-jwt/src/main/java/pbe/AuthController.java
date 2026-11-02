package pbe;

import jakarta.servlet.http.HttpServletResponse;
import pbe.User;
import pbe.JwtUtil;
import pbe.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse resp) {
        if (userService.validateUser(user.getUsername(), user.getPassword())) {
            String role = userService.getUserRole(user.getUsername());
            int count = userService.nextCount(user.getUsername());
            String token = jwtUtil.generateToken(user.getUsername(), role, count);
            return "Bearer " + token;
        }

        resp.setStatus(403);
        return "Credenciais inválidas";
    }
}