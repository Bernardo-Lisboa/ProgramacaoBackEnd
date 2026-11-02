package org.pbe.auth0;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class MeuController {

    @GetMapping
    public String login(Principal p) {
        OAuth2AuthenticationToken tok = (OAuth2AuthenticationToken) p;
        var princ = tok.getPrincipal();

        StringBuilder sb = new StringBuilder();
        sb.append("<br><br>");
        sb.append("<table>");
        for (var par: princ.getAttributes().entrySet()) {
            sb.append("<tr><td>");
            sb.append(par.getKey());
            sb.append("</td><td>");
            var val = par.getValue();
            if (val != null) {
                sb.append(par.getValue().toString());
            }
            sb.append("</td></tr>");
        }
        sb.append("</table>");
        return String.format("Tudo certo: %s (%s) ... %s!!!", princ.getName(), tok.getAuthorizedClientRegistrationId(), sb.toString());
    }
}
