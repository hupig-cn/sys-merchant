package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.domain.ManageOrg;
import com.weisen.www.code.yjf.merchant.repository.ManageOrgRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.ManageOrg}.
 */
@RestController
@RequestMapping("/api")
public class ManageOrgResource {

    private final Logger log = LoggerFactory.getLogger(ManageOrgResource.class);

    private static final String ENTITY_NAME = "merchantManageOrg";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManageOrgRepository manageOrgRepository;

    public ManageOrgResource(ManageOrgRepository manageOrgRepository) {
        this.manageOrgRepository = manageOrgRepository;
    }

    /**
     * {@code POST  /manage-orgs} : Create a new manageOrg.
     *
     * @param manageOrg the manageOrg to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new manageOrg, or with status {@code 400 (Bad Request)} if the manageOrg has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/manage-orgs")
    public ResponseEntity<ManageOrg> createManageOrg(@RequestBody ManageOrg manageOrg) throws URISyntaxException {
        log.debug("REST request to save ManageOrg : {}", manageOrg);
        if (manageOrg.getId() != null) {
            throw new BadRequestAlertException("A new manageOrg cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManageOrg result = manageOrgRepository.save(manageOrg);
        return ResponseEntity.created(new URI("/api/manage-orgs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /manage-orgs} : Updates an existing manageOrg.
     *
     * @param manageOrg the manageOrg to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manageOrg,
     * or with status {@code 400 (Bad Request)} if the manageOrg is not valid,
     * or with status {@code 500 (Internal Server Error)} if the manageOrg couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/manage-orgs")
    public ResponseEntity<ManageOrg> updateManageOrg(@RequestBody ManageOrg manageOrg) throws URISyntaxException {
        log.debug("REST request to update ManageOrg : {}", manageOrg);
        if (manageOrg.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ManageOrg result = manageOrgRepository.save(manageOrg);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, manageOrg.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /manage-orgs} : get all the manageOrgs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of manageOrgs in body.
     */
    @GetMapping("/manage-orgs")
    public List<ManageOrg> getAllManageOrgs() {
        log.debug("REST request to get all ManageOrgs");
        return manageOrgRepository.findAll();
    }

    /**
     * {@code GET  /manage-orgs/:id} : get the "id" manageOrg.
     *
     * @param id the id of the manageOrg to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the manageOrg, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/manage-orgs/{id}")
    public ResponseEntity<ManageOrg> getManageOrg(@PathVariable Long id) {
        log.debug("REST request to get ManageOrg : {}", id);
        Optional<ManageOrg> manageOrg = manageOrgRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(manageOrg);
    }

    /**
     * {@code DELETE  /manage-orgs/:id} : delete the "id" manageOrg.
     *
     * @param id the id of the manageOrg to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/manage-orgs/{id}")
    public ResponseEntity<Void> deleteManageOrg(@PathVariable Long id) {
        log.debug("REST request to delete ManageOrg : {}", id);
        manageOrgRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
