package com.stpr.gestionstockprod.Model.ProductType;

import com.stpr.gestionstockprod.Model.Categorie;
import com.stpr.gestionstockprod.Model.ProductLink.PFLinkPSF;
import com.stpr.gestionstockprod.Model.ProductLink.PSFLinkMP;
import com.stpr.gestionstockprod.Model.ProductLink.PSFLinkPSF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class PSF {
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
    @OneToMany(mappedBy = "psf", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<PSFLinkMP> mps= new ArrayList<>();

    @OneToMany(mappedBy = "psf", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<PFLinkPSF> pfs= new ArrayList<>();

    @OneToMany(mappedBy = "psf1", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<PSFLinkPSF> psfs1= new ArrayList<>();

    @OneToMany(mappedBy = "psf2", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<PSFLinkPSF> psfs2= new ArrayList<>();

    public PSF(String designation, double price, int stock, Categorie categorie, int stockSec, String unit){
        super();
        this.designation=designation;
        this.stock= stock;
        this.stockSec=stockSec;
        this.categorie= categorie;
        this.unit= unit;
    }
    public PSF(){
        super();
    }
    public void AddPSFLinkMP(int quantity, PSF psf, MP mp){
        PSFLinkMP x= new PSFLinkMP(quantity, mp, psf);
        this.mps.add(x);
        mp.AddPSFLinkMPinv(x);
    }
    public void AddPFLinkPFSinv(PFLinkPSF x){
        this.pfs.add(x);
    }
    public void setMps(ArrayList<PSFLinkMP> mps){
        this.mps=mps;
        for(int i=0; i<mps.size();i++){
            PSFLinkMP x=mps.get(i);
            x.mp.AddPSFLinkMPinv(mps.get(i));
        }
    }

    public void setPfs(Collection<PFLinkPSF> pfs) {
        this.pfs = pfs;
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

    public Collection<PSFLinkMP> getMps() {
        return mps;
    }

    public Collection<PFLinkPSF> getPfs() {
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

    public void setMps(Collection<PSFLinkMP> mps) {
        this.mps = mps;
    }
}
