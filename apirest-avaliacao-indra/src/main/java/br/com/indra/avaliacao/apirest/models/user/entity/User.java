package br.com.indra.avaliacao.apirest.models.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.indra.avaliacao.apirest.models.foundation.entity.AbstractModel;
import br.com.indra.avaliacao.apirest.models.foundation.entity.IModel;

@Entity
@Table(name="USER",
       uniqueConstraints=@UniqueConstraint(columnNames = {"firstName", "lastName"}))
@EntityListeners(AuditingEntityListener.class)
public class User  extends AbstractModel implements IModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8809076259755608105L;
	
	@Column(name = "FIRSTNAME")
	private String firstName;
	
	@Column(name = "LASTNAME")
	private String lastName;
	
    public User() {
		super();
	}

    public String getFirstName() {
		return firstName;
	}
    
    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
    
    public String getLastName() {
		return lastName;
	}
    
    public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    

	public void setUser(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
	}
	
	
}
