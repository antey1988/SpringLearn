package ch8.boot;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Singer")
@NamedQueries({
        @NamedQuery(name = Singer.FIND_ALL,
                query = "select s from Singer s"),
        @NamedQuery(name = Singer.FIND_SINGER_BY_ID,
                query = "select distinct s from Singer s " +
                        "left join fetch s.albums a " +
                        "left join fetch s.instruments i " +
                        "where s.id = :id"),
        @NamedQuery(name = Singer.FIND_ALL_WITH_ALBUM,
                query = "select distinct s from Singer s " +
                        "left join fetch s.albums a " +
                        "left join fetch s.instruments i")
})

@SqlResultSetMapping(
        name = "singerResult",
        entities = @EntityResult(entityClass = Singer.class)
)

public class Singer implements Serializable {

    public static final String FIND_ALL = "Singer.findAll";
    public static final String FIND_SINGER_BY_ID = "Singer.findById";
    public static final String FIND_ALL_WITH_ALBUM = "Singer.findAllWithAlbum";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
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

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Album> albums = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "SINGER_INSTRUMENT",
            joinColumns = @JoinColumn(name = "SINGER_ID"),
            inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
    private Set<Instrument> instruments = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    public int getVersion() {
        return this.version;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return this.lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
    public Set<Album> getAlbums() {
        return albums;
    }
    public boolean addAlbum(Album album) {
        album.setSinger(this);
        return getAlbums().add(album);
    }
    public void removeAlbum(Album album) {
        getAlbums().remove(album);
    }

    public void setInstruments(Set<Instrument> instruments) {
        this.instruments = instruments;
    }
    public Set<Instrument> getInstruments() {
        return instruments;
    }
    public boolean addInstrument(Instrument instrument) {
        instrument.getSingers().add(this);
        return getInstruments().add(instrument);
    }
    public void removeInstrument(Instrument instrument) {
        getInstruments().remove(instrument);
        instrument.getSingers().remove(this);
    }


    @Override
    public String toString() {
        return "Singer - id: " + id +
                ", First name: " + firstName +
                ", Last name: " + lastName +
                ", Birthday: " + birthDate;
    }
}
