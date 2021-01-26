package ch8.entities;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Singer_Audit")

public class SingerAudit implements Serializable {

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

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private Date lastModifiedDate;

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

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Optional<String> getCreatedBy() {
        return Optional.of(createdBy);
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Optional<Date> getCreatedDate() {
        return Optional.of(createdDate);
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    public Optional<String> getLastModifiedBy() {
        return Optional.of(lastModifiedBy);
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    public Optional<Date> getLastModifiedDate() {
        return Optional.of(lastModifiedDate);
    }

    @Override
    public String toString() {
        return "Singer - id: " + id +
                ", First name: " + firstName +
                ", Last name: " + lastName +
                ", Birthday: " + birthDate +
                ", Created by: " + createdBy +
                ", Created date: " + createdDate +
                ", Modified by: " + lastModifiedBy +
                ", Modified date: " + lastModifiedDate;
    }
}
