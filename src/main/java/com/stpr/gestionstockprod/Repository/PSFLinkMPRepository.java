package com.stpr.gestionstockprod.Repository;

import com.stpr.gestionstockprod.Model.ProductLink.PSFLinkMP;
import com.stpr.gestionstockprod.Model.ProductType.PSF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PSFLinkMPRepository extends JpaRepository<PSFLinkMP, Long> {
    Collection<PSFLinkMP> findByPsf(PSF psf);
}
