package br.com.indra.avaliacao.apirest.models.user.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.indra.avaliacao.apirest.models.user.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
//	User findByCredential(Credential credential);
}
