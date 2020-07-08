package br.com.indra.avaliacao.apirest.initialize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.indra.avaliacao.apirest.models.banner.repository.BannerRepository;
import br.com.indra.avaliacao.apirest.models.city.repository.CityRepository;
import br.com.indra.avaliacao.apirest.models.distributor.repository.DistributorRepository;
import br.com.indra.avaliacao.apirest.models.fuelpricehistory.repository.FuelPriceHistoryRepository;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.repository.FuelPriceHistoryItemRepository;
import br.com.indra.avaliacao.apirest.models.product.repository.ProductRepository;
import br.com.indra.avaliacao.apirest.models.region.repository.RegionRepository;
import br.com.indra.avaliacao.apirest.models.state.repository.StateRepository;
import br.com.indra.avaliacao.apirest.models.user.entity.User;
import br.com.indra.avaliacao.apirest.models.user.repository.UserRepository;

@Component
public class DemoData implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RegionRepository regionRepository;
    
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private DistributorRepository distributorRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private FuelPriceHistoryRepository fuelPriceHistoryRepository;
    
    @Autowired
    private FuelPriceHistoryItemRepository fuelPriceHistoryItemRepository;
    
    
    @Override
    @Transactional
    public void run(String...args) throws Exception {

    	User user = new User();
    	user.setFirstName("Legilmo");
    	user.setLastName("Oliveira");
    	
        userRepository.save(user);
        
    	user = new User();
    	user.setFirstName("Joao");
    	user.setLastName("Carlos");
        userRepository.save(user);
        
                
        
//        // Save Region
//        Region region = new Region();
//        region.setInitials("CO");
//        region.setName("CENTRO OESTE");
//        regionRepository.save(region);
//        
//        State state = new State();
//        state.setInitials("DF");
//        state.setName("DISTRITO FEDERAL");
//        state.setRegion(region);
//        stateRepository.save(state);
//        
//        City city = new City();
//        city.setName("BRASILIA");
//        city.setState(state);
//        cityRepository.save(city);
//        
//	   Banner banner  = new Banner();
//	   banner.setName("FEDERAL");
//	   bannerRepository.save(banner);
//	   
//	   
//	   
//	   Distributor distributor = new Distributor();
//	   distributor.setCnpj("26223104000159");
//	   distributor.setName("MM CORUMBA COMBUSTIVEIS LTDA");
//	   distributor.setBanner(banner);
//	   distributor.setCity(city);
//	   distributorRepository.save(distributor);
//        
//                
//        
//        
//        // business objects
//
//		Product product = new Product();
//		product.setName("GASOLINA");
//		product.setUnitOfMeasurement("R$ / litro");
//		productRepository.save(product);
//		
//		Product product2 = new Product();
//		product2.setName("DIESEL");
//		product2.setUnitOfMeasurement("R$ / litro");
//		productRepository.save(product2);
		
//		//1º item (master)
//		FuelPriceHistory fuelPriceHistory = new FuelPriceHistory();
//		fuelPriceHistory.setDistributor(distributor);
//		String dataColetaString= "16/01/2019";
//		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); 
//		Date dataColeta=sdf1.parse(dataColetaString);
//		
//		fuelPriceHistory.setCollectionDate(dataColeta);
//		fuelPriceHistory = fuelPriceHistoryRepository.save(fuelPriceHistory);
//		
//		//1º item
//		FuelPriceHistoryItem fuelPriceHistoryItem = new FuelPriceHistoryItem();
//		fuelPriceHistoryItem.setFuelPriceHistory(fuelPriceHistory);
//		fuelPriceHistoryItem.setProduct(product);
//		fuelPriceHistoryItem.setSalePrice(4.299);
//		fuelPriceHistoryItem.setPurchaseAmount(3.6745);	
//		
//		fuelPriceHistoryItem = fuelPriceHistoryItemRepository.save(fuelPriceHistoryItem);
//		
//		//2º item
//		FuelPriceHistoryItem fuelPriceHistoryItem2 = new FuelPriceHistoryItem();
//		fuelPriceHistoryItem2.setFuelPriceHistory(fuelPriceHistory);
//		fuelPriceHistoryItem2.setProduct(product2);
//		fuelPriceHistoryItem2.setSalePrice(3.799);
//		fuelPriceHistoryItem2.setPurchaseAmount(3.2166);
//		
//		fuelPriceHistoryItemRepository.save(fuelPriceHistoryItem2);
//		
		
//		Double media = fuelPriceHistoryItemRepository.getAvgSalesPriceByCity("BRASILIA");
//		System.out.println("MÉDIA ==>" + media);
//		
//		List<FuelPriceHistory> listFuelPriceHistory = fuelPriceHistoryRepository.getListFuelPriceHistoryByRegionInitials("CO");
//		System.out.println("Tamanho ==>" + listFuelPriceHistory.size());
//		
//		listFuelPriceHistory = fuelPriceHistoryRepository.getListFuelPriceHistoryByDistributor();
//		System.out.println("Tamanho ==>" + listFuelPriceHistory.size());
//		
//		List<AVGSalesPriceAndPurchaseAmountByBanner> listFuelPriceHistoryResult = fuelPriceHistoryItemRepository.getListAVGSalePriceAndpurchaseAmountByBanner();
//		System.out.println("Tamanho    ==>" + listFuelPriceHistoryResult.size());
//		System.out.println("           ==>" + listFuelPriceHistoryResult.get(0).getBanner().getName());
//		System.out.println("Med Preço  ==>" + listFuelPriceHistoryResult.get(0).getAvgSalesPriceAndPurchaseAmount().getAvgSalesPrice());
//		System.out.println("Med Compra ==>" + listFuelPriceHistoryResult.get(0).getAvgSalesPriceAndPurchaseAmount().getAvgPurchaseAmount());
//		
//		List<AVGSalesPriceAndPurchaseAmountByCity> listFuelPriceHistoryResult2 = fuelPriceHistoryItemRepository.getListAVGSalePriceAndpurchaseAmountByCity();
//		System.out.println("Tamanho    ==>" + listFuelPriceHistoryResult2.size());
//		System.out.println("           ==>" + listFuelPriceHistoryResult2.get(0).getCity().getName());
//		System.out.println("Med Preço  ==>" + listFuelPriceHistoryResult2.get(0).getAvgSalesPriceAndPurchaseAmount().getAvgSalesPrice());
//		System.out.println("Med Compra ==>" + listFuelPriceHistoryResult2.get(0).getAvgSalesPriceAndPurchaseAmount().getAvgPurchaseAmount());
    
    }
    
}