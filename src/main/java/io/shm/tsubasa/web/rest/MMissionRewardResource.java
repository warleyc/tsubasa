package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMissionRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMissionRewardDTO;
import io.shm.tsubasa.service.dto.MMissionRewardCriteria;
import io.shm.tsubasa.service.MMissionRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMissionReward}.
 */
@RestController
@RequestMapping("/api")
public class MMissionRewardResource {

    private final Logger log = LoggerFactory.getLogger(MMissionRewardResource.class);

    private static final String ENTITY_NAME = "mMissionReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMissionRewardService mMissionRewardService;

    private final MMissionRewardQueryService mMissionRewardQueryService;

    public MMissionRewardResource(MMissionRewardService mMissionRewardService, MMissionRewardQueryService mMissionRewardQueryService) {
        this.mMissionRewardService = mMissionRewardService;
        this.mMissionRewardQueryService = mMissionRewardQueryService;
    }

    /**
     * {@code POST  /m-mission-rewards} : Create a new mMissionReward.
     *
     * @param mMissionRewardDTO the mMissionRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMissionRewardDTO, or with status {@code 400 (Bad Request)} if the mMissionReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-mission-rewards")
    public ResponseEntity<MMissionRewardDTO> createMMissionReward(@Valid @RequestBody MMissionRewardDTO mMissionRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MMissionReward : {}", mMissionRewardDTO);
        if (mMissionRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMissionReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMissionRewardDTO result = mMissionRewardService.save(mMissionRewardDTO);
        return ResponseEntity.created(new URI("/api/m-mission-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-mission-rewards} : Updates an existing mMissionReward.
     *
     * @param mMissionRewardDTO the mMissionRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMissionRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mMissionRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMissionRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-mission-rewards")
    public ResponseEntity<MMissionRewardDTO> updateMMissionReward(@Valid @RequestBody MMissionRewardDTO mMissionRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MMissionReward : {}", mMissionRewardDTO);
        if (mMissionRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMissionRewardDTO result = mMissionRewardService.save(mMissionRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMissionRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-mission-rewards} : get all the mMissionRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMissionRewards in body.
     */
    @GetMapping("/m-mission-rewards")
    public ResponseEntity<List<MMissionRewardDTO>> getAllMMissionRewards(MMissionRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMissionRewards by criteria: {}", criteria);
        Page<MMissionRewardDTO> page = mMissionRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-mission-rewards/count} : count all the mMissionRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-mission-rewards/count")
    public ResponseEntity<Long> countMMissionRewards(MMissionRewardCriteria criteria) {
        log.debug("REST request to count MMissionRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMissionRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-mission-rewards/:id} : get the "id" mMissionReward.
     *
     * @param id the id of the mMissionRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMissionRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-mission-rewards/{id}")
    public ResponseEntity<MMissionRewardDTO> getMMissionReward(@PathVariable Long id) {
        log.debug("REST request to get MMissionReward : {}", id);
        Optional<MMissionRewardDTO> mMissionRewardDTO = mMissionRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMissionRewardDTO);
    }

    /**
     * {@code DELETE  /m-mission-rewards/:id} : delete the "id" mMissionReward.
     *
     * @param id the id of the mMissionRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-mission-rewards/{id}")
    public ResponseEntity<Void> deleteMMissionReward(@PathVariable Long id) {
        log.debug("REST request to delete MMissionReward : {}", id);
        mMissionRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
