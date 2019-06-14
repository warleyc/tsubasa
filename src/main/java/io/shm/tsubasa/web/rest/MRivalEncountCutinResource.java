package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MRivalEncountCutinService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MRivalEncountCutinDTO;
import io.shm.tsubasa.service.dto.MRivalEncountCutinCriteria;
import io.shm.tsubasa.service.MRivalEncountCutinQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MRivalEncountCutin}.
 */
@RestController
@RequestMapping("/api")
public class MRivalEncountCutinResource {

    private final Logger log = LoggerFactory.getLogger(MRivalEncountCutinResource.class);

    private static final String ENTITY_NAME = "mRivalEncountCutin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MRivalEncountCutinService mRivalEncountCutinService;

    private final MRivalEncountCutinQueryService mRivalEncountCutinQueryService;

    public MRivalEncountCutinResource(MRivalEncountCutinService mRivalEncountCutinService, MRivalEncountCutinQueryService mRivalEncountCutinQueryService) {
        this.mRivalEncountCutinService = mRivalEncountCutinService;
        this.mRivalEncountCutinQueryService = mRivalEncountCutinQueryService;
    }

    /**
     * {@code POST  /m-rival-encount-cutins} : Create a new mRivalEncountCutin.
     *
     * @param mRivalEncountCutinDTO the mRivalEncountCutinDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mRivalEncountCutinDTO, or with status {@code 400 (Bad Request)} if the mRivalEncountCutin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-rival-encount-cutins")
    public ResponseEntity<MRivalEncountCutinDTO> createMRivalEncountCutin(@Valid @RequestBody MRivalEncountCutinDTO mRivalEncountCutinDTO) throws URISyntaxException {
        log.debug("REST request to save MRivalEncountCutin : {}", mRivalEncountCutinDTO);
        if (mRivalEncountCutinDTO.getId() != null) {
            throw new BadRequestAlertException("A new mRivalEncountCutin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MRivalEncountCutinDTO result = mRivalEncountCutinService.save(mRivalEncountCutinDTO);
        return ResponseEntity.created(new URI("/api/m-rival-encount-cutins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-rival-encount-cutins} : Updates an existing mRivalEncountCutin.
     *
     * @param mRivalEncountCutinDTO the mRivalEncountCutinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mRivalEncountCutinDTO,
     * or with status {@code 400 (Bad Request)} if the mRivalEncountCutinDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mRivalEncountCutinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-rival-encount-cutins")
    public ResponseEntity<MRivalEncountCutinDTO> updateMRivalEncountCutin(@Valid @RequestBody MRivalEncountCutinDTO mRivalEncountCutinDTO) throws URISyntaxException {
        log.debug("REST request to update MRivalEncountCutin : {}", mRivalEncountCutinDTO);
        if (mRivalEncountCutinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MRivalEncountCutinDTO result = mRivalEncountCutinService.save(mRivalEncountCutinDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mRivalEncountCutinDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-rival-encount-cutins} : get all the mRivalEncountCutins.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mRivalEncountCutins in body.
     */
    @GetMapping("/m-rival-encount-cutins")
    public ResponseEntity<List<MRivalEncountCutinDTO>> getAllMRivalEncountCutins(MRivalEncountCutinCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MRivalEncountCutins by criteria: {}", criteria);
        Page<MRivalEncountCutinDTO> page = mRivalEncountCutinQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-rival-encount-cutins/count} : count all the mRivalEncountCutins.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-rival-encount-cutins/count")
    public ResponseEntity<Long> countMRivalEncountCutins(MRivalEncountCutinCriteria criteria) {
        log.debug("REST request to count MRivalEncountCutins by criteria: {}", criteria);
        return ResponseEntity.ok().body(mRivalEncountCutinQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-rival-encount-cutins/:id} : get the "id" mRivalEncountCutin.
     *
     * @param id the id of the mRivalEncountCutinDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mRivalEncountCutinDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-rival-encount-cutins/{id}")
    public ResponseEntity<MRivalEncountCutinDTO> getMRivalEncountCutin(@PathVariable Long id) {
        log.debug("REST request to get MRivalEncountCutin : {}", id);
        Optional<MRivalEncountCutinDTO> mRivalEncountCutinDTO = mRivalEncountCutinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mRivalEncountCutinDTO);
    }

    /**
     * {@code DELETE  /m-rival-encount-cutins/:id} : delete the "id" mRivalEncountCutin.
     *
     * @param id the id of the mRivalEncountCutinDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-rival-encount-cutins/{id}")
    public ResponseEntity<Void> deleteMRivalEncountCutin(@PathVariable Long id) {
        log.debug("REST request to delete MRivalEncountCutin : {}", id);
        mRivalEncountCutinService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
