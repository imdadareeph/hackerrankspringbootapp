package com.hackerrank.orm.controller;

import com.hackerrank.orm.enums.ItemStatus;
import com.hackerrank.orm.exception.BadRequestException;
import com.hackerrank.orm.exception.ResourceNotFoundException;
import com.hackerrank.orm.model.Item;
import com.hackerrank.orm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ItemService itemService;

    //1. insert POST
    @RequestMapping(value = "/app/item", method = RequestMethod.POST)
    public ResponseEntity<Item> addItems(@RequestBody Item item) {
        return new ResponseEntity<Item>(itemService.save(item), HttpStatus.CREATED);
    }


    //2. update PUT

    @RequestMapping(value = "/app/item/{itemId}", method = RequestMethod.PUT)
    public ResponseEntity<Item> update(@PathVariable int id, @RequestBody Item item) {
        Item itemExist = itemService.findById(id);

        if (itemExist == null) {
            throw new ResourceNotFoundException();
        }


        itemService.update(item);
        return new ResponseEntity<Item>(itemExist, HttpStatus.OK);
    }


    //3. delete by itemId DELETE
    @RequestMapping(value = "/app/item/{itemId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable int id) {

        Item existingBook = itemService.findById(id);
        if (existingBook == null) {
            throw new BadRequestException();
        }
        itemService.delete(id);
        return new ResponseEntity<String>("Record deleted Successfully", HttpStatus.OK);
    }


    //4. delete all DELETE

    @RequestMapping(value = "/app/item/", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAll(@PathVariable int id) {

        itemService.deleteAll();
        return new ResponseEntity<String>("All Record deleted Successfully", HttpStatus.OK);
    }


    //5. get by itemId GET
    @RequestMapping(value = "/app/item/{itemId}", method = RequestMethod.GET)
    public @ResponseBody
    Item findItemById(@PathVariable("itemId") int itemId) {
        return itemService.findById(itemId);
    }


    //6. get all GET

    @RequestMapping(value = "/app/items", method = RequestMethod.GET)
    public @ResponseBody
    List<Item> getAllItems() {
        return (List<Item>) itemService.findAll();
    }


    //7. filters by fields ?itemStatus={status}&itemEnteredByUser={modifiedBy} GET

    @RequestMapping(value = "/app/item?itemStatus={status}&itemEnteredByUser={enteredBy}", method = RequestMethod.GET)
    public @ResponseBody
    List<Item> getItemsByStatusAndUser(@PathVariable ItemStatus status, @PathVariable String enteredBy) {
        return (List<Item>) itemService.getItemsByStatusAndUser(status,enteredBy);
    }


    //8. select all with sorting and pagination ?pageSize={pageSize}&page={page}&sortBy={sortBy} GET

    @RequestMapping(value = "app/item?pageSize={pageSize}&page={page}&sortBy={sortByField}", method = RequestMethod.GET)
    public @ResponseBody
    List<Item> getSortedItemsWithPagination(@PathVariable int pageSize, @PathVariable int page, @PathVariable String sortByField) {
        return (List<Item>) itemService.getSortedItemsWithPagination(pageSize,page,sortByField);
    }

}
