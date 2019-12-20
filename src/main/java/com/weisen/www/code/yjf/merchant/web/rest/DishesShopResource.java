package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.domain.DishesShop;
import com.weisen.www.code.yjf.merchant.repository.DishesShopRepository;
import com.weisen.www.code.yjf.merchant.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.DishesShop}.
 */
@RestController
@RequestMapping("/api")
public class DishesShopResource {

    private final Logger log = LoggerFactory.getLogger(DishesShopResource.class);

    private static final String ENTITY_NAME = "merchantDishesShop";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DishesShopRepository dishesShopRepository;

    public DishesShopResource(DishesShopRepository dishesShopRepository) {
        this.dishesShopRepository = dishesShopRepository;
    }

    /**
     * {@code POST  /dishes-shops} : Create a new dishesShop.
     *
     * @param dishesShop the dishesShop to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dishesShop, or with status {@code 400 (Bad Request)} if the dishesShop has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dishes-shops")
    public ResponseEntity<DishesShop> createDishesShop(@RequestBody DishesShop dishesShop) throws URISyntaxException {
        log.debug("REST request to save DishesShop : {}", dishesShop);
        if (dishesShop.getId() != null) {
            throw new BadRequestAlertException("A new dishesShop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DishesShop result = dishesShopRepository.save(dishesShop);
        return ResponseEntity.created(new URI("/api/dishes-shops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dishes-shops} : Updates an existing dishesShop.
     *
     * @param dishesShop the dishesShop to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dishesShop,
     * or with status {@code 400 (Bad Request)} if the dishesShop is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dishesShop couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dishes-shops")
    public ResponseEntity<DishesShop> updateDishesShop(@RequestBody DishesShop dishesShop) throws URISyntaxException {
        log.debug("REST request to update DishesShop : {}", dishesShop);
        if (dishesShop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DishesShop result = dishesShopRepository.save(dishesShop);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dishesShop.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dishes-shops} : get all the dishesShops.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dishesShops in body.
     */
    @GetMapping("/dishes-shops")
    public List<DishesShop> getAllDishesShops() {
        log.debug("REST request to get all DishesShops");
        return dishesShopRepository.findAll();
    }

    /**
     * {@code GET  /dishes-shops/:id} : get the "id" dishesShop.
     *
     * @param id the id of the dishesShop to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dishesShop, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dishes-shops/{id}")
    public ResponseEntity<DishesShop> getDishesShop(@PathVariable Long id) {
        log.debug("REST request to get DishesShop : {}", id);
        Optional<DishesShop> dishesShop = dishesShopRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dishesShop);
    }

    /**
     * {@code DELETE  /dishes-shops/:id} : delete the "id" dishesShop.
     *
     * @param id the id of the dishesShop to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dishes-shops/{id}")
    public ResponseEntity<Void> deleteDishesShop(@PathVariable Long id) {
        log.debug("REST request to delete DishesShop : {}", id);
        dishesShopRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
