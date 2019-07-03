package com.xsis.batch197.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xsis.batch197.model.XOrganisasiModel;

@Repository
public interface XOrganisasiRepo extends JpaRepository<XOrganisasiModel, Long> {
	public List<XOrganisasiModel> findByBiodataId(Long biodataId);
	// yang aktif
	@Query(value="SELECT x FROM XOrganisasiModel x WHERE x.isDelete=0")
	public List<XOrganisasiModel> findAllActive();
	
	// untuk mencari yang tidak aktif, atau sudah terhapus
	@Query(value="SELECT x FROM XOrganisasiModel x WHERE x.isDelete=1")
	public List<XOrganisasiModel> findAllNonActive();
}
