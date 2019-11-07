package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.domain.ManageUser;
import com.weisen.www.code.yjf.merchant.repository.ManageUserRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.ManageUser}.
 */
@RestController
@RequestMapping("/api")
public class ManageUserResource {

    private final Logger log = LoggerFactory.getLogger(ManageUserResource.class);

    private static final String ENTITY_NAME = "merchantManageUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManageUserRepository manageUserRepository;

    public ManageUserResource(ManageUserRepository manageUserRepository) {
        this.manageUserRepository = manageUserRepository;
    }

    /**
     * {@code POST  /manage-users} : Create a new manageUser.
     *
     * @param manageUser the manageUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new manageUser, or with status {@code 400 (Bad Request)} if the manageUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/manage-users")
    public ResponseEntity<ManageUser> createManageUser(@RequestBody ManageUser manageUser) throws URISyntaxException {
        log.debug("REST request to save ManageUser : {}", manageUser);
        if (manageUser.getId() != null) {
            throw new BadRequestAlertException("A new manageUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManageUser result = manageUserRepository.save(manageUser);
        return ResponseEntity.created(new URI("/api/manage-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /manage-users} : Updates an existing manageUser.
     *
     * @param manageUser the manageUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manageUser,
     * or with status {@code 400 (Bad Request)} if the manageUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the manageUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/manage-users")
    public ResponseEntity<ManageUser> updateManageUser(@RequestBody ManageUser manageUser) throws URISyntaxException {
        log.debug("REST request to update ManageUser : {}", manageUser);
        if (manageUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ManageUser result = manageUserRepository.save(manageUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, manageUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /manage-users} : get all the manageUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of manageUsers in body.
     */
    @GetMapping("/manage-users")
    public List<ManageUser> getAllManageUsers() {
        log.debug("REST request to get all ManageUsers");
        return manageUserRepository.findAll();
    }

    /**
     * {@code GET  /manage-users/:id} : get the "id" manageUser.
     *
     * @param id the id of the manageUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the manageUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/manage-users/{id}")
    public ResponseEntity<ManageUser> getManageUser(@PathVariable Long id) {
        log.debug("REST request to get ManageUser : {}", id);
        Optional<ManageUser> manageUser = manageUserRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(manageUser);
    }

    /**
     * {@code DELETE  /manage-users/:id} : delete the "id" manageUser.
     *
     * @param id the id of the manageUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/manage-users/{id}")
    public ResponseEntity<Void> deleteManageUser(@PathVariable Long id) {
        log.debug("REST request to delete ManageUser : {}", id);
        manageUserRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
