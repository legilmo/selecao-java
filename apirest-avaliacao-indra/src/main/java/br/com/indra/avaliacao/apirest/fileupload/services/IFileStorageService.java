package br.com.indra.avaliacao.apirest.fileupload.services;

import java.io.InputStream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface IFileStorageService {

    public String storeFile(MultipartFile file);
    public Resource loadFileAsResource(String fileName);
    public void populateAllFuelPriceHistoryByCSVFile(InputStream bodyCSVFile);
    
}
