package ru.safiullina.HWCreditRequest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.safiullina.HWCreditRequest.entity.CreditRequestEntity;

@Repository
public interface CreditRepository extends JpaRepository<CreditRequestEntity, Integer> {

    @Modifying
    @Query("UPDATE CreditRequestEntity t SET t.status = :status WHERE t.id = :id")
    void updateStatusById(@Param("status") String status, @Param("id") Integer id);

}
