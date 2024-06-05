package com.teste.picpay.services;

import com.teste.picpay.domain.user.User;
import com.teste.picpay.domain.user.UserType;
import com.teste.picpay.dtos.UserDTO;
import com.teste.picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void validadeTransaction(User sender , BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo lojista não esta autorizado a realizar esta operação.");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Usuário não possui saldo para realizar esta operação.");
        }
    }

    public User createUser(UserDTO userDTO){
        User user  = new User(userDTO);
       this.userRepository.save(user);

        return user;
    }

    public List<User> findAllUsers(){

        return this.userRepository.findAll();
    }


    public User findUserById(Long id)throws Exception{
        return this.userRepository.findUserById(id).orElseThrow(()-> new Exception("Usuário não encontrado!"));
    }

    public User findUserByDocument(String document)throws Exception{
        return this.userRepository.findUserByDocument(document).orElseThrow(()-> new Exception("Usuário não encontrado!"));
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }
}
