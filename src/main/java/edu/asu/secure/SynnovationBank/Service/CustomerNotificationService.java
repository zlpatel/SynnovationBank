package edu.asu.secure.SynnovationBank.Service;

import java.util.List;

import edu.asu.secure.SynnovationBank.FormBean.CustNotifFormBean;

public interface CustomerNotificationService {

public List<CustNotifFormBean> notifications(String userName);

}
