package com.stpr.gestionstockprod.Model.ProductLink;

import com.stpr.gestionstockprod.Model.ProductType.MP;
import com.stpr.gestionstockprod.Model.ProductType.PSF;

import javax.persistence.*;

@Entity
@Table(name = "psflinkmp")
public class PSFLinkMP {
    @Id
    @GeneratedValue
    private long id;
    private int quantity;
    @ManyToOne
    public MP mp;
    @ManyToOne
    private PSF psf;
    public PSFLinkMP(int quantity, MP mp, PSF psf){
        super();
        this.psf=psf;
        this.mp=mp;
        this.quantity=quantity;
    }
    public PSFLinkMP(){
        super();
    }
}
