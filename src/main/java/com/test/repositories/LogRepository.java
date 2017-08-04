package com.test.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entities.LogEntity;

/**
 * LogRepository
 * Created on 14/02/2017 16:08
 *
 * @author Florian RUYNAT <florian.ruynat@ext.ombudsman.europa.eu>
 */
public interface LogRepository extends JpaRepository<LogEntity, Long> {
}
