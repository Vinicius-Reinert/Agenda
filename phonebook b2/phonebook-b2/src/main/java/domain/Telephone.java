package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Vinicius Sipoli Reinert.
 */
@Entity
@Table(name = "telephone")
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="number", unique = true)
    private String number;

    @ManyToOne
    @JoinColumn(name="carrier_id")
    @JsonIgnore
    private Carrier carrier_id;

    @ManyToOne
    @JoinColumn(name="type_id")
    @JsonIgnore
    private TelephoneType type_id;

    @ManyToOne
    @JoinColumn(name="person_id")
    @JsonIgnore
    private Person person_id;

    public Carrier getCarrier_id() {
        return carrier_id;
    }

    public void setCarrier_id(Carrier carrier_id) {
        this.carrier_id = carrier_id;
    }

    public TelephoneType getType_id() {
        return type_id;
    }

    public void setType_id(TelephoneType type_id) {
        this.type_id = type_id;
    }

    public Person getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Person person_id) {
        this.person_id = person_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
