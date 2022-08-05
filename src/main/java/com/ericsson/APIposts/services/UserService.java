package com.ericsson.APIposts.services;

import com.ericsson.APIposts.domain.User;
import com.ericsson.APIposts.dto.UserDTO;
import com.ericsson.APIposts.repository.UserRepository;
import com.ericsson.APIposts.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User obj) {
        return userRepository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }
    public User fromDTO(UserDTO onjDto) {
        return new User(onjDto.getId(), onjDto.getName(), onjDto.getEmail());
    }
}
