package iClinic.model.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Role")
@Table(name = "roles")
public class Role implements Serializable  {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
