package com.imtf.siron.repository;

import com.imtf.siron.entity.WebServiceStatusEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WebServiceStatusRepository implements PanacheRepository<WebServiceStatusEntity> {
}
