package com.xsis.batch197.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xsis.batch197.model.XSertifikasiModel;

@Repository
public interface XSertifikasiRepo extends JpaRepository<XSertifikasiModel, Long>{
	public List<XSertifikasiModel> findByBiodataId(Long biodataId);
	// yang aktif
	@Query(value="SELECT x FROM XSertifikasiModel x WHERE x.isDelete=0")
	public List<XSertifikasiModel> findAllActive();
	
	// untuk mencari yang tidak aktif, atau sudah terhapus
	@Query(value="SELECT x FROM XSertifikasiModel x WHERE x.isDelete=1")
	public List<XSertifikasiModel> findAllNonActive();
}
