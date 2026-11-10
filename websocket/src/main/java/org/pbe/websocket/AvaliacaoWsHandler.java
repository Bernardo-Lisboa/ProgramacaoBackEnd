package org.pbe.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AvaliacaoWsHandler extends TextWebSocketHandler {

    record NovaAvaliacao(String nome, int estrelas, String comentario) {}
    record Erro(String erro) {}

    private final List<WebSocketSession> conectados = new CopyOnWriteArrayList<>();
    private final AvaliacaoRepository repo;
    private final ObjectMapper mapper = new ObjectMapper();

    public AvaliacaoWsHandler(AvaliacaoRepository repo) {
        this.repo = repo;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        conectados.add(session);
        enviar(session, mapper.writeValueAsString(repo.findAll()));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        NovaAvaliacao n;
        try {
            n = mapper.readValue(message.getPayload(), NovaAvaliacao.class);
        } catch (Exception e) {
            enviar(session, mapper.writeValueAsString(new Erro("JSON inválido")));
            return;
        }

        if (n.nome() == null || n.nome().isBlank()
                || n.estrelas() < 0 || n.estrelas() > 5
                || (n.comentario() != null && n.comentario().length() > 1000)) {
            enviar(session, mapper.writeValueAsString(new Erro("avaliação inválida")));
            return;
        }

        Avaliacao salva = repo.save(new Avaliacao(n.nome(), n.estrelas(), n.comentario()));
        String json = mapper.writeValueAsString(salva);
        for (var cliente : conectados) {
            enviar(cliente, json);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        conectados.remove(session);
    }

    private void enviar(WebSocketSession s, String json) throws IOException {
        synchronized (s) {
            s.sendMessage(new TextMessage(json));
        }
    }
}