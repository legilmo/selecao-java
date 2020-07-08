package br.com.indra.avaliacao.apirest.models.banner.services;

import br.com.indra.avaliacao.apirest.models.banner.entity.Banner;

public interface IBannerService {
    Iterable<Banner> listAllBanners();

    Banner getBannerById(Integer id);

    Banner saveBanner(Banner banner);

    void deleteBanner(Integer id);

}
