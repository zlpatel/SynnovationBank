package edu.asu.secure.SynnovationBank.Service;

import java.util.List;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.FormBean.MerchantNotifFormBean;
import edu.asu.secure.SynnovationBank.FormBean.MerchantPaymentFormBean;

public interface MerchantNotificationService {

public List<MerchantNotifFormBean> notifications(String userName);
public void sendTransactionDeclinedNotification(String userName); 
public boolean submitPayment(MerchantPaymentFormBean merchantPaymentFormBean);
public MerchantPaymentFormBean getPaymentNotifications(Notifications notifObj);
public Notifications getNotificationsById(long notifId);

}
