package com.weisen.www.code.yjf.merchant.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.weisen.www.code.yjf.merchant.service.DishestypeService;
import com.weisen.www.code.yjf.merchant.service.dto.DishestypeDTO;
import com.weisen.www.code.yjf.merchant.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.Dishestype}.
 */
@RestController
@RequestMapping("/api")
public class DishestypeResource {

    private final Logger log = LoggerFactory.getLogger(DishestypeResource.class);

    private static final String ENTITY_NAME = "merchantDishestype";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DishestypeService dishestypeService;

    public DishestypeResource(DishestypeService dishestypeService) {
        this.dishestypeService = dishestypeService;
    }

    /**
     * {@code POST  /dishestypes} : Create a new dishestype.
     *
     * @param dishestypeDTO the dishestypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dishestypeDTO, or with status {@code 400 (Bad Request)} if the dishestype has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dishestypes")
    public ResponseEntity<DishestypeDTO> createDishestype(@RequestBody DishestypeDTO dishestypeDTO) throws URISyntaxException {
        log.debug("REST request to save Dishestype : {}", dishestypeDTO);
        if (dishestypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new dishestype cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DishestypeDTO result = dishestypeService.save(dishestypeDTO);
        return ResponseEntity.created(new URI("/api/dishestypes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dishestypes} : Updates an existing dishestype.
     *
     * @param dishestypeDTO the dishestypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dishestypeDTO,
     * or with status {@code 400 (Bad Request)} if the dishestypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dishestypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dishestypes")
    public ResponseEntity<DishestypeDTO> updateDishestype(@RequestBody DishestypeDTO dishestypeDTO) throws URISyntaxException {
        log.debug("REST request to update Dishestype : {}", dishestypeDTO);
        if (dishestypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DishestypeDTO result = dishestypeService.save(dishestypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dishestypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dishestypes} : get all the dishestypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dishestypes in body.
     */
    @GetMapping("/dishestypes")
    public ResponseEntity<List<DishestypeDTO>> getAllDishestypes(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Dishestypes");
        Page<DishestypeDTO> page = dishestypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dishestypes/:id} : get the "id" dishestype.
     *
     * @param id the id of the dishestypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dishestypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dishestypes/{id}")
    public ResponseEntity<DishestypeDTO> getDishestype(@PathVariable Long id) {
        log.debug("REST request to get Dishestype : {}", id);
        Optional<DishestypeDTO> dishestypeDTO = dishestypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dishestypeDTO);
    }

    /**
     * {@code DELETE  /dishestypes/:id} : delete the "id" dishestype.
     *
     * @param id the id of the dishestypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dishestypes/{id}")
    public ResponseEntity<Void> deleteDishestype(@PathVariable Long id) {
        log.debug("REST request to delete Dishestype : {}", id);
        dishestypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
