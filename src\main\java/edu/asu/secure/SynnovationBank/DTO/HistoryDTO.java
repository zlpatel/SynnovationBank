/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.asu.secure.SynnovationBank.DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author zeel
 */
@Entity
@Table(name = "History_Tbl")
@NamedQueries({
    @NamedQuery(name = "HistoryDTO.findAll", query = "SELECT h FROM HistoryDTO h"),
    @NamedQuery(name = "HistoryDTO.findByTranid", query = "SELECT h FROM HistoryDTO h WHERE h.tranid = :tranid"),
    @NamedQuery(name = "HistoryDTO.findByBookid", query = "SELECT h FROM HistoryDTO h WHERE h.bookid = :bookid"),
    @NamedQuery(name = "HistoryDTO.findByCustid", query = "SELECT h FROM HistoryDTO h WHERE h.custid = :custid"),
    @NamedQuery(name = "HistoryDTO.findByChkoutDate", query = "SELECT h FROM HistoryDTO h WHERE h.chkoutDate = :chkoutDate"),
    @NamedQuery(name = "HistoryDTO.findByReturnedDate", query = "SELECT h FROM HistoryDTO h WHERE h.returnedDate = :returnedDate"),
    @NamedQuery(name = "HistoryDTO.findByFine", query = "SELECT h FROM HistoryDTO h WHERE h.fine = :fine")})
public class HistoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Tranid")
    private Integer tranid;
    @Basic(optional = false)
    @Column(name = "Bookid")
    private Integer bookid;
    @Basic(optional = false)
    @Column(name = "Custid")
    private Integer custid;
    @Basic(optional = false)
    @Column(name = "chkoutDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chkoutDate;
    @Basic(optional = false)
    @Column(name = "ReturnedDate")
    @Temporal(TemporalType.DATE)
    private Date returnedDate;
    @Column(name = "Fine")
    private Integer fine;

    public HistoryDTO() {
    }

    public HistoryDTO(Integer tranid) {
        this.tranid = tranid;
    }

    public HistoryDTO(Integer tranid, Integer bookid, Integer custid, Date chkoutDate, Date returnedDate) {
        this.tranid = tranid;
        this.bookid = bookid;
        this.custid = custid;
        this.chkoutDate = chkoutDate;
        this.returnedDate = returnedDate;
    }

    public Integer getTranid() {
        return tranid;
    }

    public void setTranid(Integer tranid) {
        this.tranid = tranid;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    public Date getChkoutDate() {
        return chkoutDate;
    }

    public void setChkoutDate(Date chkoutDate) {
        this.chkoutDate = chkoutDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Integer getFine() {
        return fine;
    }

    public void setFine(Integer fine) {
        this.fine = fine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tranid != null ? tranid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoryDTO)) {
            return false;
        }
        HistoryDTO other = (HistoryDTO) object;
        if ((this.tranid == null && other.tranid != null) || (this.tranid != null && !this.tranid.equals(other.tranid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zeel.HistoryTbl[ tranid=" + tranid + " ]";
    }
    
}
