package com.stpr.gestionstockprod.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stpr.gestionstockprod.Model.ProductType.MP;
import com.stpr.gestionstockprod.Model.ProductType.PF;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Categorie {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 50)
    private String code;
    @Column(length = 50)
    private String libelle;

    @OneToMany(mappedBy = "categorie", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<MP> mps = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "categorie", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<PF> pfs= new java.util.ArrayList<>();

    @OneToMany(mappedBy = "categorie", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<PF> psfs= new java.util.ArrayList<>();

    public void setMPs(Collection<MP> mps) {
        this.mps = mps;
    }


    public Categorie(String code, String libelle) {
        super();
        this.code = code;
        this.libelle = libelle;
    }
    @Override
    public String toString() {
        return "Categorie [id=" + id + ", code=" + code + ", libelle=" +

                libelle + "]";
    }
    public Categorie() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    @JsonIgnore
    public Collection<MP> getMps() {
        return mps;
    }
    @JsonIgnore
    public Collection<PF> getPfs() {
        return pfs;
    }
    @JsonIgnore
    public Collection<PF> getPsfs() {
        return psfs;
    }

    public void setMps(Collection<MP> mps) {
        this.mps = mps;
    }

    public void setPfs(Collection<PF> pfs) {
        this.pfs = pfs;
    }

    public void setPsfs(Collection<PF> psfs) {
        this.psfs = psfs;
    }
}


