package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Documentary {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "item_code")
    private String itemCode;
    @Basic
    @Column(name = "director")
    private String director;
    @Basic
    @Column(name = "length")
    private Integer length;
    @Basic
    @Column(name = "release_date")
    private Date releaseDate;
    @OneToOne
    @JoinColumn(name = "item_code", referencedColumnName = "code ", nullable = false)
    private Item itemByItemCode;

    public Documentary(String director, Integer length, Date releaseDate)
    {
        this.director = director;
        this.length = length;
        this.releaseDate = releaseDate;
    }

    public Documentary(){}
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documentary that = (Documentary) o;
        return Objects.equals(itemCode, that.itemCode) && Objects.equals(director, that.director) && Objects.equals(length, that.length) && Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode, director, length, releaseDate);
    }

    public Item getItemByItemCode() {
        return itemByItemCode;
    }

    public void setItemByItemCode(Item itemByItemCode) {
        this.itemByItemCode = itemByItemCode;
    }
}
