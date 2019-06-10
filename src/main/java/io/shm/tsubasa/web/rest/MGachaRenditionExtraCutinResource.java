package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionExtraCutinService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionExtraCutinDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionExtraCutinCriteria;
import io.shm.tsubasa.service.MGachaRenditionExtraCutinQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionExtraCutin}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionExtraCutinResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionExtraCutinResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionExtraCutin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionExtraCutinService mGachaRenditionExtraCutinService;

    private final MGachaRenditionExtraCutinQueryService mGachaRenditionExtraCutinQueryService;

    public MGachaRenditionExtraCutinResource(MGachaRenditionExtraCutinService mGachaRenditionExtraCutinService, MGachaRenditionExtraCutinQueryService mGachaRenditionExtraCutinQueryService) {
        this.mGachaRenditionExtraCutinService = mGachaRenditionExtraCutinService;
        this.mGachaRenditionExtraCutinQueryService = mGachaRenditionExtraCutinQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-extra-cutins} : Create a new mGachaRenditionExtraCutin.
     *
     * @param mGachaRenditionExtraCutinDTO the mGachaRenditionExtraCutinDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionExtraCutinDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionExtraCutin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-extra-cutins")
    public ResponseEntity<MGachaRenditionExtraCutinDTO> createMGachaRenditionExtraCutin(@Valid @RequestBody MGachaRenditionExtraCutinDTO mGachaRenditionExtraCutinDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionExtraCutin : {}", mGachaRenditionExtraCutinDTO);
        if (mGachaRenditionExtraCutinDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionExtraCutin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionExtraCutinDTO result = mGachaRenditionExtraCutinService.save(mGachaRenditionExtraCutinDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-extra-cutins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-extra-cutins} : Updates an existing mGachaRenditionExtraCutin.
     *
     * @param mGachaRenditionExtraCutinDTO the mGachaRenditionExtraCutinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionExtraCutinDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionExtraCutinDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionExtraCutinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-extra-cutins")
    public ResponseEntity<MGachaRenditionExtraCutinDTO> updateMGachaRenditionExtraCutin(@Valid @RequestBody MGachaRenditionExtraCutinDTO mGachaRenditionExtraCutinDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionExtraCutin : {}", mGachaRenditionExtraCutinDTO);
        if (mGachaRenditionExtraCutinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionExtraCutinDTO result = mGachaRenditionExtraCutinService.save(mGachaRenditionExtraCutinDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionExtraCutinDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-extra-cutins} : get all the mGachaRenditionExtraCutins.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionExtraCutins in body.
     */
    @GetMapping("/m-gacha-rendition-extra-cutins")
    public ResponseEntity<List<MGachaRenditionExtraCutinDTO>> getAllMGachaRenditionExtraCutins(MGachaRenditionExtraCutinCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionExtraCutins by criteria: {}", criteria);
        Page<MGachaRenditionExtraCutinDTO> page = mGachaRenditionExtraCutinQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-extra-cutins/count} : count all the mGachaRenditionExtraCutins.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-extra-cutins/count")
    public ResponseEntity<Long> countMGachaRenditionExtraCutins(MGachaRenditionExtraCutinCriteria criteria) {
        log.debug("REST request to count MGachaRenditionExtraCutins by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionExtraCutinQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-extra-cutins/:id} : get the "id" mGachaRenditionExtraCutin.
     *
     * @param id the id of the mGachaRenditionExtraCutinDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionExtraCutinDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-extra-cutins/{id}")
    public ResponseEntity<MGachaRenditionExtraCutinDTO> getMGachaRenditionExtraCutin(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionExtraCutin : {}", id);
        Optional<MGachaRenditionExtraCutinDTO> mGachaRenditionExtraCutinDTO = mGachaRenditionExtraCutinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionExtraCutinDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-extra-cutins/:id} : delete the "id" mGachaRenditionExtraCutin.
     *
     * @param id the id of the mGachaRenditionExtraCutinDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-extra-cutins/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionExtraCutin(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionExtraCutin : {}", id);
        mGachaRenditionExtraCutinService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
