package com.stpr.gestionstockprod.Repository;

import com.stpr.gestionstockprod.Model.ProductLink.PSFLinkPSF;
import com.stpr.gestionstockprod.Model.ProductType.PSF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PSFLinkPSFRepository extends JpaRepository<PSFLinkPSF, Long> {
    Collection<PSFLinkPSF> findByPsf1(PSF psf);

    Collection<PSFLinkPSF> findByPsf2(PSF oneById);
}
