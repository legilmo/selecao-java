/**
 * 
 */
package br.com.indra.avaliacao.apirest.models.fuelpricehistory.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.indra.avaliacao.apirest.models.distributor.entity.Distributor;
import br.com.indra.avaliacao.apirest.models.foundation.entity.AbstractModel;
import br.com.indra.avaliacao.apirest.models.foundation.entity.IModel;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.entity.FuelPriceHistoryItem;

/**
 * @author Legilmo Oliveira
 *
 */
@Entity
@Table(name = "FuelPriceHistory", 
       uniqueConstraints=@UniqueConstraint(columnNames = {"distributorId", "collectionDate"})
		)
@EntityListeners(AuditingEntityListener.class)
public class FuelPriceHistory extends AbstractModel  implements IModel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5362053390045864335L;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Distributor.class)
	@JoinColumn(name = "distributorId", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_History_distributorId"))
	private Distributor distributor;
	
	@NotNull
	@Column(name = "collectionDate", columnDefinition="DATE DEFAULT current_date")
	@Temporal(TemporalType.DATE)
	private Date collectionDate;
	
    @OneToMany(
        mappedBy = "fuelPriceHistory",
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
    )
    private Set<FuelPriceHistoryItem> items;
 
    public FuelPriceHistory() {
		super();
		items = new HashSet<FuelPriceHistoryItem>();
	}

	public FuelPriceHistory(Distributor distributor, Date collectionDate) {
		super();
		this.distributor    = distributor;
		this.collectionDate = collectionDate;
		items = new HashSet<FuelPriceHistoryItem>();
	}
	
	public void setFuelPriceHistory(FuelPriceHistory fuelPriceHistory) {
		this.distributor    = fuelPriceHistory.getDistributor();
		this.collectionDate = fuelPriceHistory.getCollectionDate();
		this.items          = fuelPriceHistory.getItems(); 
	}


	public Integer getId() {
		return super.getId();
	}
	
	public Distributor getDistributor() {
		return distributor;
	}
	
	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}
	
	
	public Date getCollectionDate() {
		return collectionDate;
	}

    public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}
    
    public Set<FuelPriceHistoryItem> getItems() {
		return items;
	}
    
    public void setItems(Set<FuelPriceHistoryItem> items) {
		 this.items = items;
		 for (FuelPriceHistoryItem fuelPriceHistoryItem : items) {
			 fuelPriceHistoryItem.setFuelPriceHistory(this);
	     }
	}
    
    public void addItem(FuelPriceHistoryItem fuelPriceHistoryItem) {
    	if (fuelPriceHistoryItem != null) {
        	items.add(fuelPriceHistoryItem);
    	}
    }
       
	@Override
	public String toString() {
		return "FuelPriceHistory [distributor=" + distributor + ", collectionDate=" + collectionDate +  ", items=" + items + "]";
	}
	
}
