package io.arukas.repo;

import io.arukas.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

    public Tag findByTagName(String name);

}
