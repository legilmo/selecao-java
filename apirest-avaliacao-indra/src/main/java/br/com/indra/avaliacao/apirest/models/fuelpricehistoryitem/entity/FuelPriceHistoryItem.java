/**
 * 
 */
package br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.indra.avaliacao.apirest.models.foundation.entity.AbstractModel;
import br.com.indra.avaliacao.apirest.models.foundation.entity.IModel;
import br.com.indra.avaliacao.apirest.models.fuelpricehistory.entity.FuelPriceHistory;
import br.com.indra.avaliacao.apirest.models.product.entity.Product;

/**
 * @author Legilmo Oliveira
 *
 */
@Entity
@Table(name = "FuelPriceHistoryitem", 
       uniqueConstraints=@UniqueConstraint(columnNames = {"fuelPriceHistoryId", "productId"})
		)
@EntityListeners(AuditingEntityListener.class)
public class FuelPriceHistoryItem extends AbstractModel  implements IModel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5362053390045864335L;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = FuelPriceHistory.class)
	@JoinColumn(name = "fuelPriceHistoryId", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_HistoryITEM_fuelPriceHistory"))
	private FuelPriceHistory fuelPriceHistory;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
	@JoinColumn(name = "productId", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ITEM_productId"))
	private Product product;
		
	@NotNull
	@Column(name = "SalePrice", length = 10, precision = 3)
	private Double salePrice;

	@Column(name = "PurchaseAmount", length = 10, precision = 4)
	private Double purchaseAmount;
	
    public FuelPriceHistoryItem() {
		super();
	}

	public void setFuelPriceHistory(FuelPriceHistoryItem fuelPriceHistory) {
		this.fuelPriceHistory = fuelPriceHistory.getFuelPriceHistory();
		this.salePrice        = fuelPriceHistory.getSalePrice();
		this.product          = fuelPriceHistory.getProduct();
		this.purchaseAmount   = fuelPriceHistory.getPurchaseAmount();
	}


	public Integer getId() {
		return super.getId();
	}

	public FuelPriceHistory getFuelPriceHistory() {
		return fuelPriceHistory;
	}
	
	public void setFuelPriceHistory(FuelPriceHistory fuelPriceHistory) {
		this.fuelPriceHistory = fuelPriceHistory;
		fuelPriceHistory.getItems().add(this);
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

    
    public Double getSalePrice() {
		return salePrice;
	}
    
    public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
    
    public Double getPurchaseAmount() {
		return purchaseAmount;
	}
    
    public void setPurchaseAmount(Double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
    
	
	public void setFuelPriceHistoryItem(FuelPriceHistoryItem fuelPriceHistoryItem) {
		this.fuelPriceHistory = fuelPriceHistoryItem.getFuelPriceHistory();
		this.product          = fuelPriceHistoryItem.getProduct();
		this.salePrice        = fuelPriceHistoryItem.getSalePrice();
		this.purchaseAmount   = fuelPriceHistoryItem.getPurchaseAmount();
	}
    
	@Override
	public String toString() {
		return "FuelPriceHistory [fuelPriceHistory=" + fuelPriceHistory + ", product=" + product + ", salePrice=" + salePrice + ", purchaseAmount=" + purchaseAmount  + "]";
	}
	

	
}
