package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "singer")
@NamedQueries({
        @NamedQuery(name = Singer.SINGER_ALL, query = "select s from Singer s"),
        @NamedQuery(name = Singer.COUNT_ALL, query = "select count(s) from Singer s"),
})
public class Singer implements Serializable {

    public static final String SINGER_ALL = "Singer.findAll";
    public static final String COUNT_ALL= "Singer.countAll";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @OneToMany(mappedBy = "singer", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Album> album = new HashSet<>();

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Singer - " +
                "id: " + id +
                ", First name: " + firstName +
                ", Last name:  " + lastName +
                ", birthDate: " + birthDate;
    }
}
