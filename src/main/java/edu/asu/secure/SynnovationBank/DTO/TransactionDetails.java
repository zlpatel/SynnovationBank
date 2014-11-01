package edu.asu.secure.SynnovationBank.DTO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="Transaction_Details")
public class TransactionDetails {

	private long sequenceId;
	
	private Transactions transactions;
	private TransactionType transactionType;
	private Account account;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TRANDETAILS_SEQ_GEN")
	@TableGenerator(name="TRANDETAILS_SEQ_GEN", table="transactionDetails_pk_table", pkColumnName="seq_key", pkColumnValue="seq_key", allocationSize=1)
	@Column(name="sequence_id")
	public long getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(long sequenceId) {
		this.sequenceId = sequenceId;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="transaction_id", referencedColumnName="transaction_id")
	public Transactions getTransactions() {
		return transactions;
	}

	public void setTransactions(Transactions transactions) {
		this.transactions = transactions;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="transaction_type_id", referencedColumnName="transaction_type_id")
	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="account_number", referencedColumnName="account_number")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
