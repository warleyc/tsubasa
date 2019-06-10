package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionFirstGimmickService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionFirstGimmickDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionFirstGimmickCriteria;
import io.shm.tsubasa.service.MGachaRenditionFirstGimmickQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionFirstGimmick}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionFirstGimmickResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionFirstGimmickResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionFirstGimmick";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionFirstGimmickService mGachaRenditionFirstGimmickService;

    private final MGachaRenditionFirstGimmickQueryService mGachaRenditionFirstGimmickQueryService;

    public MGachaRenditionFirstGimmickResource(MGachaRenditionFirstGimmickService mGachaRenditionFirstGimmickService, MGachaRenditionFirstGimmickQueryService mGachaRenditionFirstGimmickQueryService) {
        this.mGachaRenditionFirstGimmickService = mGachaRenditionFirstGimmickService;
        this.mGachaRenditionFirstGimmickQueryService = mGachaRenditionFirstGimmickQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-first-gimmicks} : Create a new mGachaRenditionFirstGimmick.
     *
     * @param mGachaRenditionFirstGimmickDTO the mGachaRenditionFirstGimmickDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionFirstGimmickDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionFirstGimmick has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-first-gimmicks")
    public ResponseEntity<MGachaRenditionFirstGimmickDTO> createMGachaRenditionFirstGimmick(@Valid @RequestBody MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionFirstGimmick : {}", mGachaRenditionFirstGimmickDTO);
        if (mGachaRenditionFirstGimmickDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionFirstGimmick cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionFirstGimmickDTO result = mGachaRenditionFirstGimmickService.save(mGachaRenditionFirstGimmickDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-first-gimmicks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-first-gimmicks} : Updates an existing mGachaRenditionFirstGimmick.
     *
     * @param mGachaRenditionFirstGimmickDTO the mGachaRenditionFirstGimmickDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionFirstGimmickDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionFirstGimmickDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionFirstGimmickDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-first-gimmicks")
    public ResponseEntity<MGachaRenditionFirstGimmickDTO> updateMGachaRenditionFirstGimmick(@Valid @RequestBody MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionFirstGimmick : {}", mGachaRenditionFirstGimmickDTO);
        if (mGachaRenditionFirstGimmickDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionFirstGimmickDTO result = mGachaRenditionFirstGimmickService.save(mGachaRenditionFirstGimmickDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionFirstGimmickDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-first-gimmicks} : get all the mGachaRenditionFirstGimmicks.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionFirstGimmicks in body.
     */
    @GetMapping("/m-gacha-rendition-first-gimmicks")
    public ResponseEntity<List<MGachaRenditionFirstGimmickDTO>> getAllMGachaRenditionFirstGimmicks(MGachaRenditionFirstGimmickCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionFirstGimmicks by criteria: {}", criteria);
        Page<MGachaRenditionFirstGimmickDTO> page = mGachaRenditionFirstGimmickQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-first-gimmicks/count} : count all the mGachaRenditionFirstGimmicks.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-first-gimmicks/count")
    public ResponseEntity<Long> countMGachaRenditionFirstGimmicks(MGachaRenditionFirstGimmickCriteria criteria) {
        log.debug("REST request to count MGachaRenditionFirstGimmicks by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionFirstGimmickQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-first-gimmicks/:id} : get the "id" mGachaRenditionFirstGimmick.
     *
     * @param id the id of the mGachaRenditionFirstGimmickDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionFirstGimmickDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-first-gimmicks/{id}")
    public ResponseEntity<MGachaRenditionFirstGimmickDTO> getMGachaRenditionFirstGimmick(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionFirstGimmick : {}", id);
        Optional<MGachaRenditionFirstGimmickDTO> mGachaRenditionFirstGimmickDTO = mGachaRenditionFirstGimmickService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionFirstGimmickDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-first-gimmicks/:id} : delete the "id" mGachaRenditionFirstGimmick.
     *
     * @param id the id of the mGachaRenditionFirstGimmickDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-first-gimmicks/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionFirstGimmick(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionFirstGimmick : {}", id);
        mGachaRenditionFirstGimmickService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
