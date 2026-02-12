package org.doit.ik.emp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchEmpRepository {
	Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
	}