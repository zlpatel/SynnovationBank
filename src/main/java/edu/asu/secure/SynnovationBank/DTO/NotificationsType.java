package edu.asu.secure.SynnovationBank.DTO;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="Notifications_Type")
public class NotificationsType {

	private long notificationTypeId;
	private String notificationType;
	private String description;
	
	private Set<Notifications> notifications;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="NOTF_TYPE_SEQ_GEN")
	@TableGenerator(name="NOTF_TYPE_SEQ_GEN", table="notiftype_pk_table", pkColumnName="seq_key", pkColumnValue="seq_key", allocationSize=1)
	@Column(name="notification_type_id")
	public long getNotificationTypeId() {
		return notificationTypeId;
	}

	public void setNotificationTypeId(long notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	@Column(name="notification_type")
	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="notification_type_id", referencedColumnName="notification_type_id")
	public Set<Notifications> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notifications> notifications) {
		this.notifications = notifications;
	}	
	
	
}
