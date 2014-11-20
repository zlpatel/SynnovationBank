package edu.asu.secure.SynnovationBank.FormBean;

public class TransferFormBean {

	private String receiverID;
	private String transferAmount;
	private String roleId;

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


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


}
