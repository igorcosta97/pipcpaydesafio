package com.picpay.picpacy.services;

import com.picpay.picpacy.domain.user.User;
import com.picpay.picpacy.domain.user.UserType;
import com.picpay.picpacy.dtos.UserDTO;
import com.picpay.picpacy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transação");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User createUser(UserDTO user){
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        this.saveUser(newUser);
        return newUser;
    }
    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }

    public List<User> getAllUsers() {
        var users = this.repository.findAll();
        return users;
    }
}
