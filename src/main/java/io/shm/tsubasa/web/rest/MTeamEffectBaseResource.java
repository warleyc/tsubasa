package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTeamEffectBaseService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTeamEffectBaseDTO;
import io.shm.tsubasa.service.dto.MTeamEffectBaseCriteria;
import io.shm.tsubasa.service.MTeamEffectBaseQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTeamEffectBase}.
 */
@RestController
@RequestMapping("/api")
public class MTeamEffectBaseResource {

    private final Logger log = LoggerFactory.getLogger(MTeamEffectBaseResource.class);

    private static final String ENTITY_NAME = "mTeamEffectBase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTeamEffectBaseService mTeamEffectBaseService;

    private final MTeamEffectBaseQueryService mTeamEffectBaseQueryService;

    public MTeamEffectBaseResource(MTeamEffectBaseService mTeamEffectBaseService, MTeamEffectBaseQueryService mTeamEffectBaseQueryService) {
        this.mTeamEffectBaseService = mTeamEffectBaseService;
        this.mTeamEffectBaseQueryService = mTeamEffectBaseQueryService;
    }

    /**
     * {@code POST  /m-team-effect-bases} : Create a new mTeamEffectBase.
     *
     * @param mTeamEffectBaseDTO the mTeamEffectBaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTeamEffectBaseDTO, or with status {@code 400 (Bad Request)} if the mTeamEffectBase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-team-effect-bases")
    public ResponseEntity<MTeamEffectBaseDTO> createMTeamEffectBase(@Valid @RequestBody MTeamEffectBaseDTO mTeamEffectBaseDTO) throws URISyntaxException {
        log.debug("REST request to save MTeamEffectBase : {}", mTeamEffectBaseDTO);
        if (mTeamEffectBaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTeamEffectBase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTeamEffectBaseDTO result = mTeamEffectBaseService.save(mTeamEffectBaseDTO);
        return ResponseEntity.created(new URI("/api/m-team-effect-bases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-team-effect-bases} : Updates an existing mTeamEffectBase.
     *
     * @param mTeamEffectBaseDTO the mTeamEffectBaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTeamEffectBaseDTO,
     * or with status {@code 400 (Bad Request)} if the mTeamEffectBaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTeamEffectBaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-team-effect-bases")
    public ResponseEntity<MTeamEffectBaseDTO> updateMTeamEffectBase(@Valid @RequestBody MTeamEffectBaseDTO mTeamEffectBaseDTO) throws URISyntaxException {
        log.debug("REST request to update MTeamEffectBase : {}", mTeamEffectBaseDTO);
        if (mTeamEffectBaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTeamEffectBaseDTO result = mTeamEffectBaseService.save(mTeamEffectBaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTeamEffectBaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-team-effect-bases} : get all the mTeamEffectBases.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTeamEffectBases in body.
     */
    @GetMapping("/m-team-effect-bases")
    public ResponseEntity<List<MTeamEffectBaseDTO>> getAllMTeamEffectBases(MTeamEffectBaseCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTeamEffectBases by criteria: {}", criteria);
        Page<MTeamEffectBaseDTO> page = mTeamEffectBaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-team-effect-bases/count} : count all the mTeamEffectBases.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-team-effect-bases/count")
    public ResponseEntity<Long> countMTeamEffectBases(MTeamEffectBaseCriteria criteria) {
        log.debug("REST request to count MTeamEffectBases by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTeamEffectBaseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-team-effect-bases/:id} : get the "id" mTeamEffectBase.
     *
     * @param id the id of the mTeamEffectBaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTeamEffectBaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-team-effect-bases/{id}")
    public ResponseEntity<MTeamEffectBaseDTO> getMTeamEffectBase(@PathVariable Long id) {
        log.debug("REST request to get MTeamEffectBase : {}", id);
        Optional<MTeamEffectBaseDTO> mTeamEffectBaseDTO = mTeamEffectBaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTeamEffectBaseDTO);
    }

    /**
     * {@code DELETE  /m-team-effect-bases/:id} : delete the "id" mTeamEffectBase.
     *
     * @param id the id of the mTeamEffectBaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-team-effect-bases/{id}")
    public ResponseEntity<Void> deleteMTeamEffectBase(@PathVariable Long id) {
        log.debug("REST request to delete MTeamEffectBase : {}", id);
        mTeamEffectBaseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
