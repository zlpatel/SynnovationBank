package edu.asu.secure.SynnovationBank.Service;

public interface TransferService {

	boolean performTransfer(String senderID, String receiverID, String amount );
}
