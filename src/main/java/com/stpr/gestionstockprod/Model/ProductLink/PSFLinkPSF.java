package com.stpr.gestionstockprod.Model.ProductLink;

import com.stpr.gestionstockprod.Model.ProductType.MP;
import com.stpr.gestionstockprod.Model.ProductType.PF;
import com.stpr.gestionstockprod.Model.ProductType.PSF;

import javax.persistence.*;

@Entity
@Table(name = "psflinkpsf")
public class PSFLinkPSF {
    @Id
    @GeneratedValue
    private long id;
    private int quantity;
    @ManyToOne
    public PSF psf1;
    @ManyToOne
    public PSF psf2;
    public PSFLinkPSF(int quantity, PSF psf1, PSF psf2){
        super();
        this.psf1=psf1;
        this.psf2=psf2;
        this.quantity=quantity;
    }
    public PSFLinkPSF(){
        super();
    }
}
