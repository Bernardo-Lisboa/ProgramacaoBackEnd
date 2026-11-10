package org.pbe.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final AvaliacaoRepository avaliacaoRepository;

    public WebSocketConfig(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsHandler(), "/ws");
        registry.addHandler(avaliacaoHandler(), "/ws/avaliacoes");

    @Bean
    public WebSocketHandler wsHandler() {
        return new WsHandler();
    }

    @Bean
    public WebSocketHandler avaliacaoHandler() {
        return new AvaliacaoWsHandler(avaliacaoRepository);
    }

    @Bean
    public WebSocketHandler avaliacaoHandler() {
        return new AvaliacaoWsHandler(avaliacaoRepository);
    }
}
