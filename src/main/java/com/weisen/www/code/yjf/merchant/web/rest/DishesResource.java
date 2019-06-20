package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.service.DishesService;
import com.weisen.www.code.yjf.merchant.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.merchant.service.dto.DishesDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.Dishes}.
 */
@RestController
@RequestMapping("/api")
public class DishesResource {

    private final Logger log = LoggerFactory.getLogger(DishesResource.class);

    private static final String ENTITY_NAME = "merchantDishes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DishesService dishesService;

    public DishesResource(DishesService dishesService) {
        this.dishesService = dishesService;
    }

    /**
     * {@code POST  /dishes} : Create a new dishes.
     *
     * @param dishesDTO the dishesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dishesDTO, or with status {@code 400 (Bad Request)} if the dishes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dishes")
    public ResponseEntity<DishesDTO> createDishes(@RequestBody DishesDTO dishesDTO) throws URISyntaxException {
        log.debug("REST request to save Dishes : {}", dishesDTO);
        if (dishesDTO.getId() != null) {
            throw new BadRequestAlertException("A new dishes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DishesDTO result = dishesService.save(dishesDTO);
        return ResponseEntity.created(new URI("/api/dishes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dishes} : Updates an existing dishes.
     *
     * @param dishesDTO the dishesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dishesDTO,
     * or with status {@code 400 (Bad Request)} if the dishesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dishesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dishes")
    public ResponseEntity<DishesDTO> updateDishes(@RequestBody DishesDTO dishesDTO) throws URISyntaxException {
        log.debug("REST request to update Dishes : {}", dishesDTO);
        if (dishesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DishesDTO result = dishesService.save(dishesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dishesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dishes} : get all the dishes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dishes in body.
     */
    @GetMapping("/dishes")
    public ResponseEntity<List<DishesDTO>> getAllDishes(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Dishes");
        Page<DishesDTO> page = dishesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dishes/:id} : get the "id" dishes.
     *
     * @param id the id of the dishesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dishesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dishes/{id}")
    public ResponseEntity<DishesDTO> getDishes(@PathVariable Long id) {
        log.debug("REST request to get Dishes : {}", id);
        Optional<DishesDTO> dishesDTO = dishesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dishesDTO);
    }

    /**
     * {@code DELETE  /dishes/:id} : delete the "id" dishes.
     *
     * @param id the id of the dishesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<Void> deleteDishes(@PathVariable Long id) {
        log.debug("REST request to delete Dishes : {}", id);
        dishesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
