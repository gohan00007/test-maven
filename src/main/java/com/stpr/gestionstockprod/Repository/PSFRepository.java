package com.stpr.gestionstockprod.Repository;

import com.stpr.gestionstockprod.Model.ProductType.PSF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PSFRepository extends JpaRepository<PSF, Long> {
    Page<PSF> findAllByOrderByIdAsc(Pageable pageable);
    Page<PSF> findByDesignationLikeOrderByIdAsc(String mc, Pageable pageable);
    PSF findOneById(Long mc);
}
