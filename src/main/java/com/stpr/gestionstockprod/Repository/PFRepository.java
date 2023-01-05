package com.stpr.gestionstockprod.Repository;

import com.stpr.gestionstockprod.Model.ProductType.MP;
import com.stpr.gestionstockprod.Model.ProductType.PF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PFRepository extends JpaRepository<PF, Long> {
    Page<PF> findAllByOrderByIdAsc(Pageable pageable);
    Page<PF> findByDesignationLikeOrderByIdAsc(String mc, Pageable pageable);

    PF findOneById(Long id);
}
