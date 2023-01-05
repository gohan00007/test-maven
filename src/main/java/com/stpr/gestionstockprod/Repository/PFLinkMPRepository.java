package com.stpr.gestionstockprod.Repository;

import com.stpr.gestionstockprod.Model.ProductLink.PFLinkMP;
import com.stpr.gestionstockprod.Model.ProductLink.PSFLinkMP;
import com.stpr.gestionstockprod.Model.ProductType.PF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PFLinkMPRepository extends JpaRepository<PFLinkMP, Long> {
    Page<PFLinkMP> findByIdLike(String mc, Pageable pageable);

    Collection<PSFLinkMP> findByPf(PF oneById);
}
