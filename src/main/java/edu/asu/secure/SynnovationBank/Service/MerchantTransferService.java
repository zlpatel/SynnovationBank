package edu.asu.secure.SynnovationBank.Service;

public interface MerchantTransferService {

	boolean performTransfer(String senderID, String receiverID, String amount );
}
