package com.stpr.gestionstockprod.Model.ProductLink;

import com.stpr.gestionstockprod.Model.ProductType.MP;
import com.stpr.gestionstockprod.Model.ProductType.PF;
import com.stpr.gestionstockprod.Model.ProductType.PSF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PFLinkMP {
    @Id
    @GeneratedValue
    private long id;
    private int quantity;
    @ManyToOne
    public MP mp;
    @ManyToOne
    private PF pf;
    public PFLinkMP(int quantity, PF pf, MP mp){
        super();
        this.pf=pf;
        this.mp=mp;
        this.quantity=quantity;
    }
    public PFLinkMP(){
        super();
    }
}
