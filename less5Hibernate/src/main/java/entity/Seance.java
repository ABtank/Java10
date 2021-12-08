package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="seance")
public class Seance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  @ManyToOne
  @JoinColumn(name = "film_id", nullable = false)
  private Film film;
  @Column(name="price", nullable = false)
  private double price;
  @Column(name="dt_start", nullable = false)
  private Date dtStart;


  public Seance() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Film getFilm() {
    return film;
  }

  public void setFilm(Film film) {
    this.film = film;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Date getDtStart() {
    return dtStart;
  }

  public void setDtStart(Date dtStart) {
    this.dtStart = dtStart;
  }

  @Override
  public String toString() {
    return "Seance{" +
            "id=" + id +
            ", film=" + film +
            ", price=" + price +
            ", dtStart=" + dtStart +
            '}';
  }
}
