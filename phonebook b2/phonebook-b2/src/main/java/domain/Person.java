package domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import util.CustomDateDeserializer;
import util.CustomDateSerializer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Vinicius Sipoli Reinert.
 */
@Entity
@Table(name = "person")
public class Person {

    public static final String ANNIVERSARY_DATE_FORMAT = "dd-MM-yyyy";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="anniversary_date")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date anniversaryDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person_id")
    private List<Telephone> telephones;

    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAnniversaryDate() {
        return anniversaryDate;
    }

    public void setAnniversaryDate(Date anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }

    public Long getId() {
        return id;
    }
}
