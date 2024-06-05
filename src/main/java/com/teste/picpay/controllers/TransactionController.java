package com.teste.picpay.controllers;

import com.teste.picpay.domain.transaction.Transaction;
import com.teste.picpay.dtos.TransactionDTO;
import com.teste.picpay.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = this.transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAllTransactions(){
        List<Transaction> transactions = this.transactionService.findAllTransactions();

        return new ResponseEntity<>(transactions,HttpStatus.OK);
    }

}
