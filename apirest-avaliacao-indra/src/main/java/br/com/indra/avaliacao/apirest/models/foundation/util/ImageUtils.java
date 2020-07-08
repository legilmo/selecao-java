package br.com.indra.avaliacao.apirest.models.foundation.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class ImageUtils {

	public static final String STR_TOKEN_BASE64 = "base64,";


	public static String getBase64ImageExtension(String base64){
		String base64ImageExtension = null;
		String[] parts = base64.split(STR_TOKEN_BASE64);
		if(parts.length == 2) {
			base64ImageExtension = parts[0];
		}
		
		return base64ImageExtension;		
	}
	
	public static String getBase64ImageData(String base64){
		String base64ImageData = null;
		String[] parts = base64.split(STR_TOKEN_BASE64);
		if(parts.length == 2) {
			base64ImageData = parts[1];
		}
		
		return base64ImageData;		
	}
	
	public static void saveBase64Image(String base64, String filename) throws IOException {
		//		UUID.fromString(filename);
		byte[] bytes = Base64.getEncoder().encode(base64.getBytes());
		InputStream in = new ByteArrayInputStream(bytes);
		File tmpDir = new File("/images");
		if (!tmpDir.exists()) {
			tmpDir.mkdir();
		}
		File file = new File(tmpDir, filename);
		FileOutputStream out = new FileOutputStream(file);
		IOUtils.copy(in, out);
		IOUtils.closeQuietly(out);
	}


	public static String getBase64Image(String filename, String base64ImageExtension) {
		String imageContent = null;
		String base64Image = null;

		File tmpDir = new File("/images");
		if (tmpDir.exists() && filename != null && base64ImageExtension != null) {
			File file = new File(tmpDir, filename);
			if (file.exists()) {
				FileInputStream fis = null;

				byte[] bArray = new byte[(int) file.length()];
				try{
					fis = new FileInputStream(file);
					fis.read(bArray);
					fis.close();        

				}catch(IOException ioExp){
					ioExp.printStackTrace();
				}
				byte[] decodedBytes = Base64.getDecoder().decode(bArray);
				base64Image = new String(decodedBytes);
				imageContent = base64ImageExtension + STR_TOKEN_BASE64 + base64Image;
			}
		}
		
		return imageContent;
	}
}
