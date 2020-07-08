/**
 * 
 */
package br.com.indra.avaliacao.apirest.models.city.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.indra.avaliacao.apirest.models.foundation.entity.AbstractModel;
import br.com.indra.avaliacao.apirest.models.foundation.entity.IModel;
import br.com.indra.avaliacao.apirest.models.state.entity.State;

/**
 * @author Legilmo Oliveira
 *
 */
@Entity
@Table(name = "city")
@EntityListeners(AuditingEntityListener.class)
public class City extends AbstractModel  implements IModel  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3338794845627153473L;

	@NotNull
	@Size(max = 120)
	@Column(name = "name", nullable = false, length = 120, unique = true)
	private String name;
	
	
    @JoinColumn(name="stateId", nullable = false, referencedColumnName = "id", 
			foreignKey = @ForeignKey(name = "FK_CITY_state"))
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private State state;  
	
    public City() {
		super();
	}

	public City(String name) {
		super();
		
		this.name = name;
		
	}


	public Integer getId() {
		return super.getId();
	}
	

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
    public State getState() { 
	   return state;
    }
   
    public void setState(State state) {
		this.state = state;
	}
	
	public void setCity(City city) {
		this.name    = city.getName();
		this.state   = city.getState();
	}

	
	@Override
	public String toString() {
		return "City [name=" + name + ", state=" + state + "]";
	}

	
}
