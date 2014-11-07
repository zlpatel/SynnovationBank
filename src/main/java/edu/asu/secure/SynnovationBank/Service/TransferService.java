package edu.asu.secure.SynnovationBank.Service;

public interface TransferService {

	boolean performTransfer(String senderID, String receiverID, String amount );

	boolean performTransfer(int i, String senderID, String receiverID,
			String amount);

	String getReceiverRole(String receiverID);
}
