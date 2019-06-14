package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MUserRankService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MUserRankDTO;
import io.shm.tsubasa.service.dto.MUserRankCriteria;
import io.shm.tsubasa.service.MUserRankQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MUserRank}.
 */
@RestController
@RequestMapping("/api")
public class MUserRankResource {

    private final Logger log = LoggerFactory.getLogger(MUserRankResource.class);

    private static final String ENTITY_NAME = "mUserRank";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MUserRankService mUserRankService;

    private final MUserRankQueryService mUserRankQueryService;

    public MUserRankResource(MUserRankService mUserRankService, MUserRankQueryService mUserRankQueryService) {
        this.mUserRankService = mUserRankService;
        this.mUserRankQueryService = mUserRankQueryService;
    }

    /**
     * {@code POST  /m-user-ranks} : Create a new mUserRank.
     *
     * @param mUserRankDTO the mUserRankDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mUserRankDTO, or with status {@code 400 (Bad Request)} if the mUserRank has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-user-ranks")
    public ResponseEntity<MUserRankDTO> createMUserRank(@Valid @RequestBody MUserRankDTO mUserRankDTO) throws URISyntaxException {
        log.debug("REST request to save MUserRank : {}", mUserRankDTO);
        if (mUserRankDTO.getId() != null) {
            throw new BadRequestAlertException("A new mUserRank cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MUserRankDTO result = mUserRankService.save(mUserRankDTO);
        return ResponseEntity.created(new URI("/api/m-user-ranks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-user-ranks} : Updates an existing mUserRank.
     *
     * @param mUserRankDTO the mUserRankDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mUserRankDTO,
     * or with status {@code 400 (Bad Request)} if the mUserRankDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mUserRankDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-user-ranks")
    public ResponseEntity<MUserRankDTO> updateMUserRank(@Valid @RequestBody MUserRankDTO mUserRankDTO) throws URISyntaxException {
        log.debug("REST request to update MUserRank : {}", mUserRankDTO);
        if (mUserRankDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MUserRankDTO result = mUserRankService.save(mUserRankDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mUserRankDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-user-ranks} : get all the mUserRanks.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mUserRanks in body.
     */
    @GetMapping("/m-user-ranks")
    public ResponseEntity<List<MUserRankDTO>> getAllMUserRanks(MUserRankCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MUserRanks by criteria: {}", criteria);
        Page<MUserRankDTO> page = mUserRankQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-user-ranks/count} : count all the mUserRanks.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-user-ranks/count")
    public ResponseEntity<Long> countMUserRanks(MUserRankCriteria criteria) {
        log.debug("REST request to count MUserRanks by criteria: {}", criteria);
        return ResponseEntity.ok().body(mUserRankQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-user-ranks/:id} : get the "id" mUserRank.
     *
     * @param id the id of the mUserRankDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mUserRankDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-user-ranks/{id}")
    public ResponseEntity<MUserRankDTO> getMUserRank(@PathVariable Long id) {
        log.debug("REST request to get MUserRank : {}", id);
        Optional<MUserRankDTO> mUserRankDTO = mUserRankService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mUserRankDTO);
    }

    /**
     * {@code DELETE  /m-user-ranks/:id} : delete the "id" mUserRank.
     *
     * @param id the id of the mUserRankDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-user-ranks/{id}")
    public ResponseEntity<Void> deleteMUserRank(@PathVariable Long id) {
        log.debug("REST request to delete MUserRank : {}", id);
        mUserRankService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
