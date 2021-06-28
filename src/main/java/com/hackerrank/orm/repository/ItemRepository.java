package com.hackerrank.orm.repository;

import com.hackerrank.orm.enums.ItemStatus;
import com.hackerrank.orm.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    Item save(Item item);

    Item findByItemId(int id);

    List<Item> findByItemStatusAndItemEnteredByUser(ItemStatus status, String enteredBy);


    Page<Item> findAll(Pageable pageable);
}
