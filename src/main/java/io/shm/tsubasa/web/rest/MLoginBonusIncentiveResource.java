package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLoginBonusIncentiveService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLoginBonusIncentiveDTO;
import io.shm.tsubasa.service.dto.MLoginBonusIncentiveCriteria;
import io.shm.tsubasa.service.MLoginBonusIncentiveQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLoginBonusIncentive}.
 */
@RestController
@RequestMapping("/api")
public class MLoginBonusIncentiveResource {

    private final Logger log = LoggerFactory.getLogger(MLoginBonusIncentiveResource.class);

    private static final String ENTITY_NAME = "mLoginBonusIncentive";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLoginBonusIncentiveService mLoginBonusIncentiveService;

    private final MLoginBonusIncentiveQueryService mLoginBonusIncentiveQueryService;

    public MLoginBonusIncentiveResource(MLoginBonusIncentiveService mLoginBonusIncentiveService, MLoginBonusIncentiveQueryService mLoginBonusIncentiveQueryService) {
        this.mLoginBonusIncentiveService = mLoginBonusIncentiveService;
        this.mLoginBonusIncentiveQueryService = mLoginBonusIncentiveQueryService;
    }

    /**
     * {@code POST  /m-login-bonus-incentives} : Create a new mLoginBonusIncentive.
     *
     * @param mLoginBonusIncentiveDTO the mLoginBonusIncentiveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLoginBonusIncentiveDTO, or with status {@code 400 (Bad Request)} if the mLoginBonusIncentive has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-login-bonus-incentives")
    public ResponseEntity<MLoginBonusIncentiveDTO> createMLoginBonusIncentive(@Valid @RequestBody MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO) throws URISyntaxException {
        log.debug("REST request to save MLoginBonusIncentive : {}", mLoginBonusIncentiveDTO);
        if (mLoginBonusIncentiveDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLoginBonusIncentive cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLoginBonusIncentiveDTO result = mLoginBonusIncentiveService.save(mLoginBonusIncentiveDTO);
        return ResponseEntity.created(new URI("/api/m-login-bonus-incentives/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-login-bonus-incentives} : Updates an existing mLoginBonusIncentive.
     *
     * @param mLoginBonusIncentiveDTO the mLoginBonusIncentiveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLoginBonusIncentiveDTO,
     * or with status {@code 400 (Bad Request)} if the mLoginBonusIncentiveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLoginBonusIncentiveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-login-bonus-incentives")
    public ResponseEntity<MLoginBonusIncentiveDTO> updateMLoginBonusIncentive(@Valid @RequestBody MLoginBonusIncentiveDTO mLoginBonusIncentiveDTO) throws URISyntaxException {
        log.debug("REST request to update MLoginBonusIncentive : {}", mLoginBonusIncentiveDTO);
        if (mLoginBonusIncentiveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLoginBonusIncentiveDTO result = mLoginBonusIncentiveService.save(mLoginBonusIncentiveDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLoginBonusIncentiveDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-login-bonus-incentives} : get all the mLoginBonusIncentives.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLoginBonusIncentives in body.
     */
    @GetMapping("/m-login-bonus-incentives")
    public ResponseEntity<List<MLoginBonusIncentiveDTO>> getAllMLoginBonusIncentives(MLoginBonusIncentiveCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLoginBonusIncentives by criteria: {}", criteria);
        Page<MLoginBonusIncentiveDTO> page = mLoginBonusIncentiveQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-login-bonus-incentives/count} : count all the mLoginBonusIncentives.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-login-bonus-incentives/count")
    public ResponseEntity<Long> countMLoginBonusIncentives(MLoginBonusIncentiveCriteria criteria) {
        log.debug("REST request to count MLoginBonusIncentives by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLoginBonusIncentiveQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-login-bonus-incentives/:id} : get the "id" mLoginBonusIncentive.
     *
     * @param id the id of the mLoginBonusIncentiveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLoginBonusIncentiveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-login-bonus-incentives/{id}")
    public ResponseEntity<MLoginBonusIncentiveDTO> getMLoginBonusIncentive(@PathVariable Long id) {
        log.debug("REST request to get MLoginBonusIncentive : {}", id);
        Optional<MLoginBonusIncentiveDTO> mLoginBonusIncentiveDTO = mLoginBonusIncentiveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLoginBonusIncentiveDTO);
    }

    /**
     * {@code DELETE  /m-login-bonus-incentives/:id} : delete the "id" mLoginBonusIncentive.
     *
     * @param id the id of the mLoginBonusIncentiveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-login-bonus-incentives/{id}")
    public ResponseEntity<Void> deleteMLoginBonusIncentive(@PathVariable Long id) {
        log.debug("REST request to delete MLoginBonusIncentive : {}", id);
        mLoginBonusIncentiveService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
