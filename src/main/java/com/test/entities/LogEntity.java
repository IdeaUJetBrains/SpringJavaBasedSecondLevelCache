package com.test.entities;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Created by ruynatf on 29/06/2017.
 */
@Entity
@Table(name = "EO_LOGS")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LogEntity {
    @Id
    @Column(name = "LOGS_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long techKey;

    @Column(name = "H_CONTENT")
    private String content;

    public Long getTechKey() {
        return techKey;
    }

    public void setTechKey(Long techKey) {
        this.techKey = techKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
