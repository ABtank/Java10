package entity;

import javax.persistence.*;

@Entity
@Table(name = "film")
public class Film {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  @Column(name = "name", nullable = false )
  private String name;
  @Column(name = "length", nullable = false )
  private long length;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getLength() {
    return length;
  }

  public void setLength(long length) {
    this.length = length;
  }


  public Film() {
  }

  public Film(String name, long length) {
    this.name = name;
    this.length = length;
  }

  @Override
  public String toString() {
    return "Film{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", length=" + length +
            '}';
  }
}
