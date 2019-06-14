package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MSituationAnnounceService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MSituationAnnounceDTO;
import io.shm.tsubasa.service.dto.MSituationAnnounceCriteria;
import io.shm.tsubasa.service.MSituationAnnounceQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MSituationAnnounce}.
 */
@RestController
@RequestMapping("/api")
public class MSituationAnnounceResource {

    private final Logger log = LoggerFactory.getLogger(MSituationAnnounceResource.class);

    private static final String ENTITY_NAME = "mSituationAnnounce";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MSituationAnnounceService mSituationAnnounceService;

    private final MSituationAnnounceQueryService mSituationAnnounceQueryService;

    public MSituationAnnounceResource(MSituationAnnounceService mSituationAnnounceService, MSituationAnnounceQueryService mSituationAnnounceQueryService) {
        this.mSituationAnnounceService = mSituationAnnounceService;
        this.mSituationAnnounceQueryService = mSituationAnnounceQueryService;
    }

    /**
     * {@code POST  /m-situation-announces} : Create a new mSituationAnnounce.
     *
     * @param mSituationAnnounceDTO the mSituationAnnounceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mSituationAnnounceDTO, or with status {@code 400 (Bad Request)} if the mSituationAnnounce has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-situation-announces")
    public ResponseEntity<MSituationAnnounceDTO> createMSituationAnnounce(@Valid @RequestBody MSituationAnnounceDTO mSituationAnnounceDTO) throws URISyntaxException {
        log.debug("REST request to save MSituationAnnounce : {}", mSituationAnnounceDTO);
        if (mSituationAnnounceDTO.getId() != null) {
            throw new BadRequestAlertException("A new mSituationAnnounce cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MSituationAnnounceDTO result = mSituationAnnounceService.save(mSituationAnnounceDTO);
        return ResponseEntity.created(new URI("/api/m-situation-announces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-situation-announces} : Updates an existing mSituationAnnounce.
     *
     * @param mSituationAnnounceDTO the mSituationAnnounceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mSituationAnnounceDTO,
     * or with status {@code 400 (Bad Request)} if the mSituationAnnounceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mSituationAnnounceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-situation-announces")
    public ResponseEntity<MSituationAnnounceDTO> updateMSituationAnnounce(@Valid @RequestBody MSituationAnnounceDTO mSituationAnnounceDTO) throws URISyntaxException {
        log.debug("REST request to update MSituationAnnounce : {}", mSituationAnnounceDTO);
        if (mSituationAnnounceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MSituationAnnounceDTO result = mSituationAnnounceService.save(mSituationAnnounceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mSituationAnnounceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-situation-announces} : get all the mSituationAnnounces.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mSituationAnnounces in body.
     */
    @GetMapping("/m-situation-announces")
    public ResponseEntity<List<MSituationAnnounceDTO>> getAllMSituationAnnounces(MSituationAnnounceCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MSituationAnnounces by criteria: {}", criteria);
        Page<MSituationAnnounceDTO> page = mSituationAnnounceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-situation-announces/count} : count all the mSituationAnnounces.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-situation-announces/count")
    public ResponseEntity<Long> countMSituationAnnounces(MSituationAnnounceCriteria criteria) {
        log.debug("REST request to count MSituationAnnounces by criteria: {}", criteria);
        return ResponseEntity.ok().body(mSituationAnnounceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-situation-announces/:id} : get the "id" mSituationAnnounce.
     *
     * @param id the id of the mSituationAnnounceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mSituationAnnounceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-situation-announces/{id}")
    public ResponseEntity<MSituationAnnounceDTO> getMSituationAnnounce(@PathVariable Long id) {
        log.debug("REST request to get MSituationAnnounce : {}", id);
        Optional<MSituationAnnounceDTO> mSituationAnnounceDTO = mSituationAnnounceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mSituationAnnounceDTO);
    }

    /**
     * {@code DELETE  /m-situation-announces/:id} : delete the "id" mSituationAnnounce.
     *
     * @param id the id of the mSituationAnnounceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-situation-announces/{id}")
    public ResponseEntity<Void> deleteMSituationAnnounce(@PathVariable Long id) {
        log.debug("REST request to delete MSituationAnnounce : {}", id);
        mSituationAnnounceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
