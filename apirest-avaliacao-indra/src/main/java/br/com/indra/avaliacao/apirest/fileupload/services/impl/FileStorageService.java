package br.com.indra.avaliacao.apirest.fileupload.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.indra.avaliacao.apirest.fileupload.exception.FileStorageException;
import br.com.indra.avaliacao.apirest.fileupload.exception.MyFileNotFoundException;
import br.com.indra.avaliacao.apirest.fileupload.services.IFileStorageService;
import br.com.indra.avaliacao.apirest.models.banner.entity.Banner;
import br.com.indra.avaliacao.apirest.models.banner.repository.BannerRepository;
import br.com.indra.avaliacao.apirest.models.city.entity.City;
import br.com.indra.avaliacao.apirest.models.city.repository.CityRepository;
import br.com.indra.avaliacao.apirest.models.distributor.entity.Distributor;
import br.com.indra.avaliacao.apirest.models.distributor.repository.DistributorRepository;
import br.com.indra.avaliacao.apirest.models.fuelpricehistory.entity.FuelPriceHistory;
import br.com.indra.avaliacao.apirest.models.fuelpricehistory.repository.FuelPriceHistoryRepository;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.entity.FuelPriceHistoryItem;
import br.com.indra.avaliacao.apirest.models.fuelpricehistoryitem.repository.FuelPriceHistoryItemRepository;
import br.com.indra.avaliacao.apirest.models.product.entity.Product;
import br.com.indra.avaliacao.apirest.models.product.repository.ProductRepository;
import br.com.indra.avaliacao.apirest.models.region.entity.Region;
import br.com.indra.avaliacao.apirest.models.region.repository.RegionRepository;
import br.com.indra.avaliacao.apirest.models.state.entity.State;
import br.com.indra.avaliacao.apirest.models.state.repository.StateRepository;
import br.com.indra.avaliacao.apirest.util.Util;

@Service
public class FileStorageService  implements IFileStorageService{

    private final Path fileStorageLocation = Paths.get("./");
    private static final String HEADER_COLUMN_UM = "Região - Sigla";
    
    private RegionRepository regionRepository;
    private StateRepository stateRepository;
    
    private CityRepository   cityRepository;
    private BannerRepository bannerRepository;
    private DistributorRepository distributorRepository;    
    private ProductRepository productRepository;  
    
    private FuelPriceHistoryRepository fuelPriceHistoryRepository;
    private FuelPriceHistoryItemRepository fuelPriceHistoryItemRepository;

    
    @Autowired
    public void setFuelPriceHistoryItemRepository(FuelPriceHistoryItemRepository fuelPriceHistoryItemRepository ) {
        this.fuelPriceHistoryItemRepository = fuelPriceHistoryItemRepository;
    }
    
    @Autowired
    public void setFuelPriceHistoryRepository(FuelPriceHistoryRepository fuelPriceHistoryRepository ) {
        this.fuelPriceHistoryRepository = fuelPriceHistoryRepository;
    }
    
    
    
    @Autowired
    public void setRegionRepository(RegionRepository regionRepository ) {
        this.regionRepository = regionRepository; 
    }
    
    @Autowired
    public void setStateRepository(StateRepository stateRepository ) {
        this.stateRepository = stateRepository;
    }

    @Autowired
    public void setCityRepository(CityRepository cityRepository ) {
        this.cityRepository = cityRepository;
    }
    
    @Autowired
    public void setBannerRepository(BannerRepository bannerRepository ) {
        this.bannerRepository = bannerRepository;
    }

    @Autowired
    public void setDistributorRepository(DistributorRepository distributorRepository ) {
        this.distributorRepository = distributorRepository;
    }
    
    @Autowired
    public void setProductRepository(ProductRepository productRepository ) {
        this.productRepository = productRepository;
    }
    
   
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
 
