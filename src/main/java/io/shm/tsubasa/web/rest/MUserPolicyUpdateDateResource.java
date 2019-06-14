package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MUserPolicyUpdateDateService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MUserPolicyUpdateDateDTO;
import io.shm.tsubasa.service.dto.MUserPolicyUpdateDateCriteria;
import io.shm.tsubasa.service.MUserPolicyUpdateDateQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MUserPolicyUpdateDate}.
 */
@RestController
@RequestMapping("/api")
public class MUserPolicyUpdateDateResource {

    private final Logger log = LoggerFactory.getLogger(MUserPolicyUpdateDateResource.class);

    private static final String ENTITY_NAME = "mUserPolicyUpdateDate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MUserPolicyUpdateDateService mUserPolicyUpdateDateService;

    private final MUserPolicyUpdateDateQueryService mUserPolicyUpdateDateQueryService;

    public MUserPolicyUpdateDateResource(MUserPolicyUpdateDateService mUserPolicyUpdateDateService, MUserPolicyUpdateDateQueryService mUserPolicyUpdateDateQueryService) {
        this.mUserPolicyUpdateDateService = mUserPolicyUpdateDateService;
        this.mUserPolicyUpdateDateQueryService = mUserPolicyUpdateDateQueryService;
    }

    /**
     * {@code POST  /m-user-policy-update-dates} : Create a new mUserPolicyUpdateDate.
     *
     * @param mUserPolicyUpdateDateDTO the mUserPolicyUpdateDateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mUserPolicyUpdateDateDTO, or with status {@code 400 (Bad Request)} if the mUserPolicyUpdateDate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-user-policy-update-dates")
    public ResponseEntity<MUserPolicyUpdateDateDTO> createMUserPolicyUpdateDate(@Valid @RequestBody MUserPolicyUpdateDateDTO mUserPolicyUpdateDateDTO) throws URISyntaxException {
        log.debug("REST request to save MUserPolicyUpdateDate : {}", mUserPolicyUpdateDateDTO);
        if (mUserPolicyUpdateDateDTO.getId() != null) {
            throw new BadRequestAlertException("A new mUserPolicyUpdateDate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MUserPolicyUpdateDateDTO result = mUserPolicyUpdateDateService.save(mUserPolicyUpdateDateDTO);
        return ResponseEntity.created(new URI("/api/m-user-policy-update-dates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-user-policy-update-dates} : Updates an existing mUserPolicyUpdateDate.
     *
     * @param mUserPolicyUpdateDateDTO the mUserPolicyUpdateDateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mUserPolicyUpdateDateDTO,
     * or with status {@code 400 (Bad Request)} if the mUserPolicyUpdateDateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mUserPolicyUpdateDateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-user-policy-update-dates")
    public ResponseEntity<MUserPolicyUpdateDateDTO> updateMUserPolicyUpdateDate(@Valid @RequestBody MUserPolicyUpdateDateDTO mUserPolicyUpdateDateDTO) throws URISyntaxException {
        log.debug("REST request to update MUserPolicyUpdateDate : {}", mUserPolicyUpdateDateDTO);
        if (mUserPolicyUpdateDateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MUserPolicyUpdateDateDTO result = mUserPolicyUpdateDateService.save(mUserPolicyUpdateDateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mUserPolicyUpdateDateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-user-policy-update-dates} : get all the mUserPolicyUpdateDates.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mUserPolicyUpdateDates in body.
     */
    @GetMapping("/m-user-policy-update-dates")
    public ResponseEntity<List<MUserPolicyUpdateDateDTO>> getAllMUserPolicyUpdateDates(MUserPolicyUpdateDateCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MUserPolicyUpdateDates by criteria: {}", criteria);
        Page<MUserPolicyUpdateDateDTO> page = mUserPolicyUpdateDateQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-user-policy-update-dates/count} : count all the mUserPolicyUpdateDates.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-user-policy-update-dates/count")
    public ResponseEntity<Long> countMUserPolicyUpdateDates(MUserPolicyUpdateDateCriteria criteria) {
        log.debug("REST request to count MUserPolicyUpdateDates by criteria: {}", criteria);
        return ResponseEntity.ok().body(mUserPolicyUpdateDateQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-user-policy-update-dates/:id} : get the "id" mUserPolicyUpdateDate.
     *
     * @param id the id of the mUserPolicyUpdateDateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mUserPolicyUpdateDateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-user-policy-update-dates/{id}")
    public ResponseEntity<MUserPolicyUpdateDateDTO> getMUserPolicyUpdateDate(@PathVariable Long id) {
        log.debug("REST request to get MUserPolicyUpdateDate : {}", id);
        Optional<MUserPolicyUpdateDateDTO> mUserPolicyUpdateDateDTO = mUserPolicyUpdateDateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mUserPolicyUpdateDateDTO);
    }

    /**
     * {@code DELETE  /m-user-policy-update-dates/:id} : delete the "id" mUserPolicyUpdateDate.
     *
     * @param id the id of the mUserPolicyUpdateDateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-user-policy-update-dates/{id}")
    public ResponseEntity<Void> deleteMUserPolicyUpdateDate(@PathVariable Long id) {
        log.debug("REST request to delete MUserPolicyUpdateDate : {}", id);
        mUserPolicyUpdateDateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
