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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CheckOut_Tbl")
@NamedQueries({
    @NamedQuery(name = "CheckOutDTO.findAll", query = "SELECT c FROM CheckOutDTO c"),
    @NamedQuery(name = "CheckOutDTO.findByBookid", query = "SELECT c FROM CheckOutDTO c WHERE c.checkOutTblPK.bookid = :bookid"),
    @NamedQuery(name = "CheckOutDTO.findByCustid", query = "SELECT c FROM CheckOutDTO c WHERE c.checkOutTblPK.custid = :custid"),
    @NamedQuery(name = "CheckOutDTO.findByChkoutDate", query = "SELECT c FROM CheckOutDTO c WHERE c.chkoutDate = :chkoutDate"),
    @NamedQuery(name = "CheckOutDTO.findByReturnDate", query = "SELECT c FROM CheckOutDTO c WHERE c.returnDate = :returnDate")})
public class CheckOutDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CheckOutTblPK checkOutTblPK;
    @Basic(optional = false)
    @Column(name = "chkoutDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chkoutDate;
    //@Basic(optional = false)
    @Column(name = "ReturnDate")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @JoinColumn(name = "Bookid", referencedColumnName = "Bookid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BookDTO bookDTO;
    @JoinColumn(name = "Custid", referencedColumnName = "Custid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CustDTO custDTO;

    public CheckOutDTO() {
    }

    public CheckOutDTO(CheckOutTblPK checkOutTblPK) {
        this.checkOutTblPK = checkOutTblPK;
    }

    public CheckOutDTO(CheckOutTblPK checkOutTblPK, Date chkoutDate, Date returnDate) {
        this.checkOutTblPK = checkOutTblPK;
        this.chkoutDate = chkoutDate;
        this.returnDate = returnDate;
    }

    public CheckOutDTO(int bookid, int custid) {
        this.checkOutTblPK = new CheckOutTblPK(bookid, custid);
    }

    public CheckOutTblPK getCheckOutTblPK() {
        return checkOutTblPK;
    }

    public void setCheckOutTblPK(CheckOutTblPK checkOutTblPK) {
        this.checkOutTblPK = checkOutTblPK;
    }

    public Date getChkoutDate() {
        return chkoutDate;
    }

    public void setChkoutDate(Date chkoutDate) {
        this.chkoutDate = chkoutDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BookDTO getBookDtlsTbl() {
        return bookDTO;
    }

    public void setBookDtlsTbl(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public CustDTO getCustDtlsTbl() {
        return custDTO;
    }

    public void setCustDtlsTbl(CustDTO custDTO) {
        this.custDTO = custDTO;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (checkOutTblPK != null ? checkOutTblPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CheckOutDTO)) {
            return false;
        }
        CheckOutDTO other = (CheckOutDTO) object;
        if ((this.checkOutTblPK == null && other.checkOutTblPK != null) || (this.checkOutTblPK != null && !this.checkOutTblPK.equals(other.checkOutTblPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zeel.CheckOutTbl[ checkOutTblPK=" + checkOutTblPK + " ]";
    }
    
}
