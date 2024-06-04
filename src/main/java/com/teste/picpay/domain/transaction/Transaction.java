package com.teste.picpay.domain.transaction;

import com.teste.picpay.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "transactions")
@Table(name = "transactions")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User Sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User Receiver;



}
