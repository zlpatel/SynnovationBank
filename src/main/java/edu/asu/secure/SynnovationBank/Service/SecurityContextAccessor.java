package edu.asu.secure.SynnovationBank.Service;

public interface SecurityContextAccessor {

	boolean isCurrentAuthenticationAnonymous();

	String determineDefaultTargetUrl();
}