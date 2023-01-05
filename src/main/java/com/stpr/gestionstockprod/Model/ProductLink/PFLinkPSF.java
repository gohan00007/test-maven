package com.stpr.gestionstockprod.Model.ProductLink;

import com.stpr.gestionstockprod.Model.ProductType.MP;
import com.stpr.gestionstockprod.Model.ProductType.PF;
import com.stpr.gestionstockprod.Model.ProductType.PSF;

import javax.persistence.*;

@Entity
public class PFLinkPSF {
    @Id
    @GeneratedValue
    private long id;
    private int quantity;
    @ManyToOne
    public PSF psf;
    @ManyToOne
    private PF pf;
    public PFLinkPSF(int quantity, PSF psf, PF pf){
        super();
        this.pf=pf;
        this.psf=psf;
        this.quantity=quantity;
    }
    public PFLinkPSF(){
        super();
    }
}
