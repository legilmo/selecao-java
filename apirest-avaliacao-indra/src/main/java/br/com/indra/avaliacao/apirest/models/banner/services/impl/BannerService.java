package br.com.indra.avaliacao.apirest.models.banner.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.indra.avaliacao.apirest.models.banner.entity.Banner;
import br.com.indra.avaliacao.apirest.models.banner.repository.BannerRepository;
import br.com.indra.avaliacao.apirest.models.banner.services.IBannerService;


@Service
public class BannerService implements IBannerService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private BannerRepository bannerRepository;
    
    @Autowired
    public void setBannerRepository(BannerRepository bannerRepository ) {
        this.bannerRepository = bannerRepository;
    }
    
	@Override
	public Iterable<Banner> listAllBanners() {
        logger.debug("BannerService::listAllBanners called");
        Iterable<Banner> banners = bannerRepository.findAll();
  
        return banners;
	}

	@Override
	public Banner getBannerById(Integer id) {
        logger.debug("BannerService::getBannerById called");
        Optional<Banner> banner = bannerRepository.findById(id);
        
        if(banner.isPresent()) {
        	Banner r = banner.get();
        	return r;
        } else {
        	return null;
        }
		
	}

	@Override
	public Banner saveBanner(Banner banner) {
        logger.debug("BannerService::saveBanner called");
        return bannerRepository.save(banner);
	}

	@Override
	public void deleteBanner(Integer id) {
        logger.debug("BannerService::deleteBanner called");        
        bannerRepository.deleteById(id);
	}

	


}
