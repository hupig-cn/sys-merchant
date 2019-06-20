package com.weisen.www.code.yjf.merchant.web.rest;

import com.weisen.www.code.yjf.merchant.service.ShoplocationService;
import com.weisen.www.code.yjf.merchant.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.merchant.service.dto.ShoplocationDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.merchant.domain.Shoplocation}.
 */
@RestController
@RequestMapping("/api")
public class ShoplocationResource {

    private final Logger log = LoggerFactory.getLogger(ShoplocationResource.class);

    private static final String ENTITY_NAME = "merchantShoplocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShoplocationService shoplocationService;

    public ShoplocationResource(ShoplocationService shoplocationService) {
        this.shoplocationService = shoplocationService;
    }

    /**
     * {@code POST  /shoplocations} : Create a new shoplocation.
     *
     * @param shoplocationDTO the shoplocationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shoplocationDTO, or with status {@code 400 (Bad Request)} if the shoplocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shoplocations")
    public ResponseEntity<ShoplocationDTO> createShoplocation(@RequestBody ShoplocationDTO shoplocationDTO) throws URISyntaxException {
        log.debug("REST request to save Shoplocation : {}", shoplocationDTO);
        if (shoplocationDTO.getId() != null) {
            throw new BadRequestAlertException("A new shoplocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShoplocationDTO result = shoplocationService.save(shoplocationDTO);
        return ResponseEntity.created(new URI("/api/shoplocations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shoplocations} : Updates an existing shoplocation.
     *
     * @param shoplocationDTO the shoplocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shoplocationDTO,
     * or with status {@code 400 (Bad Request)} if the shoplocationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shoplocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shoplocations")
    public ResponseEntity<ShoplocationDTO> updateShoplocation(@RequestBody ShoplocationDTO shoplocationDTO) throws URISyntaxException {
        log.debug("REST request to update Shoplocation : {}", shoplocationDTO);
        if (shoplocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShoplocationDTO result = shoplocationService.save(shoplocationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shoplocationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shoplocations} : get all the shoplocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shoplocations in body.
     */
    @GetMapping("/shoplocations")
    public ResponseEntity<List<ShoplocationDTO>> getAllShoplocations(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Shoplocations");
        Page<ShoplocationDTO> page = shoplocationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shoplocations/:id} : get the "id" shoplocation.
     *
     * @param id the id of the shoplocationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shoplocationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shoplocations/{id}")
    public ResponseEntity<ShoplocationDTO> getShoplocation(@PathVariable Long id) {
        log.debug("REST request to get Shoplocation : {}", id);
        Optional<ShoplocationDTO> shoplocationDTO = shoplocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shoplocationDTO);
    }

    /**
     * {@code DELETE  /shoplocations/:id} : delete the "id" shoplocation.
     *
     * @param id the id of the shoplocationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shoplocations/{id}")
    public ResponseEntity<Void> deleteShoplocation(@PathVariable Long id) {
        log.debug("REST request to delete Shoplocation : {}", id);
        shoplocationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
