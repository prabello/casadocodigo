package org.casadocodigo.loja.infra;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

@RequestScoped
public class FileSaver {

	@Inject
	// private HttpServletRequest request;
	private AmazonS3Client s3;

	private static final String CONTENT_DISPOSITION = "content-disposition";

	private static final String FILENAME_KEY = "filename=";

	public String write(String baseFolder, Part multipartFile) {
		// String serverPath = request.getServletContext().getRealPath("/" +
		// baseFolder);

		String fileName = extractFilename(multipartFile.getHeader(CONTENT_DISPOSITION));

		// String path = serverPath + "/" + filename;

		try {
			s3.putObject("casadocodigo/" + baseFolder, fileName, multipartFile.getInputStream(), new ObjectMetadata());
			return "http://localhost:9444/s3/casadocodigo/"+baseFolder+"_"+fileName+"?noAuth=true";
			// multipartFile.write(path);
		} catch (AmazonClientException | IOException e) {
			throw new RuntimeException(e);
		}

		// return baseFolder + "/" + filename;
	}

	private String extractFilename(String contentDisposition) {
		if (contentDisposition == null) {
			return null;
		}

		int startIndex = contentDisposition.indexOf(FILENAME_KEY);

		if (startIndex == -1) {
			return null;
		}

		String fileName = contentDisposition.substring(startIndex + FILENAME_KEY.length());
		if (fileName.startsWith("\"")) {
			int endIndex = fileName.indexOf("\"", 1);
			if (endIndex != -1) {
				return fileName.substring(1, endIndex);
			}
		} else {
			int endIndex = fileName.indexOf(";");
			if (endIndex != -1) {
				return fileName.substring(0, endIndex);
			}
		}
		return fileName;
	}
}
