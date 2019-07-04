package com.xsis.batch197.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.batch197.model.XSumberLokerModel;

@Repository
public interface XSumberLokerRepo extends JpaRepository<XSumberLokerModel, Long> {
	public XSumberLokerModel findByBiodataId(Long biodataId);

	// yang aktif
	@Query(value = "SELECT x FROM XSumberLokerModel x WHERE x.isDelete=0 and x.biodataId=:bid")
	public List<XSumberLokerModel> findAllActive(@Param("bid") Long bid);

	// untuk mencari yang tidak aktif, atau sudah terhapus
	@Query(value = "SELECT x FROM XSumberLokerModel x WHERE x.isDelete=1 and x.biodataId=:bid")
	public List<XSumberLokerModel> findAllNonActive(@Param("bid") Long bid);
}