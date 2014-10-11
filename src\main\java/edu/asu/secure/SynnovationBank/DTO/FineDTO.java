/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.asu.secure.SynnovationBank.DTO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author zeel
 */
@Entity
@Table(name = "Fine_Tbl")
@NamedQueries({
    @NamedQuery(name = "FineDTO.findAll", query = "SELECT f FROM FineDTO f"),
    @NamedQuery(name = "FineDTO.findByBookid", query = "SELECT f FROM FineDTO f WHERE f.fineTblPK.bookid = :bookid"),
    @NamedQuery(name = "FineDTO.findByCustid", query = "SELECT f FROM FineDTO f WHERE f.fineTblPK.custid = :custid"),
    @NamedQuery(name = "FineDTO.findByFine", query = "SELECT f FROM FineDTO f WHERE f.fine = :fine")})
public class FineDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FineTblPK fineTblPK;
    @Column(name = "Fine")
    private Integer fine;
    @JoinColumn(name = "Bookid", referencedColumnName = "Bookid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BookDTO bookDTO;
    @JoinColumn(name = "Custid", referencedColumnName = "Custid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CustDTO custDTO;

    public FineDTO() {
    }

    public FineDTO(FineTblPK fineTblPK) {
        this.fineTblPK = fineTblPK;
    }

    public FineDTO(int bookid, int custid) {
        this.fineTblPK = new FineTblPK(bookid, custid);
    }

    public FineTblPK getFineTblPK() {
        return fineTblPK;
    }

    public void setFineTblPK(FineTblPK fineTblPK) {
        this.fineTblPK = fineTblPK;
    }

    public Integer getFine() {
        return fine;
    }

    public void setFine(Integer fine) {
        this.fine = fine;
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
        hash += (fineTblPK != null ? fineTblPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FineDTO)) {
            return false;
        }
        FineDTO other = (FineDTO) object;
        if ((this.fineTblPK == null && other.fineTblPK != null) || (this.fineTblPK != null && !this.fineTblPK.equals(other.fineTblPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zeel.FineTbl[ fineTblPK=" + fineTblPK + " ]";
    }
    
}
