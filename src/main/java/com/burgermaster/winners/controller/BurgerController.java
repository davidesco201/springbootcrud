package com.burgermaster.winners.controller;

import com.burgermaster.winners.dto.BurgerDto;
import com.burgermaster.winners.service.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BurgerController {
    @Autowired
    BurgerService burgerService;

    @GetMapping(value = "/winners")
    public ResponseEntity<List<BurgerDto>> getBurgers() {
        return ResponseEntity.ok(burgerService.getBurgers());
    }

    @GetMapping(value = "/winner")
    public ResponseEntity<BurgerDto> getBurger(@RequestParam String id) {
        return ResponseEntity.ok(burgerService.getBurger(id));
    }

    @RequestMapping(value = "/winner/create", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody BurgerDto burgerDto) throws Exception {
        burgerService.createBurger(burgerDto);
        return new ResponseEntity<>("Burger was created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/winner/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody BurgerDto burgerDto) throws Exception {
        burgerService.updateBurger(id, burgerDto);
        return new ResponseEntity<>("Burger was updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/winner/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        burgerService.deleteBurger(id);
        return new ResponseEntity<>("Burger was deleted successfully", HttpStatus.OK);
    }

}
