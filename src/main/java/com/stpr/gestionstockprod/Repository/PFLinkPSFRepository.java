package com.stpr.gestionstockprod.Repository;

import com.stpr.gestionstockprod.Model.ProductLink.PFLinkMP;
import com.stpr.gestionstockprod.Model.ProductLink.PFLinkPSF;
import com.stpr.gestionstockprod.Model.ProductLink.PSFLinkPSF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PFLinkPSFRepository extends JpaRepository<PFLinkPSF, Long> {
    Collection<PSFLinkPSF> findByPf(Object oneById);
}
