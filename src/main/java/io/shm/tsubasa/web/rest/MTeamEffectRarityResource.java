package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTeamEffectRarityService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTeamEffectRarityDTO;
import io.shm.tsubasa.service.dto.MTeamEffectRarityCriteria;
import io.shm.tsubasa.service.MTeamEffectRarityQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTeamEffectRarity}.
 */
@RestController
@RequestMapping("/api")
public class MTeamEffectRarityResource {

    private final Logger log = LoggerFactory.getLogger(MTeamEffectRarityResource.class);

    private static final String ENTITY_NAME = "mTeamEffectRarity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTeamEffectRarityService mTeamEffectRarityService;

    private final MTeamEffectRarityQueryService mTeamEffectRarityQueryService;

    public MTeamEffectRarityResource(MTeamEffectRarityService mTeamEffectRarityService, MTeamEffectRarityQueryService mTeamEffectRarityQueryService) {
        this.mTeamEffectRarityService = mTeamEffectRarityService;
        this.mTeamEffectRarityQueryService = mTeamEffectRarityQueryService;
    }

    /**
     * {@code POST  /m-team-effect-rarities} : Create a new mTeamEffectRarity.
     *
     * @param mTeamEffectRarityDTO the mTeamEffectRarityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTeamEffectRarityDTO, or with status {@code 400 (Bad Request)} if the mTeamEffectRarity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-team-effect-rarities")
    public ResponseEntity<MTeamEffectRarityDTO> createMTeamEffectRarity(@Valid @RequestBody MTeamEffectRarityDTO mTeamEffectRarityDTO) throws URISyntaxException {
        log.debug("REST request to save MTeamEffectRarity : {}", mTeamEffectRarityDTO);
        if (mTeamEffectRarityDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTeamEffectRarity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTeamEffectRarityDTO result = mTeamEffectRarityService.save(mTeamEffectRarityDTO);
        return ResponseEntity.created(new URI("/api/m-team-effect-rarities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-team-effect-rarities} : Updates an existing mTeamEffectRarity.
     *
     * @param mTeamEffectRarityDTO the mTeamEffectRarityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTeamEffectRarityDTO,
     * or with status {@code 400 (Bad Request)} if the mTeamEffectRarityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTeamEffectRarityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-team-effect-rarities")
    public ResponseEntity<MTeamEffectRarityDTO> updateMTeamEffectRarity(@Valid @RequestBody MTeamEffectRarityDTO mTeamEffectRarityDTO) throws URISyntaxException {
        log.debug("REST request to update MTeamEffectRarity : {}", mTeamEffectRarityDTO);
        if (mTeamEffectRarityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTeamEffectRarityDTO result = mTeamEffectRarityService.save(mTeamEffectRarityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTeamEffectRarityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-team-effect-rarities} : get all the mTeamEffectRarities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTeamEffectRarities in body.
     */
    @GetMapping("/m-team-effect-rarities")
    public ResponseEntity<List<MTeamEffectRarityDTO>> getAllMTeamEffectRarities(MTeamEffectRarityCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTeamEffectRarities by criteria: {}", criteria);
        Page<MTeamEffectRarityDTO> page = mTeamEffectRarityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-team-effect-rarities/count} : count all the mTeamEffectRarities.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-team-effect-rarities/count")
    public ResponseEntity<Long> countMTeamEffectRarities(MTeamEffectRarityCriteria criteria) {
        log.debug("REST request to count MTeamEffectRarities by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTeamEffectRarityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-team-effect-rarities/:id} : get the "id" mTeamEffectRarity.
     *
     * @param id the id of the mTeamEffectRarityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTeamEffectRarityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-team-effect-rarities/{id}")
    public ResponseEntity<MTeamEffectRarityDTO> getMTeamEffectRarity(@PathVariable Long id) {
        log.debug("REST request to get MTeamEffectRarity : {}", id);
        Optional<MTeamEffectRarityDTO> mTeamEffectRarityDTO = mTeamEffectRarityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTeamEffectRarityDTO);
    }

    /**
     * {@code DELETE  /m-team-effect-rarities/:id} : delete the "id" mTeamEffectRarity.
     *
     * @param id the id of the mTeamEffectRarityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-team-effect-rarities/{id}")
    public ResponseEntity<Void> deleteMTeamEffectRarity(@PathVariable Long id) {
        log.debug("REST request to delete MTeamEffectRarity : {}", id);
        mTeamEffectRarityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
