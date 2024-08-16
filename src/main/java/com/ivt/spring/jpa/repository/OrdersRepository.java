package com.ivt.spring.jpa.repository;

import com.ivt.spring.jpa.entity.BookEntity;
import com.ivt.spring.jpa.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<OrdersEntity, Integer> {
    @Query("SELECT o FROM OrdersEntity o JOIN o.orderDetails od WHERE o.orderDate = CURRENT_DATE")
    List<OrdersEntity> getOrderByCurrentDate();
    @Query("SELECT o FROM OrdersEntity o JOIN o.orderDetails od GROUP BY o.id HAVING SUM(od.quantity * od.unitPrice) > 1000")
    List<OrdersEntity> listOrdersWithTotalAmountMoreThan1000();
    @Query("SELECT o FROM OrdersEntity o JOIN o.orderDetails od WHERE od.productName = 'JavaBook' GROUP BY o.id")
    List<OrdersEntity> getOrdersWithProductNameJavaBook();
}
