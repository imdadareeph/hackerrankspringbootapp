package com.hackerrank.orm.service;

import com.hackerrank.orm.enums.ItemStatus;
import com.hackerrank.orm.exception.BadRequestException;
import com.hackerrank.orm.model.Item;
import com.hackerrank.orm.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ItemService {
    @Autowired
    public ItemRepository itemRepository;

    public Item save(Item item) {

        return Optional.of(!StringUtils.isEmpty(item.getItemId()))
                .filter(Boolean::booleanValue)
                .map(correctRequest ->
                        Optional.ofNullable(null == itemRepository.findByItemId(item.getItemId()))
                                .filter(Boolean::booleanValue)
                                .map(notPresentItem -> itemRepository.save(Item.builder()
                                        .itemName(item.getItemName())
                                        .itemEnteredByUser(item.getItemEnteredByUser())
                                        .itemEnteredDate(item.getItemEnteredDate())
                                        .itemBuyingPrice(item.getItemBuyingPrice())
                                        .itemSellingPrice(item.getItemSellingPrice())
                                        .itemLastModifiedDate(item.getItemLastModifiedDate())
                                        .itemLastModifiedByUser(item.getItemLastModifiedByUser())
                                        .itemStatus(item.getItemStatus())
                                        .build()))
                                .orElseThrow(() -> new BadRequestException())
                ).orElseThrow(() -> new BadRequestException());
    }

    public List<Item> findAll() {

        return itemRepository.findAll();
    }

    public Item findById(int id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.isPresent() ? optionalItem.get() : null;
    }

    public Item update(Item item) {
        Item item1 = findById(item.getItemId());
        if (item1 != null) {
            itemRepository.save(item);
        }
        return item1;
    }

    public void delete(int id) {
        itemRepository.deleteById(id);
    }

    public void deleteAll() {
        itemRepository.deleteAll();
    }

    public List<Item> getItemsByStatusAndUser(ItemStatus status, String enteredBy) {

        return itemRepository.findByItemStatusAndItemEnteredByUser(status,enteredBy);
    }

    public Page<Item> getSortedItemsWithPagination(int pageSize, int page, String sortByField) {
        Pageable pageable = PageRequest.of(page,pageSize, Sort.by(Sort.Order.desc(sortByField)));

        return itemRepository.findAll(pageable);
    }


}
