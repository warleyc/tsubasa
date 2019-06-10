package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionKickerService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionKickerDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionKickerCriteria;
import io.shm.tsubasa.service.MGachaRenditionKickerQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionKicker}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionKickerResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionKickerResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionKicker";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionKickerService mGachaRenditionKickerService;

    private final MGachaRenditionKickerQueryService mGachaRenditionKickerQueryService;

    public MGachaRenditionKickerResource(MGachaRenditionKickerService mGachaRenditionKickerService, MGachaRenditionKickerQueryService mGachaRenditionKickerQueryService) {
        this.mGachaRenditionKickerService = mGachaRenditionKickerService;
        this.mGachaRenditionKickerQueryService = mGachaRenditionKickerQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-kickers} : Create a new mGachaRenditionKicker.
     *
     * @param mGachaRenditionKickerDTO the mGachaRenditionKickerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionKickerDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionKicker has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-kickers")
    public ResponseEntity<MGachaRenditionKickerDTO> createMGachaRenditionKicker(@Valid @RequestBody MGachaRenditionKickerDTO mGachaRenditionKickerDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionKicker : {}", mGachaRenditionKickerDTO);
        if (mGachaRenditionKickerDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionKicker cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionKickerDTO result = mGachaRenditionKickerService.save(mGachaRenditionKickerDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-kickers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-kickers} : Updates an existing mGachaRenditionKicker.
     *
     * @param mGachaRenditionKickerDTO the mGachaRenditionKickerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionKickerDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionKickerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionKickerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-kickers")
    public ResponseEntity<MGachaRenditionKickerDTO> updateMGachaRenditionKicker(@Valid @RequestBody MGachaRenditionKickerDTO mGachaRenditionKickerDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionKicker : {}", mGachaRenditionKickerDTO);
        if (mGachaRenditionKickerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionKickerDTO result = mGachaRenditionKickerService.save(mGachaRenditionKickerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionKickerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-kickers} : get all the mGachaRenditionKickers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionKickers in body.
     */
    @GetMapping("/m-gacha-rendition-kickers")
    public ResponseEntity<List<MGachaRenditionKickerDTO>> getAllMGachaRenditionKickers(MGachaRenditionKickerCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionKickers by criteria: {}", criteria);
        Page<MGachaRenditionKickerDTO> page = mGachaRenditionKickerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-kickers/count} : count all the mGachaRenditionKickers.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-kickers/count")
    public ResponseEntity<Long> countMGachaRenditionKickers(MGachaRenditionKickerCriteria criteria) {
        log.debug("REST request to count MGachaRenditionKickers by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionKickerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-kickers/:id} : get the "id" mGachaRenditionKicker.
     *
     * @param id the id of the mGachaRenditionKickerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionKickerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-kickers/{id}")
    public ResponseEntity<MGachaRenditionKickerDTO> getMGachaRenditionKicker(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionKicker : {}", id);
        Optional<MGachaRenditionKickerDTO> mGachaRenditionKickerDTO = mGachaRenditionKickerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionKickerDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-kickers/:id} : delete the "id" mGachaRenditionKicker.
     *
     * @param id the id of the mGachaRenditionKickerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-kickers/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionKicker(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionKicker : {}", id);
        mGachaRenditionKickerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
