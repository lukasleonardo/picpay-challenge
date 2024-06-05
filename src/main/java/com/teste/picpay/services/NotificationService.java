package com.teste.picpay.services;

import com.teste.picpay.domain.user.User;
import com.teste.picpay.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String userEmail = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(userEmail, message);
        ResponseEntity<String> notificationResponse = this.restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

        if(!(notificationResponse.getStatusCode() == HttpStatus.NO_CONTENT)){
            System.out.println("serviço fora do ar");
            throw new Exception("Serviço de Notificação está Inoperante");
        }
    }
}
