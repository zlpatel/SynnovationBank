/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.asu.secure.SynnovationBank.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author zeel
 */
@Embeddable
public class FineTblPK implements Serializable {
    /**
	 * 
	 */
	
	@Basic(optional = false)
    @Column(name = "Bookid")
    private int bookid;
    @Basic(optional = false)
    @Column(name = "Custid")
    private int custid;

    public FineTblPK() {
    }

    public FineTblPK(int bookid, int custid) {
        this.bookid = bookid;
        this.custid = custid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) bookid;
        hash += (int) custid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FineTblPK)) {
            return false;
        }
        FineTblPK other = (FineTblPK) object;
        if (this.bookid != other.bookid) {
            return false;
        }
        if (this.custid != other.custid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zeel.FineTblPK[ bookid=" + bookid + ", custid=" + custid + " ]";
    }
    
}
