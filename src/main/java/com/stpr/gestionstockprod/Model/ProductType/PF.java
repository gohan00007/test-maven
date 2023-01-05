package com.stpr.gestionstockprod.Model.ProductType;

import com.stpr.gestionstockprod.Model.Categorie;
import com.stpr.gestionstockprod.Model.ProductLink.PFLinkMP;
import com.stpr.gestionstockprod.Model.ProductLink.PFLinkPSF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class PF {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String designation;
    private double sellPrice;
    private int stock;
    private int stockSec;
    @ManyToOne
    private Categorie categorie;
    private String unit;
    @OneToMany(mappedBy = "pf", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<PFLinkPSF> psfs= new ArrayList<>();

    @OneToMany(mappedBy = "pf", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<PFLinkMP> mps= new ArrayList<>();


    public PF(String designation, double sellPrice, int stock, Categorie categorie, int stockSec, int prodQuanMin, String unit) {
        super();
        this.designation=designation;
        this.sellPrice=sellPrice;
        this.stock= stock;
        this.stockSec=stockSec;
        this.categorie= categorie;
        this.unit= unit;
    }
    public PF(){
        super();
    }

    public void AddPFLinkPSF(int quantity, PSF psf, PF pf){
        PFLinkPSF x= new PFLinkPSF(quantity, psf, pf);
        this.psfs.add(x);
        psf.AddPFLinkPFSinv(x);
    }
    public void AddPFLinkMP(int quantity, MP mp, PF pf){
        PFLinkMP x= new PFLinkMP(quantity, pf, mp);
        this.mps.add(x);
        mp.AddPFLinkMPinv(x);
    }

    public void setPsfs(ArrayList<PFLinkPSF> psfs) {
        this.psfs = psfs;
        for(int i=0; i<psfs.size();i++){
            PFLinkPSF x=psfs.get(i);
            x.psf.AddPFLinkPFSinv(psfs.get(i));
        }

    }

    public void setMps(ArrayList<PFLinkMP> mps) {
        this.mps = mps;
        for(int i=0; i<mps.size();i++){
            PFLinkMP x=mps.get(i);
            x.mp.AddPFLinkMPinv(mps.get(i));
        }
    }

    public long getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSellPrice() {
        return sellPrice;
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

    public Collection<PFLinkPSF> getPsfs() {
        return psfs;
    }

    public Collection<PFLinkMP> getMps() {
        return mps;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrice(double sellPrice) {
        this.sellPrice = sellPrice;
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

    public void setPsfs(Collection<PFLinkPSF> psfs) {
        this.psfs = psfs;
    }

    public void setMps(Collection<PFLinkMP> mps) {
        this.mps = mps;
    }
}
