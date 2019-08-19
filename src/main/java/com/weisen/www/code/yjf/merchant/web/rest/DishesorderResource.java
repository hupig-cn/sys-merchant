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

import com.weisen.www.code.yjf.merchant.service.DishesorderService;
import com.weisen.www.code.yjf.merchant.service.dto.DishesorderDTO;
import com.weisen.www.code.yjf.merchant.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.Dishesorder}.
 */
@RestController
@RequestMapping("/api")
public class DishesorderResource {

    private final Logger log = LoggerFactory.getLogger(DishesorderResource.class);

    private static final String ENTITY_NAME = "merchantDishesorder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DishesorderService dishesorderService;

    public DishesorderResource(DishesorderService dishesorderService) {
        this.dishesorderService = dishesorderService;
    }

    /**
     * {@code POST  /dishesorders} : Create a new dishesorder.
     *
     * @param dishesorderDTO the dishesorderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dishesorderDTO, or with status {@code 400 (Bad Request)} if the dishesorder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dishesorders")
    public ResponseEntity<DishesorderDTO> createDishesorder(@RequestBody DishesorderDTO dishesorderDTO) throws URISyntaxException {
        log.debug("REST request to save Dishesorder : {}", dishesorderDTO);
        if (dishesorderDTO.getId() != null) {
            throw new BadRequestAlertException("A new dishesorder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DishesorderDTO result = dishesorderService.save(dishesorderDTO);
        return ResponseEntity.created(new URI("/api/dishesorders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dishesorders} : Updates an existing dishesorder.
     *
     * @param dishesorderDTO the dishesorderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dishesorderDTO,
     * or with status {@code 400 (Bad Request)} if the dishesorderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dishesorderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dishesorders")
    public ResponseEntity<DishesorderDTO> updateDishesorder(@RequestBody DishesorderDTO dishesorderDTO) throws URISyntaxException {
        log.debug("REST request to update Dishesorder : {}", dishesorderDTO);
        if (dishesorderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DishesorderDTO result = dishesorderService.save(dishesorderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dishesorderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dishesorders} : get all the dishesorders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dishesorders in body.
     */
    @GetMapping("/dishesorders")
    public ResponseEntity<List<DishesorderDTO>> getAllDishesorders(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Dishesorders");
        Page<DishesorderDTO> page = dishesorderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dishesorders/:id} : get the "id" dishesorder.
     *
     * @param id the id of the dishesorderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dishesorderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dishesorders/{id}")
    public ResponseEntity<DishesorderDTO> getDishesorder(@PathVariable Long id) {
        log.debug("REST request to get Dishesorder : {}", id);
        Optional<DishesorderDTO> dishesorderDTO = dishesorderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dishesorderDTO);
    }

    /**
     * {@code DELETE  /dishesorders/:id} : delete the "id" dishesorder.
     *
     * @param id the id of the dishesorderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dishesorders/{id}")
    public ResponseEntity<Void> deleteDishesorder(@PathVariable Long id) {
        log.debug("REST request to delete Dishesorder : {}", id);
        dishesorderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
