package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MDetachActionSkillCostService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MDetachActionSkillCostDTO;
import io.shm.tsubasa.service.dto.MDetachActionSkillCostCriteria;
import io.shm.tsubasa.service.MDetachActionSkillCostQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MDetachActionSkillCost}.
 */
@RestController
@RequestMapping("/api")
public class MDetachActionSkillCostResource {

    private final Logger log = LoggerFactory.getLogger(MDetachActionSkillCostResource.class);

    private static final String ENTITY_NAME = "mDetachActionSkillCost";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MDetachActionSkillCostService mDetachActionSkillCostService;

    private final MDetachActionSkillCostQueryService mDetachActionSkillCostQueryService;

    public MDetachActionSkillCostResource(MDetachActionSkillCostService mDetachActionSkillCostService, MDetachActionSkillCostQueryService mDetachActionSkillCostQueryService) {
        this.mDetachActionSkillCostService = mDetachActionSkillCostService;
        this.mDetachActionSkillCostQueryService = mDetachActionSkillCostQueryService;
    }

    /**
     * {@code POST  /m-detach-action-skill-costs} : Create a new mDetachActionSkillCost.
     *
     * @param mDetachActionSkillCostDTO the mDetachActionSkillCostDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mDetachActionSkillCostDTO, or with status {@code 400 (Bad Request)} if the mDetachActionSkillCost has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-detach-action-skill-costs")
    public ResponseEntity<MDetachActionSkillCostDTO> createMDetachActionSkillCost(@Valid @RequestBody MDetachActionSkillCostDTO mDetachActionSkillCostDTO) throws URISyntaxException {
        log.debug("REST request to save MDetachActionSkillCost : {}", mDetachActionSkillCostDTO);
        if (mDetachActionSkillCostDTO.getId() != null) {
            throw new BadRequestAlertException("A new mDetachActionSkillCost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MDetachActionSkillCostDTO result = mDetachActionSkillCostService.save(mDetachActionSkillCostDTO);
        return ResponseEntity.created(new URI("/api/m-detach-action-skill-costs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-detach-action-skill-costs} : Updates an existing mDetachActionSkillCost.
     *
     * @param mDetachActionSkillCostDTO the mDetachActionSkillCostDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mDetachActionSkillCostDTO,
     * or with status {@code 400 (Bad Request)} if the mDetachActionSkillCostDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mDetachActionSkillCostDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-detach-action-skill-costs")
    public ResponseEntity<MDetachActionSkillCostDTO> updateMDetachActionSkillCost(@Valid @RequestBody MDetachActionSkillCostDTO mDetachActionSkillCostDTO) throws URISyntaxException {
        log.debug("REST request to update MDetachActionSkillCost : {}", mDetachActionSkillCostDTO);
        if (mDetachActionSkillCostDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MDetachActionSkillCostDTO result = mDetachActionSkillCostService.save(mDetachActionSkillCostDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mDetachActionSkillCostDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-detach-action-skill-costs} : get all the mDetachActionSkillCosts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mDetachActionSkillCosts in body.
     */
    @GetMapping("/m-detach-action-skill-costs")
    public ResponseEntity<List<MDetachActionSkillCostDTO>> getAllMDetachActionSkillCosts(MDetachActionSkillCostCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MDetachActionSkillCosts by criteria: {}", criteria);
        Page<MDetachActionSkillCostDTO> page = mDetachActionSkillCostQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-detach-action-skill-costs/count} : count all the mDetachActionSkillCosts.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-detach-action-skill-costs/count")
    public ResponseEntity<Long> countMDetachActionSkillCosts(MDetachActionSkillCostCriteria criteria) {
        log.debug("REST request to count MDetachActionSkillCosts by criteria: {}", criteria);
        return ResponseEntity.ok().body(mDetachActionSkillCostQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-detach-action-skill-costs/:id} : get the "id" mDetachActionSkillCost.
     *
     * @param id the id of the mDetachActionSkillCostDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mDetachActionSkillCostDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-detach-action-skill-costs/{id}")
    public ResponseEntity<MDetachActionSkillCostDTO> getMDetachActionSkillCost(@PathVariable Long id) {
        log.debug("REST request to get MDetachActionSkillCost : {}", id);
        Optional<MDetachActionSkillCostDTO> mDetachActionSkillCostDTO = mDetachActionSkillCostService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mDetachActionSkillCostDTO);
    }

    /**
     * {@code DELETE  /m-detach-action-skill-costs/:id} : delete the "id" mDetachActionSkillCost.
     *
     * @param id the id of the mDetachActionSkillCostDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-detach-action-skill-costs/{id}")
    public ResponseEntity<Void> deleteMDetachActionSkillCost(@PathVariable Long id) {
        log.debug("REST request to delete MDetachActionSkillCost : {}", id);
        mDetachActionSkillCostService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
