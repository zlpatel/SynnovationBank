/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.asu.secure.SynnovationBank.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author zeel
 */
@Entity
@Table(name = "LOGIN_TBL")
@NamedQueries({
    @NamedQuery(name = "LoginDTO.findAll", query = "SELECT l FROM LoginDTO l"),
    @NamedQuery(name = "LoginDTO.findByUsername", query = "SELECT l FROM LoginDTO l WHERE l.username = :username"),
    @NamedQuery(name = "LoginDTO.findByPassword", query = "SELECT l FROM LoginDTO l WHERE l.password = :password"),
    @NamedQuery(name = "LoginDTO.findByStatus", query = "SELECT l FROM LoginDTO l WHERE l.status = :status"),
    @NamedQuery(name = "LoginDTO.findByRcreDate", query = "SELECT l FROM LoginDTO l WHERE l.rcreDate = :rcreDate")})
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @Column(name = "Status")
    private String status;
    @Basic(optional = false)
    @Column(name = "RCRE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rcreDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private List<CustDTO> custDtlsTblList;

    public LoginDTO() {
    }

    public LoginDTO(String username) {
        this.username = username;
    }

    public LoginDTO(String username, String password, String status, Date rcreDate) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.rcreDate = rcreDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRcreDate() {
        return rcreDate;
    }

    public void setRcreDate(Date rcreDate) {
        this.rcreDate = rcreDate;
    }

    public List<CustDTO> getCustDtlsTblList() {
        return custDtlsTblList;
    }

    public void setCustDtlsTblList(List<CustDTO> custDtlsTblList) {
        this.custDtlsTblList = custDtlsTblList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginDTO)) {
            return false;
        }
        LoginDTO other = (LoginDTO) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zeel.LoginTbl[ username=" + username + " ]";
    }
    
}
