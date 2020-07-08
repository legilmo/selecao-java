package br.com.indra.avaliacao.apirest.models.user.services;

import br.com.indra.avaliacao.apirest.models.user.entity.User;

public interface IUserService {
	
    Iterable<User> listAllUsers();

    User getUserById(Integer id);

    User saveUser(User user);

    void deleteUser(Integer id);
    

}
