package edu.asu.secure.SynnovationBank.DTO;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="Transaction_Type")
public class TransactionType {

	private long transactionTypeId;
	private String transactionName;
	private String description;
	
	private Set<Role> role;
	private Set<TransactionDetails> transactionDetails;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TRANTYPE_SEQ_GEN")
	@TableGenerator(name="TRANTYPE_SEQ_GEN", table="transactiontype_pk_table", pkColumnName="seq_key", pkColumnValue="seq_key", allocationSize=1)
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

	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name="Permission",
		joinColumns={@JoinColumn(name="transaction_type_id", referencedColumnName="transaction_type_id")},
		inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="role_id")})
	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="transaction_type_id", referencedColumnName="transaction_type_id")
	public Set<TransactionDetails> getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(Set<TransactionDetails> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

}
