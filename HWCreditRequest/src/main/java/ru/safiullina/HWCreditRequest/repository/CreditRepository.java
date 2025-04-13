package ru.safiullina.HWCreditRequest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.safiullina.HWCreditRequest.entity.CreditRequestEntity;

@Repository
public interface CreditRepository extends JpaRepository<CreditRequestEntity, Integer> {

    @Modifying
    @Query("update CreditRequestEntity T set T.status = ?1 where T.id = ?2")
    void setStatusById(String status, Integer id);

}
