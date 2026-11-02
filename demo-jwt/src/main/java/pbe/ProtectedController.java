package pbe;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProtectedController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        String username = (authentication != null) ? authentication.getName() : "desconhecido";
        return "Bem-vindo ao dashboard, " + username;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminOnly() {
        return "Área exclusiva para administradores";
    }
}
