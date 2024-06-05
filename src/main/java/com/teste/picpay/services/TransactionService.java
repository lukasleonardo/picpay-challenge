package com.teste.picpay.services;

import com.teste.picpay.domain.transaction.Transaction;
import com.teste.picpay.domain.user.User;
import com.teste.picpay.dtos.TransactionDTO;
import com.teste.picpay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender = this.userService.findUserById(transactionDTO.senderId());
        User receiver = this.userService.findUserById(transactionDTO.receiverId());

        userService.validadeTransaction(sender,transactionDTO.value());
        Boolean isAuthorized = this.authorize(sender, transactionDTO.value());

        if(!isAuthorized){
            throw new Exception("Operação não autorizada");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDate.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        this.transactionRepository.save(transaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender,"transação concluida");
        this.notificationService.sendNotification(receiver,"transação concluida");

        return transaction;
    }

    public Boolean authorize(User sender, BigDecimal amount) {
        ResponseEntity<Map> responseEntity = this.restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);
        System.out.println(responseEntity);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String message = (String)responseEntity.getBody().get("status");
            System.out.println(message);
            return "success".equalsIgnoreCase(message);
        }else return false;
    }

    public List<Transaction> findAllTransactions(){
        return this.transactionRepository.findAll();
    }

}
