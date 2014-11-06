package edu.asu.secure.SynnovationBank.Service;

import java.util.List;

import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.FormBean.MerchantNotifFormBean;
import edu.asu.secure.SynnovationBank.FormBean.MerchantPaymentFormBean;

public interface MerchantNotificationService {

public List<MerchantNotifFormBean> notifications(String userName);
public void sendTransactionDeclinedNotification(String userName,long notification_id);
public Notifications getNotificationsById(long notifId);
public float getTransactionAmount(long transaction_id);
public void sendTransactionAcceptedNotification(long notification_id);

}
