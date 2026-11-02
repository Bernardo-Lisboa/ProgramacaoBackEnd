package org.pbe.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WsHandler extends TextWebSocketHandler {

    List<WebSocketSession> conectados = new ArrayList<>();

    // este record representa a mensagem a ser enviada para o cliene
    record Dado(int id, String conteudo) {}

    // um ObjectMapper é disponibilizado pelo Spring para codificação JSON
    // Ele é implementado pela API Jackson
    final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        var addr = session.getRemoteAddress();
        String usuario = addr.toString();
        TextMessage msg = new TextMessage(usuario+ ": "+ message.getPayload());
        for(var cliente : conectados){
            if (!cliente.equals(session)){
                cliente.sendMessage(msg);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        // chama o método herdado ... ele deve fazer algo importante
        super.afterConnectionEstablished(session);
        conectados.add(session);
        // faz algo de interessante quando o websocket estiver conectado
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
        // chama o método herdado ... também deve fazer algo importante
        super.afterConnectionClosed(session, status);
        conectados.remove(session);
        // e faz algo necessário (?!) quando esse websocket for fechado
    }
}