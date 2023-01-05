package com.stpr.gestionstockprod.Model.ProductType;

import com.stpr.gestionstockprod.Model.Categorie;
import com.stpr.gestionstockprod.Model.ProductLink.PFLinkMP;
import com.stpr.gestionstockprod.Model.ProductLink.PSFLinkMP;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class MP{
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String designation;
    private int stock;
    private int stockSec;
    @ManyToOne
    private Categorie categorie;
    private String unit;
    @OneToMany(mappedBy = "mp", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<PSFLinkMP> psfs= new ArrayList<>();

    @OneToMany(mappedBy = "mp", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<PFLinkMP> pfs= new ArrayList<>();

    public MP(String designation, int stock, Categorie categorie, int stockSec, String unit){
        super();
        this.designation=designation;
        this.stock= stock;
        this.stockSec=stockSec;
        this.categorie= categorie;
        this.unit= unit;
    }

    public MP(){
        super();
    }
    public void AddPSFLinkMPinv(PSFLinkMP x){
        this.psfs.add(x);
    }
    public void AddPFLinkMPinv(PFLinkMP x){
        this.pfs.add(x);
    }

    public long getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public int getStock() {
        return stock;
    }

    public int getStockSec() {
        return stockSec;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public String getUnit() {
        return unit;
    }

    public Collection<PSFLinkMP> getPsfs() {
        return psfs;
    }

    public Collection<PFLinkMP> getPfs() {
        return pfs;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStockSec(int stockSec) {
        this.stockSec = stockSec;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPsfs(Collection<PSFLinkMP> psfs) {
        this.psfs = psfs;
    }

    public void setPfs(Collection<PFLinkMP> pfs) {
        this.pfs = pfs;
    }
}
