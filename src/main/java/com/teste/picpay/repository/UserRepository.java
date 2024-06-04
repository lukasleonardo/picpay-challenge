package com.teste.picpay.repository;

import java.util.Optional;

public interface UserRepository<User,Long> {
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(Long id);
}
