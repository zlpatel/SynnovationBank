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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="Notifications")
public class Notifications {

	private long notificationId;
	private String empAdminFlag;
	private String resolvedFlag;
	
	private Transactions transaction;
	private Person person;
	private NotificationsType notificationsType;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="NOTF_SEQ_GEN")
	@TableGenerator(name="NOTF_SEQ_GEN", table="notif_pk_table", pkColumnName="seq_key", pkColumnValue="seq_key", allocationSize=1)
	@Column(name="notification_id")
	public long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	@Column(name="emp_admin_flag", nullable = false)
	public String getEmpAdminFlag() {
		return empAdminFlag;
	}

	public void setEmpAdminFlag(String empAdminFlag) {
		this.empAdminFlag = empAdminFlag;
	}

	@Column(name="resolved_flag", nullable = false)
	public String getResolvedFlag() {
		return resolvedFlag;
	}

	public void setResolvedFlag(String resolvedFlag) {
		this.resolvedFlag = resolvedFlag;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="transaction_id", referencedColumnName="transaction_id")
	public Transactions getTransaction() {
		return transaction;
	}

	public void setTransaction(Transactions transaction) {
		this.transaction = transaction;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="assignee_id", referencedColumnName="user_id")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="notification_type_id", referencedColumnName="notification_type_id")
	public NotificationsType getNotificationsType() {
		return notificationsType;
	}

	public void setNotificationsType(NotificationsType notificationsType) {
		this.notificationsType = notificationsType;
	}

}
