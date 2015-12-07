package com.timaar.tiimspot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.timaar.tiimspot.domain.enumeration.Geslacht;

/**
 * A Persoon.
 */
@Entity
@Table(name = "persoon")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "persoon")
public class Persoon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1)
    @Column(name = "voornaam", nullable = false)
    private String voornaam;

    @NotNull
    @Column(name = "naam", nullable = false)
    private String naam;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "geslacht", nullable = false)
    private Geslacht geslacht;

    @NotNull
    @Column(name = "geboorte_datum", nullable = false)
    private ZonedDateTime geboorteDatum;

    @OneToOne    private Adres adres;

    @OneToOne    private Contactinfo contactinfo;

    @OneToMany(mappedBy = "persoon")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(mappedBy = "ouders")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ouder> ouders = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }

    public ZonedDateTime getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(ZonedDateTime geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres Adres) {
        this.adres = Adres;
    }

    public Contactinfo getContactinfo() {
        return contactinfo;
    }

    public void setContactinfo(Contactinfo Contactinfo) {
        this.contactinfo = Contactinfo;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> Comments) {
        this.comments = Comments;
    }

    public Set<Ouder> getOuders() {
        return ouders;
    }

    public void setOuders(Set<Ouder> Ouders) {
        this.ouders = Ouders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Persoon persoon = (Persoon) o;
        return Objects.equals(id, persoon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Persoon{" +
            "id=" + id +
            ", voornaam='" + voornaam + "'" +
            ", naam='" + naam + "'" +
            ", geslacht='" + geslacht + "'" +
            ", geboorteDatum='" + geboorteDatum + "'" +
            '}';
    }
}