package domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Vinicius Sipoli Reinert.
 */
@Entity
@Table(name = "telephone_type")
public class TelephoneType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "type_id")
    private List<Telephone> telephones;

    @Column(name="type")
    private Type type;

    public enum Type {
        CELLULAR,
        FIXED,
        MESSAGE
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }



}
