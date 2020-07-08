/**
 * 
 */
package br.com.indra.avaliacao.apirest.models.foundation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author legilmo oliveira
 *
 */
public interface ICRUDController<T> {
	Iterable<T> list();

    T getById(@PathVariable  Integer id);

    T add(T domainObject);
    
    T update(Integer id, T domainObject);

    ResponseEntity<T> delete(Integer id);
}
