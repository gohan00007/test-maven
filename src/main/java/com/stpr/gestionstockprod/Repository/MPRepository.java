package com.stpr.gestionstockprod.Repository;

import com.stpr.gestionstockprod.Model.ProductType.MP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MPRepository extends JpaRepository<MP, Long> {
    Page<MP> findAllByOrderByIdAsc(Pageable pageable);
    Page<MP> findByDesignationLikeOrderByIdAsc(String mc, Pageable pageable);

    MP findOneById(long id);
}
