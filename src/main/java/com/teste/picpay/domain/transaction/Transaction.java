package com.teste.picpay.domain.transaction;

import com.teste.picpay.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "transactions")
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
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
    private LocalDate timestamp;


}
