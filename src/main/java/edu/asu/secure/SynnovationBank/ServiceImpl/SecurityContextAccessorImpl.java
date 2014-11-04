package edu.asu.secure.SynnovationBank.ServiceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.secure.SynnovationBank.Handler.UrlAuthenticationSuccessHandler;
import edu.asu.secure.SynnovationBank.Service.SecurityContextAccessor;

@Service
@Transactional
public final class SecurityContextAccessorImpl
implements SecurityContextAccessor {

@Autowired
private AuthenticationTrustResolver authenticationTrustResolver;

@Override
public boolean isCurrentAuthenticationAnonymous() {
final Authentication authentication =
  SecurityContextHolder.getContext().getAuthentication();
return authenticationTrustResolver.isAnonymous(authentication);
}

@Override
public String determineDefaultTargetUrl() {
	final Authentication authentication =
			  SecurityContextHolder.getContext().getAuthentication();
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
        return "redirect:../admin/home";
    } else if (isEmployee) {
        return "redirect:../employee/home";
    }else if (isCustomer) {
        return "redirect:../customer/home";
    }else if (isMerchant) {
        return "redirect:../merchant/home";
    } else {
        throw new IllegalStateException();
    }
}
}