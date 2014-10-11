/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.asu.secure.SynnovationBank.DTO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Cust_Dtls_Tbl")
@NamedQueries({
    @NamedQuery(name = "CustDTO.findAll", query = "SELECT c FROM CustDTO c"),
    @NamedQuery(name = "CustDTO.findByCustid", query = "SELECT c FROM CustDTO c WHERE c.custid = :custid"),
    @NamedQuery(name = "CustDTO.findByCustname", query = "SELECT c FROM CustDTO c WHERE c.custname = :custname"),
    @NamedQuery(name = "CustDTO.findByAddress", query = "SELECT c FROM CustDTO c WHERE c.address = :address"),
    @NamedQuery(name = "CustDTO.findByRegDate", query = "SELECT c FROM CustDTO c WHERE c.regDate = :regDate"),
    @NamedQuery(name = "CustDTO.findByPhoneNo", query = "SELECT c FROM CustDTO c WHERE c.phoneNo = :phoneNo"),
    @NamedQuery(name = "CustDTO.findByDateOfBirth", query = "SELECT c FROM CustDTO c WHERE c.dateOfBirth = :dateOfBirth")})
public class CustDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Custid")
    private Integer custid;
    @Basic(optional = false)
    @Column(name = "custname")
    private String custname;
    @Basic(optional = false)
    @Column(name = "Address")
    private String address;
    @Basic(optional = false)
    @Column(name = "RegDate")
    @Temporal(TemporalType.DATE)
    private Date regDate;
    @Column(name = "PhoneNo")
    private String phoneNo;
    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @JoinColumn(name = "Username", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private LoginDTO username;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custDtlsTbl")
//    private List<CheckOutDTO> checkOutTblList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custDtlsTbl")
//    private List<FineDTO> fineTblList;

    public CustDTO() {
    }

    public CustDTO(Integer custid) {
        this.custid = custid;
    }

    public CustDTO(Integer custid, String custname, String address, Date regDate) {
        this.custid = custid;
        this.custname = custname;
        this.address = address;
        this.regDate = regDate;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
    	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        // System.out.println("Format 2:   " + dateFormatter.format(dateOfBirth));
     	dateFormatter.format(regDate);
        this.regDate = regDate;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
    	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        // System.out.println("Format 2:   " + dateFormatter.format(dateOfBirth));
     	dateFormatter.format(dateOfBirth);
        this.dateOfBirth = dateOfBirth;
    }

    public LoginDTO getUsername() {
        return username;
    }

    public void setUsername(LoginDTO username) {
        this.username = username;
    }

//    public List<CheckOutDTO> getCheckOutTblList() {
//        return checkOutTblList;
//    }
//
//    public void setCheckOutTblList(List<CheckOutDTO> checkOutTblList) {
//        this.checkOutTblList = checkOutTblList;
//    }
//
//    public List<FineDTO> getFineTblList() {
//        return fineTblList;
//    }
//
//    public void setFineTblList(List<FineDTO> fineTblList) {
//        this.fineTblList = fineTblList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custid != null ? custid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustDTO)) {
            return false;
        }
        CustDTO other = (CustDTO) object;
        if ((this.custid == null && other.custid != null) || (this.custid != null && !this.custid.equals(other.custid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zeel.CustDtlsTbl[ custid=" + custid + " ]";
    }
    
}
