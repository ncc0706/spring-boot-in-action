package io.arukas.repository;

import io.arukas.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, String> {
}
