package entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private transient LocalDate released;
    @Version
    private int version;
    private int stock;
    private String title;

    public Film() {
        
    }

    public Film(String title, int stock, LocalDate released, Genre genre) {
        // TODO Auto-generated constructor stub
        setTitle(title);
        setStock(stock);
        setReleased(released);
        setGenre(genre);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Film ? ((Film)obj).title.equals(title) : false;
    }

    public Genre getGenre() {
        // TODO Auto-generated method stub
        return genre;
    }

    public Long getId() {
        // TODO Auto-generated method stub
        return id;
    }



    public int getStock() {
        // TODO Auto-generated method stub
        return stock;
    }

    public String getTitle() {
        // TODO Auto-generated method stub
        return title;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return title.hashCode();
    }

    private void setGenre(Genre genre) {
        // TODO Auto-generated method stub
        this.genre = genre;

    }

    public void setId(Long id) {
        this.id = id;

    }

    public void setStock(int stock) {
        if (stock < 0) {
            IllegalArgumentException e = new IllegalArgumentException("stock can't be negative");
            throw e;
        }
        this.stock = stock;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }



/*	public LocalDate getReleased() {
		try {
			java.sql.Date date = new java.sql.Date(released.getTime());
			return date.toLocalDate();
		} catch (Exception e) {
			return LocalDate.now();
		}
	}
	public void setReleased(LocalDate released) {
		ZonedDateTime zdt = released.atStartOfDay(ZoneId.of("Z"));
		Instant instant = zdt.toInstant();
		this.released = Date.from(instant);
	}*/

}
