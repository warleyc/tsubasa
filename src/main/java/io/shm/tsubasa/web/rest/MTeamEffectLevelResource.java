package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTeamEffectLevelService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTeamEffectLevelDTO;
import io.shm.tsubasa.service.dto.MTeamEffectLevelCriteria;
import io.shm.tsubasa.service.MTeamEffectLevelQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTeamEffectLevel}.
 */
@RestController
@RequestMapping("/api")
public class MTeamEffectLevelResource {

    private final Logger log = LoggerFactory.getLogger(MTeamEffectLevelResource.class);

    private static final String ENTITY_NAME = "mTeamEffectLevel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTeamEffectLevelService mTeamEffectLevelService;

    private final MTeamEffectLevelQueryService mTeamEffectLevelQueryService;

    public MTeamEffectLevelResource(MTeamEffectLevelService mTeamEffectLevelService, MTeamEffectLevelQueryService mTeamEffectLevelQueryService) {
        this.mTeamEffectLevelService = mTeamEffectLevelService;
        this.mTeamEffectLevelQueryService = mTeamEffectLevelQueryService;
    }

    /**
     * {@code POST  /m-team-effect-levels} : Create a new mTeamEffectLevel.
     *
     * @param mTeamEffectLevelDTO the mTeamEffectLevelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTeamEffectLevelDTO, or with status {@code 400 (Bad Request)} if the mTeamEffectLevel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-team-effect-levels")
    public ResponseEntity<MTeamEffectLevelDTO> createMTeamEffectLevel(@Valid @RequestBody MTeamEffectLevelDTO mTeamEffectLevelDTO) throws URISyntaxException {
        log.debug("REST request to save MTeamEffectLevel : {}", mTeamEffectLevelDTO);
        if (mTeamEffectLevelDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTeamEffectLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTeamEffectLevelDTO result = mTeamEffectLevelService.save(mTeamEffectLevelDTO);
        return ResponseEntity.created(new URI("/api/m-team-effect-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-team-effect-levels} : Updates an existing mTeamEffectLevel.
     *
     * @param mTeamEffectLevelDTO the mTeamEffectLevelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTeamEffectLevelDTO,
     * or with status {@code 400 (Bad Request)} if the mTeamEffectLevelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTeamEffectLevelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-team-effect-levels")
    public ResponseEntity<MTeamEffectLevelDTO> updateMTeamEffectLevel(@Valid @RequestBody MTeamEffectLevelDTO mTeamEffectLevelDTO) throws URISyntaxException {
        log.debug("REST request to update MTeamEffectLevel : {}", mTeamEffectLevelDTO);
        if (mTeamEffectLevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTeamEffectLevelDTO result = mTeamEffectLevelService.save(mTeamEffectLevelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTeamEffectLevelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-team-effect-levels} : get all the mTeamEffectLevels.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTeamEffectLevels in body.
     */
    @GetMapping("/m-team-effect-levels")
    public ResponseEntity<List<MTeamEffectLevelDTO>> getAllMTeamEffectLevels(MTeamEffectLevelCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTeamEffectLevels by criteria: {}", criteria);
        Page<MTeamEffectLevelDTO> page = mTeamEffectLevelQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-team-effect-levels/count} : count all the mTeamEffectLevels.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-team-effect-levels/count")
    public ResponseEntity<Long> countMTeamEffectLevels(MTeamEffectLevelCriteria criteria) {
        log.debug("REST request to count MTeamEffectLevels by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTeamEffectLevelQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-team-effect-levels/:id} : get the "id" mTeamEffectLevel.
     *
     * @param id the id of the mTeamEffectLevelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTeamEffectLevelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-team-effect-levels/{id}")
    public ResponseEntity<MTeamEffectLevelDTO> getMTeamEffectLevel(@PathVariable Long id) {
        log.debug("REST request to get MTeamEffectLevel : {}", id);
        Optional<MTeamEffectLevelDTO> mTeamEffectLevelDTO = mTeamEffectLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTeamEffectLevelDTO);
    }

    /**
     * {@code DELETE  /m-team-effect-levels/:id} : delete the "id" mTeamEffectLevel.
     *
     * @param id the id of the mTeamEffectLevelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-team-effect-levels/{id}")
    public ResponseEntity<Void> deleteMTeamEffectLevel(@PathVariable Long id) {
        log.debug("REST request to delete MTeamEffectLevel : {}", id);
        mTeamEffectLevelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
