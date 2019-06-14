package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMarathonAchievementRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardDTO;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardCriteria;
import io.shm.tsubasa.service.MMarathonAchievementRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMarathonAchievementReward}.
 */
@RestController
@RequestMapping("/api")
public class MMarathonAchievementRewardResource {

    private final Logger log = LoggerFactory.getLogger(MMarathonAchievementRewardResource.class);

    private static final String ENTITY_NAME = "mMarathonAchievementReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMarathonAchievementRewardService mMarathonAchievementRewardService;

    private final MMarathonAchievementRewardQueryService mMarathonAchievementRewardQueryService;

    public MMarathonAchievementRewardResource(MMarathonAchievementRewardService mMarathonAchievementRewardService, MMarathonAchievementRewardQueryService mMarathonAchievementRewardQueryService) {
        this.mMarathonAchievementRewardService = mMarathonAchievementRewardService;
        this.mMarathonAchievementRewardQueryService = mMarathonAchievementRewardQueryService;
    }

    /**
     * {@code POST  /m-marathon-achievement-rewards} : Create a new mMarathonAchievementReward.
     *
     * @param mMarathonAchievementRewardDTO the mMarathonAchievementRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMarathonAchievementRewardDTO, or with status {@code 400 (Bad Request)} if the mMarathonAchievementReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-marathon-achievement-rewards")
    public ResponseEntity<MMarathonAchievementRewardDTO> createMMarathonAchievementReward(@Valid @RequestBody MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MMarathonAchievementReward : {}", mMarathonAchievementRewardDTO);
        if (mMarathonAchievementRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMarathonAchievementReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMarathonAchievementRewardDTO result = mMarathonAchievementRewardService.save(mMarathonAchievementRewardDTO);
        return ResponseEntity.created(new URI("/api/m-marathon-achievement-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-marathon-achievement-rewards} : Updates an existing mMarathonAchievementReward.
     *
     * @param mMarathonAchievementRewardDTO the mMarathonAchievementRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMarathonAchievementRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mMarathonAchievementRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMarathonAchievementRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-marathon-achievement-rewards")
    public ResponseEntity<MMarathonAchievementRewardDTO> updateMMarathonAchievementReward(@Valid @RequestBody MMarathonAchievementRewardDTO mMarathonAchievementRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MMarathonAchievementReward : {}", mMarathonAchievementRewardDTO);
        if (mMarathonAchievementRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMarathonAchievementRewardDTO result = mMarathonAchievementRewardService.save(mMarathonAchievementRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMarathonAchievementRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-marathon-achievement-rewards} : get all the mMarathonAchievementRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMarathonAchievementRewards in body.
     */
    @GetMapping("/m-marathon-achievement-rewards")
    public ResponseEntity<List<MMarathonAchievementRewardDTO>> getAllMMarathonAchievementRewards(MMarathonAchievementRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMarathonAchievementRewards by criteria: {}", criteria);
        Page<MMarathonAchievementRewardDTO> page = mMarathonAchievementRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-marathon-achievement-rewards/count} : count all the mMarathonAchievementRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-marathon-achievement-rewards/count")
    public ResponseEntity<Long> countMMarathonAchievementRewards(MMarathonAchievementRewardCriteria criteria) {
        log.debug("REST request to count MMarathonAchievementRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMarathonAchievementRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-marathon-achievement-rewards/:id} : get the "id" mMarathonAchievementReward.
     *
     * @param id the id of the mMarathonAchievementRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMarathonAchievementRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-marathon-achievement-rewards/{id}")
    public ResponseEntity<MMarathonAchievementRewardDTO> getMMarathonAchievementReward(@PathVariable Long id) {
        log.debug("REST request to get MMarathonAchievementReward : {}", id);
        Optional<MMarathonAchievementRewardDTO> mMarathonAchievementRewardDTO = mMarathonAchievementRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMarathonAchievementRewardDTO);
    }

    /**
     * {@code DELETE  /m-marathon-achievement-rewards/:id} : delete the "id" mMarathonAchievementReward.
     *
     * @param id the id of the mMarathonAchievementRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-marathon-achievement-rewards/{id}")
    public ResponseEntity<Void> deleteMMarathonAchievementReward(@PathVariable Long id) {
        log.debug("REST request to delete MMarathonAchievementReward : {}", id);
        mMarathonAchievementRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
