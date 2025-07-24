package org.example.socketexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);
    private final SimpMessagingTemplate simpMessagingTemplate;
    public NotificationController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/notify")
    public ResponseEntity<Void> sendNotification(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        simpMessagingTemplate.convertAndSend("/topic/notification", message);
        return ResponseEntity.ok().build();
    }


}
