package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.domain.Dishesbuy;
import com.weisen.www.code.yjf.merchant.repository.DishesbuyRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.Dishesbuy}.
 */
@RestController
@RequestMapping("/api")
public class DishesbuyResource {

    private final Logger log = LoggerFactory.getLogger(DishesbuyResource.class);

    private static final String ENTITY_NAME = "merchantDishesbuy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DishesbuyRepository dishesbuyRepository;

    public DishesbuyResource(DishesbuyRepository dishesbuyRepository) {
        this.dishesbuyRepository = dishesbuyRepository;
    }

    /**
     * {@code POST  /dishesbuys} : Create a new dishesbuy.
     *
     * @param dishesbuy the dishesbuy to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dishesbuy, or with status {@code 400 (Bad Request)} if the dishesbuy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dishesbuys")
    public ResponseEntity<Dishesbuy> createDishesbuy(@RequestBody Dishesbuy dishesbuy) throws URISyntaxException {
        log.debug("REST request to save Dishesbuy : {}", dishesbuy);
        if (dishesbuy.getId() != null) {
            throw new BadRequestAlertException("A new dishesbuy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Dishesbuy result = dishesbuyRepository.save(dishesbuy);
        return ResponseEntity.created(new URI("/api/dishesbuys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dishesbuys} : Updates an existing dishesbuy.
     *
     * @param dishesbuy the dishesbuy to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dishesbuy,
     * or with status {@code 400 (Bad Request)} if the dishesbuy is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dishesbuy couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dishesbuys")
    public ResponseEntity<Dishesbuy> updateDishesbuy(@RequestBody Dishesbuy dishesbuy) throws URISyntaxException {
        log.debug("REST request to update Dishesbuy : {}", dishesbuy);
        if (dishesbuy.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Dishesbuy result = dishesbuyRepository.save(dishesbuy);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dishesbuy.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dishesbuys} : get all the dishesbuys.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dishesbuys in body.
     */
    @GetMapping("/dishesbuys")
    public List<Dishesbuy> getAllDishesbuys() {
        log.debug("REST request to get all Dishesbuys");
        return dishesbuyRepository.findAll();
    }

    /**
     * {@code GET  /dishesbuys/:id} : get the "id" dishesbuy.
     *
     * @param id the id of the dishesbuy to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dishesbuy, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dishesbuys/{id}")
    public ResponseEntity<Dishesbuy> getDishesbuy(@PathVariable Long id) {
        log.debug("REST request to get Dishesbuy : {}", id);
        Optional<Dishesbuy> dishesbuy = dishesbuyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dishesbuy);
    }

    /**
     * {@code DELETE  /dishesbuys/:id} : delete the "id" dishesbuy.
     *
     * @param id the id of the dishesbuy to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dishesbuys/{id}")
    public ResponseEntity<Void> deleteDishesbuy(@PathVariable Long id) {
        log.debug("REST request to delete Dishesbuy : {}", id);
        dishesbuyRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
