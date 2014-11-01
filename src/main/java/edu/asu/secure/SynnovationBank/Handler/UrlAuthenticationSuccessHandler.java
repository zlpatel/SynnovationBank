package edu.asu.secure.SynnovationBank.Handler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	protected static Logger logger = Logger.getLogger("service");
 
	@Autowired
    private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
	
	public static final String USERNAME_KEY = "USERNAME";
	
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
        String usernameParameter =
                usernamePasswordAuthenticationFilter.getUsernameParameter();
            String userName = request.getParameter(usernameParameter);

            HttpSession session = request.getSession(false);
            if (session != null) {
                request.getSession().setAttribute(USERNAME_KEY, userName);
            }
    }
 
    protected void handle(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
 
    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(Authentication authentication) {
    	boolean isAdmin = false;
    	boolean isEmployee = false;
        boolean isCustomer = false;
        boolean isMerchant = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_BNK_EMPL")) {
                isEmployee = true;
                break;
            }else if (grantedAuthority.getAuthority().equals("ROLE_CUST")) {
                isCustomer = true;
                break;
            }else if (grantedAuthority.getAuthority().equals("ROLE_MERC")) {
                isMerchant = true;
                break;
            }
        }
 
        if (isAdmin) {
            return "/secure/admin/home";
        } else if (isEmployee) {
            return "/secure/employee/home";
        }else if (isCustomer) {
            return "/secure/customer/home";
        }else if (isMerchant) {
            return "/secure/merchant/home";
        } else {
            throw new IllegalStateException();
        }
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}