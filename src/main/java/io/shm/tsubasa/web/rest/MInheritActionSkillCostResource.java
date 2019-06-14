package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MInheritActionSkillCostService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MInheritActionSkillCostDTO;
import io.shm.tsubasa.service.dto.MInheritActionSkillCostCriteria;
import io.shm.tsubasa.service.MInheritActionSkillCostQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MInheritActionSkillCost}.
 */
@RestController
@RequestMapping("/api")
public class MInheritActionSkillCostResource {

    private final Logger log = LoggerFactory.getLogger(MInheritActionSkillCostResource.class);

    private static final String ENTITY_NAME = "mInheritActionSkillCost";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MInheritActionSkillCostService mInheritActionSkillCostService;

    private final MInheritActionSkillCostQueryService mInheritActionSkillCostQueryService;

    public MInheritActionSkillCostResource(MInheritActionSkillCostService mInheritActionSkillCostService, MInheritActionSkillCostQueryService mInheritActionSkillCostQueryService) {
        this.mInheritActionSkillCostService = mInheritActionSkillCostService;
        this.mInheritActionSkillCostQueryService = mInheritActionSkillCostQueryService;
    }

    /**
     * {@code POST  /m-inherit-action-skill-costs} : Create a new mInheritActionSkillCost.
     *
     * @param mInheritActionSkillCostDTO the mInheritActionSkillCostDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mInheritActionSkillCostDTO, or with status {@code 400 (Bad Request)} if the mInheritActionSkillCost has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-inherit-action-skill-costs")
    public ResponseEntity<MInheritActionSkillCostDTO> createMInheritActionSkillCost(@Valid @RequestBody MInheritActionSkillCostDTO mInheritActionSkillCostDTO) throws URISyntaxException {
        log.debug("REST request to save MInheritActionSkillCost : {}", mInheritActionSkillCostDTO);
        if (mInheritActionSkillCostDTO.getId() != null) {
            throw new BadRequestAlertException("A new mInheritActionSkillCost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MInheritActionSkillCostDTO result = mInheritActionSkillCostService.save(mInheritActionSkillCostDTO);
        return ResponseEntity.created(new URI("/api/m-inherit-action-skill-costs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-inherit-action-skill-costs} : Updates an existing mInheritActionSkillCost.
     *
     * @param mInheritActionSkillCostDTO the mInheritActionSkillCostDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mInheritActionSkillCostDTO,
     * or with status {@code 400 (Bad Request)} if the mInheritActionSkillCostDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mInheritActionSkillCostDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-inherit-action-skill-costs")
    public ResponseEntity<MInheritActionSkillCostDTO> updateMInheritActionSkillCost(@Valid @RequestBody MInheritActionSkillCostDTO mInheritActionSkillCostDTO) throws URISyntaxException {
        log.debug("REST request to update MInheritActionSkillCost : {}", mInheritActionSkillCostDTO);
        if (mInheritActionSkillCostDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MInheritActionSkillCostDTO result = mInheritActionSkillCostService.save(mInheritActionSkillCostDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mInheritActionSkillCostDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-inherit-action-skill-costs} : get all the mInheritActionSkillCosts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mInheritActionSkillCosts in body.
     */
    @GetMapping("/m-inherit-action-skill-costs")
    public ResponseEntity<List<MInheritActionSkillCostDTO>> getAllMInheritActionSkillCosts(MInheritActionSkillCostCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MInheritActionSkillCosts by criteria: {}", criteria);
        Page<MInheritActionSkillCostDTO> page = mInheritActionSkillCostQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-inherit-action-skill-costs/count} : count all the mInheritActionSkillCosts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-inherit-action-skill-costs/count")
    public ResponseEntity<Long> countMInheritActionSkillCosts(MInheritActionSkillCostCriteria criteria) {
        log.debug("REST request to count MInheritActionSkillCosts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mInheritActionSkillCostQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-inherit-action-skill-costs/:id} : get the "id" mInheritActionSkillCost.
     *
     * @param id the id of the mInheritActionSkillCostDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mInheritActionSkillCostDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-inherit-action-skill-costs/{id}")
    public ResponseEntity<MInheritActionSkillCostDTO> getMInheritActionSkillCost(@PathVariable Long id) {
        log.debug("REST request to get MInheritActionSkillCost : {}", id);
        Optional<MInheritActionSkillCostDTO> mInheritActionSkillCostDTO = mInheritActionSkillCostService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mInheritActionSkillCostDTO);
    }

    /**
     * {@code DELETE  /m-inherit-action-skill-costs/:id} : delete the "id" mInheritActionSkillCost.
     *
     * @param id the id of the mInheritActionSkillCostDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-inherit-action-skill-costs/{id}")
    public ResponseEntity<Void> deleteMInheritActionSkillCost(@PathVariable Long id) {
        log.debug("REST request to delete MInheritActionSkillCost : {}", id);
        mInheritActionSkillCostService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
