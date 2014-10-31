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
@Table(name="Role")
public class Role {

	private long roleId;
	private String roleName;
	private Set<Person> person;
	private Set<TransactionType> transactionType;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="ROLE_SEQ_GEN")
	@TableGenerator(name="ROLE_SEQ_GEN", table="role_pk_table", pkColumnName="seq_key", pkColumnValue="seq_key", allocationSize=1)
	@Column(name="role_id")
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Column(name="role_name", length = 255, nullable=false)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="role_id", referencedColumnName="role_id")
	public Set<Person> getPerson() {
		return person;
	}

	public void setPerson(Set<Person> person) {
		this.person = person;
	}

	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name="Permission",
		joinColumns={@JoinColumn(name="role_id", referencedColumnName="role_id")},
		inverseJoinColumns={@JoinColumn(name="transaction_type_id", referencedColumnName="transaction_type_id")})
	public Set<TransactionType> getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Set<TransactionType> transactionType) {
		this.transactionType = transactionType;
	}
	
	
}
