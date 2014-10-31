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
@Table(name="Reported_Issues")
public class ReportedIssues {

	private long issueId;
	private String description;
	private String resolvedFlag;
	
	private Person person;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="REPISSUES_SEQ_GEN")
	@TableGenerator(name="REPISSUES_SEQ_GEN", table="rep_issues_pk_table", pkColumnName="seq_key", pkColumnValue="seq_key", allocationSize=1)
	@Column(name="issue_id")
	public long getIssueId() {
		return issueId;
	}

	public void setIssueId(long issueId) {
		this.issueId = issueId;
	}

	@Column(name="description", length = 255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="resolved_flag")
	public String getResolvedFlag() {
		return resolvedFlag;
	}

	public void setResolvedFlag(String resolvedFlag) {
		this.resolvedFlag = resolvedFlag;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
