package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTriggerEffectBaseService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTriggerEffectBaseDTO;
import io.shm.tsubasa.service.dto.MTriggerEffectBaseCriteria;
import io.shm.tsubasa.service.MTriggerEffectBaseQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTriggerEffectBase}.
 */
@RestController
@RequestMapping("/api")
public class MTriggerEffectBaseResource {

    private final Logger log = LoggerFactory.getLogger(MTriggerEffectBaseResource.class);

    private static final String ENTITY_NAME = "mTriggerEffectBase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTriggerEffectBaseService mTriggerEffectBaseService;

    private final MTriggerEffectBaseQueryService mTriggerEffectBaseQueryService;

    public MTriggerEffectBaseResource(MTriggerEffectBaseService mTriggerEffectBaseService, MTriggerEffectBaseQueryService mTriggerEffectBaseQueryService) {
        this.mTriggerEffectBaseService = mTriggerEffectBaseService;
        this.mTriggerEffectBaseQueryService = mTriggerEffectBaseQueryService;
    }

    /**
     * {@code POST  /m-trigger-effect-bases} : Create a new mTriggerEffectBase.
     *
     * @param mTriggerEffectBaseDTO the mTriggerEffectBaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTriggerEffectBaseDTO, or with status {@code 400 (Bad Request)} if the mTriggerEffectBase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-trigger-effect-bases")
    public ResponseEntity<MTriggerEffectBaseDTO> createMTriggerEffectBase(@Valid @RequestBody MTriggerEffectBaseDTO mTriggerEffectBaseDTO) throws URISyntaxException {
        log.debug("REST request to save MTriggerEffectBase : {}", mTriggerEffectBaseDTO);
        if (mTriggerEffectBaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTriggerEffectBase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTriggerEffectBaseDTO result = mTriggerEffectBaseService.save(mTriggerEffectBaseDTO);
        return ResponseEntity.created(new URI("/api/m-trigger-effect-bases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-trigger-effect-bases} : Updates an existing mTriggerEffectBase.
     *
     * @param mTriggerEffectBaseDTO the mTriggerEffectBaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTriggerEffectBaseDTO,
     * or with status {@code 400 (Bad Request)} if the mTriggerEffectBaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTriggerEffectBaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-trigger-effect-bases")
    public ResponseEntity<MTriggerEffectBaseDTO> updateMTriggerEffectBase(@Valid @RequestBody MTriggerEffectBaseDTO mTriggerEffectBaseDTO) throws URISyntaxException {
        log.debug("REST request to update MTriggerEffectBase : {}", mTriggerEffectBaseDTO);
        if (mTriggerEffectBaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTriggerEffectBaseDTO result = mTriggerEffectBaseService.save(mTriggerEffectBaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTriggerEffectBaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-trigger-effect-bases} : get all the mTriggerEffectBases.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTriggerEffectBases in body.
     */
    @GetMapping("/m-trigger-effect-bases")
    public ResponseEntity<List<MTriggerEffectBaseDTO>> getAllMTriggerEffectBases(MTriggerEffectBaseCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTriggerEffectBases by criteria: {}", criteria);
        Page<MTriggerEffectBaseDTO> page = mTriggerEffectBaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-trigger-effect-bases/count} : count all the mTriggerEffectBases.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-trigger-effect-bases/count")
    public ResponseEntity<Long> countMTriggerEffectBases(MTriggerEffectBaseCriteria criteria) {
        log.debug("REST request to count MTriggerEffectBases by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTriggerEffectBaseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-trigger-effect-bases/:id} : get the "id" mTriggerEffectBase.
     *
     * @param id the id of the mTriggerEffectBaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTriggerEffectBaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-trigger-effect-bases/{id}")
    public ResponseEntity<MTriggerEffectBaseDTO> getMTriggerEffectBase(@PathVariable Long id) {
        log.debug("REST request to get MTriggerEffectBase : {}", id);
        Optional<MTriggerEffectBaseDTO> mTriggerEffectBaseDTO = mTriggerEffectBaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTriggerEffectBaseDTO);
    }

    /**
     * {@code DELETE  /m-trigger-effect-bases/:id} : delete the "id" mTriggerEffectBase.
     *
     * @param id the id of the mTriggerEffectBaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-trigger-effect-bases/{id}")
    public ResponseEntity<Void> deleteMTriggerEffectBase(@PathVariable Long id) {
        log.debug("REST request to delete MTriggerEffectBase : {}", id);
        mTriggerEffectBaseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
