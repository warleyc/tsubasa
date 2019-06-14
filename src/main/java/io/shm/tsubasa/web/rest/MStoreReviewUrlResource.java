package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MStoreReviewUrlService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MStoreReviewUrlDTO;
import io.shm.tsubasa.service.dto.MStoreReviewUrlCriteria;
import io.shm.tsubasa.service.MStoreReviewUrlQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MStoreReviewUrl}.
 */
@RestController
@RequestMapping("/api")
public class MStoreReviewUrlResource {

    private final Logger log = LoggerFactory.getLogger(MStoreReviewUrlResource.class);

    private static final String ENTITY_NAME = "mStoreReviewUrl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MStoreReviewUrlService mStoreReviewUrlService;

    private final MStoreReviewUrlQueryService mStoreReviewUrlQueryService;

    public MStoreReviewUrlResource(MStoreReviewUrlService mStoreReviewUrlService, MStoreReviewUrlQueryService mStoreReviewUrlQueryService) {
        this.mStoreReviewUrlService = mStoreReviewUrlService;
        this.mStoreReviewUrlQueryService = mStoreReviewUrlQueryService;
    }

    /**
     * {@code POST  /m-store-review-urls} : Create a new mStoreReviewUrl.
     *
     * @param mStoreReviewUrlDTO the mStoreReviewUrlDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mStoreReviewUrlDTO, or with status {@code 400 (Bad Request)} if the mStoreReviewUrl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-store-review-urls")
    public ResponseEntity<MStoreReviewUrlDTO> createMStoreReviewUrl(@Valid @RequestBody MStoreReviewUrlDTO mStoreReviewUrlDTO) throws URISyntaxException {
        log.debug("REST request to save MStoreReviewUrl : {}", mStoreReviewUrlDTO);
        if (mStoreReviewUrlDTO.getId() != null) {
            throw new BadRequestAlertException("A new mStoreReviewUrl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MStoreReviewUrlDTO result = mStoreReviewUrlService.save(mStoreReviewUrlDTO);
        return ResponseEntity.created(new URI("/api/m-store-review-urls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-store-review-urls} : Updates an existing mStoreReviewUrl.
     *
     * @param mStoreReviewUrlDTO the mStoreReviewUrlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mStoreReviewUrlDTO,
     * or with status {@code 400 (Bad Request)} if the mStoreReviewUrlDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mStoreReviewUrlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-store-review-urls")
    public ResponseEntity<MStoreReviewUrlDTO> updateMStoreReviewUrl(@Valid @RequestBody MStoreReviewUrlDTO mStoreReviewUrlDTO) throws URISyntaxException {
        log.debug("REST request to update MStoreReviewUrl : {}", mStoreReviewUrlDTO);
        if (mStoreReviewUrlDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MStoreReviewUrlDTO result = mStoreReviewUrlService.save(mStoreReviewUrlDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mStoreReviewUrlDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-store-review-urls} : get all the mStoreReviewUrls.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mStoreReviewUrls in body.
     */
    @GetMapping("/m-store-review-urls")
    public ResponseEntity<List<MStoreReviewUrlDTO>> getAllMStoreReviewUrls(MStoreReviewUrlCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MStoreReviewUrls by criteria: {}", criteria);
        Page<MStoreReviewUrlDTO> page = mStoreReviewUrlQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-store-review-urls/count} : count all the mStoreReviewUrls.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-store-review-urls/count")
    public ResponseEntity<Long> countMStoreReviewUrls(MStoreReviewUrlCriteria criteria) {
        log.debug("REST request to count MStoreReviewUrls by criteria: {}", criteria);
        return ResponseEntity.ok().body(mStoreReviewUrlQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-store-review-urls/:id} : get the "id" mStoreReviewUrl.
     *
     * @param id the id of the mStoreReviewUrlDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mStoreReviewUrlDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-store-review-urls/{id}")
    public ResponseEntity<MStoreReviewUrlDTO> getMStoreReviewUrl(@PathVariable Long id) {
        log.debug("REST request to get MStoreReviewUrl : {}", id);
        Optional<MStoreReviewUrlDTO> mStoreReviewUrlDTO = mStoreReviewUrlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mStoreReviewUrlDTO);
    }

    /**
     * {@code DELETE  /m-store-review-urls/:id} : delete the "id" mStoreReviewUrl.
     *
     * @param id the id of the mStoreReviewUrlDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-store-review-urls/{id}")
    public ResponseEntity<Void> deleteMStoreReviewUrl(@PathVariable Long id) {
        log.debug("REST request to delete MStoreReviewUrl : {}", id);
        mStoreReviewUrlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