            if ((file != null) && (!file.isEmpty())) {
            	
            	populateAllFuelPriceHistoryByCSVFile(file.getInputStream());	
            }
            
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    } 

		@Override
		public void populateAllFuelPriceHistoryByCSVFile(InputStream bodyCSVFile) {
			BufferedReader  br = new BufferedReader(new InputStreamReader(bodyCSVFile));
		    
		    String line;
			try {
				line = br.readLine();
				if (line != null) {
					String[] line1 = line.split("\t");
					 
					if ((line1[0].equals(HEADER_COLUMN_UM)) || (line1[0].length() > 10)) {
						line = br.readLine();
					}					
				}	
				
			    while ((line != null) && (!line.equals(""))) {
			    	populateAllBYCSV(line);
				    line = br.readLine();
			    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 }
		  
		@Transactional
        private void populateAllBYCSV(String line) {
        	StringTokenizer st = new StringTokenizer(line , "\t");   
		       String valorCompra   = "0.0";
		       String unidadeMedida = "";
		       String bandeira      = "";
		       
		       String siglaRegiao  = st.nextToken();
		       String siglalEstado = st.nextToken();
		       String cidade       = st.nextToken();
		       String revenda      = st.nextToken();
		       String cnpj         = st.nextToken();
		       String nomeProduto  = st.nextToken();
		       String dataColeta   = st.nextToken();
		       String valorVenda   = st.nextToken();
		       valorVenda = valorVenda.replace(",", ".");
		       
		       String coluna9 = st.nextToken();
		       if ((coluna9 != null) && (!coluna9.isEmpty()) && (Util.isNumber(coluna9.trim().substring(0,1)))) {
		    	   valorCompra = coluna9;
			       unidadeMedida = st.nextToken();
			       bandeira      = st.nextToken();
		       } else {
		    	   unidadeMedida = coluna9;
		    	   bandeira      = st.nextToken();
		       }
		       
		       valorCompra = valorCompra.replace(",", ".");
		       
            	SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); 
            	Region  region;
            	State   state;
            	City    city;
            	Banner  banner;
            	Distributor distributor;
            	Product     product;
            	FuelPriceHistory fuelPriceHistory;
                FuelPriceHistoryItem fuelPriceHistoryItem;
                
        		//region
                region  = regionRepository.getRegionByInitials(siglaRegiao);
        		if (region == null) {
        		   region = new Region(siglaRegiao);  
        		   regionRepository.save(region);
        		}
				
        		//state
        		state  = stateRepository.getStateByInitials(siglalEstado);
        		if (state == null) {
        			state = new State(siglalEstado);  
        		}
    			state.setRegion(region);
    			stateRepository.save(state);
				
        		//city
        		city  = cityRepository.findByName(cidade);
        		if (city == null) {
        			city = new City(cidade);  
        		}
    			city.setState(state);
    			cityRepository.save(city);
        		
        		//banner
        		banner  = bannerRepository.findByName(bandeira);
        		if (banner == null) {
        			banner = new Banner(bandeira);  
        			bannerRepository.save(banner);
        		}
        		
        		//distributor
        		distributor = distributorRepository.findByCNPJ(cnpj);
        		if (distributor == null) {
        			distributor = new Distributor(cnpj); 
        		}
     			distributor.setName(revenda);
    			distributor.setBanner(banner);
    			distributor.setCity(city);
    			distributorRepository.save(distributor);
     		
        		//Product
        		product = productRepository.findByName(nomeProduto);
        		if (product == null) {
        			product = new Product(nomeProduto); 
        		}
    			product.setUnitOfMeasurement(unidadeMedida);
    			productRepository.save(product);
        		
        		//FuelPriceHistory
        		Date collectionDateFormat = new Date();
        		try {
					collectionDateFormat = sdf1.parse(dataColeta);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
				fuelPriceHistory = fuelPriceHistoryRepository.findByDistributorAndDate(distributor.getId(), collectionDateFormat);
        		if (fuelPriceHistory == null) {
        			fuelPriceHistory =  new FuelPriceHistory();
    				fuelPriceHistory.setDistributor(distributor);
    			    fuelPriceHistory.setCollectionDate(collectionDateFormat);
        		} else  {
        			fuelPriceHistoryItem =  fuelPriceHistoryItemRepository.getFuelPriceHistoryItemByProductAndFuelPriceHistory(fuelPriceHistory.getId(), product.getId() );
        		}
        		
        		//FuelPriceHistoryItem
        		fuelPriceHistoryItem = new FuelPriceHistoryItem();
				fuelPriceHistoryItem.setProduct(product);										
				fuelPriceHistoryItem.setSalePrice(new Double(valorVenda));
				fuelPriceHistoryItem.setPurchaseAmount(new Double(valorCompra));
				fuelPriceHistoryItem.setFuelPriceHistory(fuelPriceHistory);
				fuelPriceHistory.addItem(fuelPriceHistoryItem);
				
				fuelPriceHistory = fuelPriceHistoryRepository.save(fuelPriceHistory);

        }	
    
}
