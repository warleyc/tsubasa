package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MChallengeQuestAchievementRewardService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardDTO;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardCriteria;
import io.shm.tsubasa.service.MChallengeQuestAchievementRewardQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MChallengeQuestAchievementReward}.
 */
@RestController
@RequestMapping("/api")
public class MChallengeQuestAchievementRewardResource {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestAchievementRewardResource.class);

    private static final String ENTITY_NAME = "mChallengeQuestAchievementReward";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MChallengeQuestAchievementRewardService mChallengeQuestAchievementRewardService;

    private final MChallengeQuestAchievementRewardQueryService mChallengeQuestAchievementRewardQueryService;

    public MChallengeQuestAchievementRewardResource(MChallengeQuestAchievementRewardService mChallengeQuestAchievementRewardService, MChallengeQuestAchievementRewardQueryService mChallengeQuestAchievementRewardQueryService) {
        this.mChallengeQuestAchievementRewardService = mChallengeQuestAchievementRewardService;
        this.mChallengeQuestAchievementRewardQueryService = mChallengeQuestAchievementRewardQueryService;
    }

    /**
     * {@code POST  /m-challenge-quest-achievement-rewards} : Create a new mChallengeQuestAchievementReward.
     *
     * @param mChallengeQuestAchievementRewardDTO the mChallengeQuestAchievementRewardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mChallengeQuestAchievementRewardDTO, or with status {@code 400 (Bad Request)} if the mChallengeQuestAchievementReward has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-challenge-quest-achievement-rewards")
    public ResponseEntity<MChallengeQuestAchievementRewardDTO> createMChallengeQuestAchievementReward(@Valid @RequestBody MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO) throws URISyntaxException {
        log.debug("REST request to save MChallengeQuestAchievementReward : {}", mChallengeQuestAchievementRewardDTO);
        if (mChallengeQuestAchievementRewardDTO.getId() != null) {
            throw new BadRequestAlertException("A new mChallengeQuestAchievementReward cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MChallengeQuestAchievementRewardDTO result = mChallengeQuestAchievementRewardService.save(mChallengeQuestAchievementRewardDTO);
        return ResponseEntity.created(new URI("/api/m-challenge-quest-achievement-rewards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-challenge-quest-achievement-rewards} : Updates an existing mChallengeQuestAchievementReward.
     *
     * @param mChallengeQuestAchievementRewardDTO the mChallengeQuestAchievementRewardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mChallengeQuestAchievementRewardDTO,
     * or with status {@code 400 (Bad Request)} if the mChallengeQuestAchievementRewardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mChallengeQuestAchievementRewardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-challenge-quest-achievement-rewards")
    public ResponseEntity<MChallengeQuestAchievementRewardDTO> updateMChallengeQuestAchievementReward(@Valid @RequestBody MChallengeQuestAchievementRewardDTO mChallengeQuestAchievementRewardDTO) throws URISyntaxException {
        log.debug("REST request to update MChallengeQuestAchievementReward : {}", mChallengeQuestAchievementRewardDTO);
        if (mChallengeQuestAchievementRewardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MChallengeQuestAchievementRewardDTO result = mChallengeQuestAchievementRewardService.save(mChallengeQuestAchievementRewardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mChallengeQuestAchievementRewardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-challenge-quest-achievement-rewards} : get all the mChallengeQuestAchievementRewards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mChallengeQuestAchievementRewards in body.
     */
    @GetMapping("/m-challenge-quest-achievement-rewards")
    public ResponseEntity<List<MChallengeQuestAchievementRewardDTO>> getAllMChallengeQuestAchievementRewards(MChallengeQuestAchievementRewardCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MChallengeQuestAchievementRewards by criteria: {}", criteria);
        Page<MChallengeQuestAchievementRewardDTO> page = mChallengeQuestAchievementRewardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-challenge-quest-achievement-rewards/count} : count all the mChallengeQuestAchievementRewards.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-challenge-quest-achievement-rewards/count")
    public ResponseEntity<Long> countMChallengeQuestAchievementRewards(MChallengeQuestAchievementRewardCriteria criteria) {
        log.debug("REST request to count MChallengeQuestAchievementRewards by criteria: {}", criteria);
        return ResponseEntity.ok().body(mChallengeQuestAchievementRewardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-challenge-quest-achievement-rewards/:id} : get the "id" mChallengeQuestAchievementReward.
     *
     * @param id the id of the mChallengeQuestAchievementRewardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mChallengeQuestAchievementRewardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-challenge-quest-achievement-rewards/{id}")
    public ResponseEntity<MChallengeQuestAchievementRewardDTO> getMChallengeQuestAchievementReward(@PathVariable Long id) {
        log.debug("REST request to get MChallengeQuestAchievementReward : {}", id);
        Optional<MChallengeQuestAchievementRewardDTO> mChallengeQuestAchievementRewardDTO = mChallengeQuestAchievementRewardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mChallengeQuestAchievementRewardDTO);
    }

    /**
     * {@code DELETE  /m-challenge-quest-achievement-rewards/:id} : delete the "id" mChallengeQuestAchievementReward.
     *
     * @param id the id of the mChallengeQuestAchievementRewardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-challenge-quest-achievement-rewards/{id}")
    public ResponseEntity<Void> deleteMChallengeQuestAchievementReward(@PathVariable Long id) {
        log.debug("REST request to delete MChallengeQuestAchievementReward : {}", id);
        mChallengeQuestAchievementRewardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
