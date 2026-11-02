package pbe;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.*;

@RestController
public class SSEController {

    /*
    Handles connection to the event service
    Starts a thread for each client.
     */
    @GetMapping("/eventos")
    public SseEmitter streamSseEvents() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        // cria uma thread para emitir eventos periódicos para o cliente
        Thread.ofVirtual().start(() -> {
            try {
                // forever waits for events to send to client ...
                for (int n=0; n < 10; n++) {
                    LocalDateTime agora = LocalDateTime.now();
                    String msg = String.format("mensagem %d: %s", n, agora);
                    // envia o evento
                    emitter.send(msg);
                    Thread.sleep(1000);
                }
                emitter.complete();
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
}