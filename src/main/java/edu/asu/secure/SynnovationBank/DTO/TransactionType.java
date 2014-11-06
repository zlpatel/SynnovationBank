package edu.asu.secure.SynnovationBank.DTO;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Transaction_Type")
public class TransactionType {

	private long transactionTypeId;
	private String transactionName;
	private String description;

	private Set<TransactionDetails> transactionDetails;

	@Id
	@Column(name="transaction_type_id")
	public long getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionTypeId(long transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	@Column(name="transaction_name", length = 255, nullable=false)
	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	@Column(name="description", length = 255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="transaction_type_id", referencedColumnName="transaction_type_id")
	public Set<TransactionDetails> getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(Set<TransactionDetails> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

}
