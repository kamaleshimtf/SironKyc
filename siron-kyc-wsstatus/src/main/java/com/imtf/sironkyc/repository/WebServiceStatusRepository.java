package com.imtf.sironkyc.repository;

import com.imtf.sironkyc.entity.WebServiceStatusEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WebServiceStatusRepository implements PanacheRepository<WebServiceStatusEntity> {
}
