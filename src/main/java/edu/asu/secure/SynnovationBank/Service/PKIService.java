package edu.asu.secure.SynnovationBank.Service;

import org.springframework.web.multipart.MultipartFile;

public interface PKIService {

	public boolean verifyCertificate(MultipartFile file, String username);

}