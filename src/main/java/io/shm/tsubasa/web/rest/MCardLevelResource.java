package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCardLevelService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCardLevelDTO;
import io.shm.tsubasa.service.dto.MCardLevelCriteria;
import io.shm.tsubasa.service.MCardLevelQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCardLevel}.
 */
@RestController
@RequestMapping("/api")
public class MCardLevelResource {

    private final Logger log = LoggerFactory.getLogger(MCardLevelResource.class);

    private static final String ENTITY_NAME = "mCardLevel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCardLevelService mCardLevelService;

    private final MCardLevelQueryService mCardLevelQueryService;

    public MCardLevelResource(MCardLevelService mCardLevelService, MCardLevelQueryService mCardLevelQueryService) {
        this.mCardLevelService = mCardLevelService;
        this.mCardLevelQueryService = mCardLevelQueryService;
    }

    /**
     * {@code POST  /m-card-levels} : Create a new mCardLevel.
     *
     * @param mCardLevelDTO the mCardLevelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCardLevelDTO, or with status {@code 400 (Bad Request)} if the mCardLevel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-card-levels")
    public ResponseEntity<MCardLevelDTO> createMCardLevel(@Valid @RequestBody MCardLevelDTO mCardLevelDTO) throws URISyntaxException {
        log.debug("REST request to save MCardLevel : {}", mCardLevelDTO);
        if (mCardLevelDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCardLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCardLevelDTO result = mCardLevelService.save(mCardLevelDTO);
        return ResponseEntity.created(new URI("/api/m-card-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-card-levels} : Updates an existing mCardLevel.
     *
     * @param mCardLevelDTO the mCardLevelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCardLevelDTO,
     * or with status {@code 400 (Bad Request)} if the mCardLevelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCardLevelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-card-levels")
    public ResponseEntity<MCardLevelDTO> updateMCardLevel(@Valid @RequestBody MCardLevelDTO mCardLevelDTO) throws URISyntaxException {
        log.debug("REST request to update MCardLevel : {}", mCardLevelDTO);
        if (mCardLevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCardLevelDTO result = mCardLevelService.save(mCardLevelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCardLevelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-card-levels} : get all the mCardLevels.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCardLevels in body.
     */
    @GetMapping("/m-card-levels")
    public ResponseEntity<List<MCardLevelDTO>> getAllMCardLevels(MCardLevelCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCardLevels by criteria: {}", criteria);
        Page<MCardLevelDTO> page = mCardLevelQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-card-levels/count} : count all the mCardLevels.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-card-levels/count")
    public ResponseEntity<Long> countMCardLevels(MCardLevelCriteria criteria) {
        log.debug("REST request to count MCardLevels by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCardLevelQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-card-levels/:id} : get the "id" mCardLevel.
     *
     * @param id the id of the mCardLevelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCardLevelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-card-levels/{id}")
    public ResponseEntity<MCardLevelDTO> getMCardLevel(@PathVariable Long id) {
        log.debug("REST request to get MCardLevel : {}", id);
        Optional<MCardLevelDTO> mCardLevelDTO = mCardLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCardLevelDTO);
    }

    /**
     * {@code DELETE  /m-card-levels/:id} : delete the "id" mCardLevel.
     *
     * @param id the id of the mCardLevelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-card-levels/{id}")
    public ResponseEntity<Void> deleteMCardLevel(@PathVariable Long id) {
        log.debug("REST request to delete MCardLevel : {}", id);
        mCardLevelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
