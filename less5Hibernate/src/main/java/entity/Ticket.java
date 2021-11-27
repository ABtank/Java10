package entity;


import javax.persistence.*;
@Entity
@Table(name="ticket")
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  @ManyToOne
  @JoinColumn(name = "seance_id", nullable = false)
  private Seance seance;

  public Ticket() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Seance getSeance() {
    return seance;
  }

  public void setSeance(Seance seance) {
    this.seance = seance;
  }


  @Override
  public String toString() {
    return "Ticket{" +
            "id=" + id +
            ", seance=" + seance +
            '}';
  }
}
