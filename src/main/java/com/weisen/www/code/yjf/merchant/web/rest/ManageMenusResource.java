package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.domain.ManageMenus;
import com.weisen.www.code.yjf.merchant.repository.ManageMenusRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.ManageMenus}.
 */
@RestController
@RequestMapping("/api")
public class ManageMenusResource {

    private final Logger log = LoggerFactory.getLogger(ManageMenusResource.class);

    private static final String ENTITY_NAME = "merchantManageMenus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManageMenusRepository manageMenusRepository;

    public ManageMenusResource(ManageMenusRepository manageMenusRepository) {
        this.manageMenusRepository = manageMenusRepository;
    }

    /**
     * {@code POST  /manage-menus} : Create a new manageMenus.
     *
     * @param manageMenus the manageMenus to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new manageMenus, or with status {@code 400 (Bad Request)} if the manageMenus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/manage-menus")
    public ResponseEntity<ManageMenus> createManageMenus(@RequestBody ManageMenus manageMenus) throws URISyntaxException {
        log.debug("REST request to save ManageMenus : {}", manageMenus);
        if (manageMenus.getId() != null) {
            throw new BadRequestAlertException("A new manageMenus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManageMenus result = manageMenusRepository.save(manageMenus);
        return ResponseEntity.created(new URI("/api/manage-menus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /manage-menus} : Updates an existing manageMenus.
     *
     * @param manageMenus the manageMenus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manageMenus,
     * or with status {@code 400 (Bad Request)} if the manageMenus is not valid,
     * or with status {@code 500 (Internal Server Error)} if the manageMenus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/manage-menus")
    public ResponseEntity<ManageMenus> updateManageMenus(@RequestBody ManageMenus manageMenus) throws URISyntaxException {
        log.debug("REST request to update ManageMenus : {}", manageMenus);
        if (manageMenus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ManageMenus result = manageMenusRepository.save(manageMenus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, manageMenus.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /manage-menus} : get all the manageMenus.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of manageMenus in body.
     */
    @GetMapping("/manage-menus")
    public List<ManageMenus> getAllManageMenus() {
        log.debug("REST request to get all ManageMenus");
        return manageMenusRepository.findAll();
    }

    /**
     * {@code GET  /manage-menus/:id} : get the "id" manageMenus.
     *
     * @param id the id of the manageMenus to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the manageMenus, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/manage-menus/{id}")
    public ResponseEntity<ManageMenus> getManageMenus(@PathVariable Long id) {
        log.debug("REST request to get ManageMenus : {}", id);
        Optional<ManageMenus> manageMenus = manageMenusRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(manageMenus);
    }

    /**
     * {@code DELETE  /manage-menus/:id} : delete the "id" manageMenus.
     *
     * @param id the id of the manageMenus to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/manage-menus/{id}")
    public ResponseEntity<Void> deleteManageMenus(@PathVariable Long id) {
        log.debug("REST request to delete ManageMenus : {}", id);
        manageMenusRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
