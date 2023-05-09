package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="book")
public class Book {


    @Column(name = "authors", columnDefinition = "text[]")
    private String authors;
    @Basic
    @Column(name = "pages")
    private Integer pages;
    @Basic
    @Column(name = "publisher")
    private String publisher;
    @Basic
    @Column(name = "publication_date")
    private Date publicationDate;
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_code", referencedColumnName = "code", nullable = false)
    private Item itemByItemCode;

    public Book(String authors, Integer pages, String publisher, Date publicationDate)
    {
        this.authors = authors;
        this.pages = pages;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
    }

    public Book(){}
    public String getItemCode() {
        return itemByItemCode.getCode();
    }

    public void setItemCode(String itemCode) {
        this.itemByItemCode.setCode(itemCode);
    }

    public Object getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(itemByItemCode, book.itemByItemCode) && Objects.equals(authors, book.authors) && Objects.equals(pages, book.pages) && Objects.equals(publisher, book.publisher) && Objects.equals(publicationDate, book.publicationDate);
    }



    public Item getItemByItemCode() {
        return itemByItemCode;
    }

    public void setItemByItemCode(Item itemByItemCode) {
        this.itemByItemCode = itemByItemCode;
    }
}
