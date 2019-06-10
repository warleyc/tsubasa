package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDummyOpponentService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDummyOpponentDTO;
import io.shm.tsubasa.service.dto.MDummyOpponentCriteria;
import io.shm.tsubasa.service.MDummyOpponentQueryService;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.shm.tsubasa.domain.MDummyOpponent}.
 */
@RestController
@RequestMapping("/api")
public class MDummyOpponentResource {

    private final Logger log = LoggerFactory.getLogger(MDummyOpponentResource.class);

    private static final String ENTITY_NAME = "mDummyOpponent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDummyOpponentService mDummyOpponentService;

    private final MDummyOpponentQueryService mDummyOpponentQueryService;

    public MDummyOpponentResource(MDummyOpponentService mDummyOpponentService, MDummyOpponentQueryService mDummyOpponentQueryService) {
        this.mDummyOpponentService = mDummyOpponentService;
        this.mDummyOpponentQueryService = mDummyOpponentQueryService;
    }

    /**
     * {@code POST  /m-dummy-opponents} : Create a new mDummyOpponent.
     *
     * @param mDummyOpponentDTO the mDummyOpponentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDummyOpponentDTO, or with status {@code 400 (Bad Request)} if the mDummyOpponent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-dummy-opponents")
    public ResponseEntity<MDummyOpponentDTO> createMDummyOpponent(@Valid @RequestBody MDummyOpponentDTO mDummyOpponentDTO) throws URISyntaxException {
        log.debug("REST request to save MDummyOpponent : {}", mDummyOpponentDTO);
        if (mDummyOpponentDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDummyOpponent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDummyOpponentDTO result = mDummyOpponentService.save(mDummyOpponentDTO);
        return ResponseEntity.created(new URI("/api/m-dummy-opponents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-dummy-opponents} : Updates an existing mDummyOpponent.
     *
     * @param mDummyOpponentDTO the mDummyOpponentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDummyOpponentDTO,
     * or with status {@code 400 (Bad Request)} if the mDummyOpponentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDummyOpponentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-dummy-opponents")
    public ResponseEntity<MDummyOpponentDTO> updateMDummyOpponent(@Valid @RequestBody MDummyOpponentDTO mDummyOpponentDTO) throws URISyntaxException {
        log.debug("REST request to update MDummyOpponent : {}", mDummyOpponentDTO);
        if (mDummyOpponentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDummyOpponentDTO result = mDummyOpponentService.save(mDummyOpponentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDummyOpponentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-dummy-opponents} : get all the mDummyOpponents.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDummyOpponents in body.
     */
    @GetMapping("/m-dummy-opponents")
    public ResponseEntity<List<MDummyOpponentDTO>> getAllMDummyOpponents(MDummyOpponentCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDummyOpponents by criteria: {}", criteria);
        Page<MDummyOpponentDTO> page = mDummyOpponentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-dummy-opponents/count} : count all the mDummyOpponents.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-dummy-opponents/count")
    public ResponseEntity<Long> countMDummyOpponents(MDummyOpponentCriteria criteria) {
        log.debug("REST request to count MDummyOpponents by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDummyOpponentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-dummy-opponents/:id} : get the "id" mDummyOpponent.
     *
     * @param id the id of the mDummyOpponentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDummyOpponentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-dummy-opponents/{id}")
    public ResponseEntity<MDummyOpponentDTO> getMDummyOpponent(@PathVariable Long id) {
        log.debug("REST request to get MDummyOpponent : {}", id);
        Optional<MDummyOpponentDTO> mDummyOpponentDTO = mDummyOpponentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDummyOpponentDTO);
    }

    /**
     * {@code DELETE  /m-dummy-opponents/:id} : delete the "id" mDummyOpponent.
     *
     * @param id the id of the mDummyOpponentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-dummy-opponents/{id}")
    public ResponseEntity<Void> deleteMDummyOpponent(@PathVariable Long id) {
        log.debug("REST request to delete MDummyOpponent : {}", id);
        mDummyOpponentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
