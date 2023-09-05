package com.urlshortener.repository;
import com.urlshortener.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    @Query(value = "SELECT * FROM URL_ENTITY WHERE OUTPUT_URL = ?1", nativeQuery = true)
    Optional<UrlEntity> findByGeneratedUrl(String generated);

}
