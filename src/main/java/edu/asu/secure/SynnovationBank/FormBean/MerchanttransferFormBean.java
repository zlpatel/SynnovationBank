package edu.asu.secure.SynnovationBank.FormBean;

public class MerchanttransferFormBean {
	private String receiverID;
	private String transferAmount;

	public String getReceiverID() {
		return receiverID;
	}
	
	
	public String getTransferAmount() {
		return transferAmount;
	}

	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID ;
	}
	
	public void setTransferAmount(String transferAmount) {
		this.transferAmount = transferAmount ;
	}


}
