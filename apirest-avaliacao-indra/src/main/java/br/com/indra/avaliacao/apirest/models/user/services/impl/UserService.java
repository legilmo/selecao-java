package br.com.indra.avaliacao.apirest.models.user.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.indra.avaliacao.apirest.models.user.entity.User;
import br.com.indra.avaliacao.apirest.models.user.repository.UserRepository;
import br.com.indra.avaliacao.apirest.models.user.services.IUserService;

@Service
public class UserService implements IUserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepository;

    
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	

    @Transactional(readOnly = true)
	@Override
	public Iterable<User> listAllUsers() {
        logger.debug("UserService::listAllUser called");
        Iterable<User> users = userRepository.findAll(); 
        return users;
	}

	@Override
	public User getUserById(Integer id) {
        logger.debug("UserService::getById called");
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
        	User u = user.get();
        	//u.setPicture(ImageUtils.getBase64Image(u.getPictureName(), u.getPictureExtension()));
        	return u;
        } else {
        	return null;
        }
        
	}

	@Transactional
	@Override
	public User saveUser(User user) {
        logger.debug("UserService::saveUser called");

        return this.userRepository.save(user);
	}

	@Transactional
	@Override
	public void deleteUser(Integer id) {
        logger.debug("UserService::deleteUser called");        
        this.userRepository.deleteById(id);
	}



}
