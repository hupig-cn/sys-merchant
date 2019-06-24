package com.weisen.www.code.yjf.merchant.repository;

import com.weisen.www.code.yjf.merchant.domain.Dishestype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_DishestypeRepository extends JpaRepository<Dishestype, Long> {

    @Query(value = "from Dishestype where creator = ?1 and (logicdelete is null or logicdelete = 'true')")
    public List<Dishestype> getDishestypeByCreator(String creator);
}
