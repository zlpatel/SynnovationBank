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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Book_Dtls_Tbl")
@NamedQueries({
    @NamedQuery(name = "BookDTO.findAll", query = "SELECT b FROM BookDTO b"),
    @NamedQuery(name = "BookDTO.findByBookid", query = "SELECT b FROM BookDTO b WHERE b.bookid = :bookid"),
    @NamedQuery(name = "BookDTO.findByCoverpage", query = "SELECT b FROM BookDTO b WHERE b.coverpage = :coverpage"),
    @NamedQuery(name = "BookDTO.findByAuthor", query = "SELECT b FROM BookDTO b WHERE b.author = :author"),
    @NamedQuery(name = "BookDTO.findByBookName", query = "SELECT b FROM BookDTO b WHERE b.bookName = :bookName"),
    @NamedQuery(name = "BookDTO.findByIsbn", query = "SELECT b FROM BookDTO b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "BookDTO.findByPurchaseDate", query = "SELECT b FROM BookDTO b WHERE b.purchaseDate = :purchaseDate"),
    @NamedQuery(name = "BookDTO.findBySubject", query = "SELECT b FROM BookDTO b WHERE b.subject = :subject"),
    @NamedQuery(name = "BookDTO.findByBookStatus", query = "SELECT b FROM BookDTO b WHERE b.bookStatus = :bookStatus")})
public class BookDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Bookid")
    private Integer bookid;
    @Column(name = "coverpage")
    private String coverpage;
    @Basic(optional = false)
    @Column(name = "Author")
    private String author;
    @Basic(optional = false)
    @Column(name = "BookName")
    private String bookName;
    @Column(name = "ISBN")
    private String isbn;
    @Basic(optional = false)
    @Column(name = "PurchaseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;
    @Column(name = "Subject")
    private String subject;
    @Column(name = "BookStatus")
    private String bookStatus;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookDtlsTbl")
//    private List<CheckOutDTO> checkOutTblList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookDtlsTbl")
//    private List<FineDTO> fineTblList;

    public BookDTO() {
    }

    public BookDTO(Integer bookid) {
        this.bookid = bookid;
    }

    public BookDTO(Integer bookid, String author, String bookName, Date purchaseDate) {
        this.bookid = bookid;
        this.author = author;
        this.bookName = bookName;
        this.purchaseDate = purchaseDate;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getCoverpage() {
        return coverpage;
    }

    public void setCoverpage(String coverpage) {
        this.coverpage = coverpage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
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
        hash += (bookid != null ? bookid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookDTO)) {
            return false;
        }
        BookDTO other = (BookDTO) object;
        if ((this.bookid == null && other.bookid != null) || (this.bookid != null && !this.bookid.equals(other.bookid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zeel.BookDtlsTbl[ bookid=" + bookid + " ]";
    }
    
}
