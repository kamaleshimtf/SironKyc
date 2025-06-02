package com.keydemo.repository;

import com.keydemo.entity.KycStatusEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KycStatusRepository implements PanacheRepository<KycStatusEntity> {
}
