package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MChallengeQuestAchievementRewardGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardGroupDTO;
import io.shm.tsubasa.service.dto.MChallengeQuestAchievementRewardGroupCriteria;
import io.shm.tsubasa.service.MChallengeQuestAchievementRewardGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MChallengeQuestAchievementRewardGroup}.
 */
@RestController
@RequestMapping("/api")
public class MChallengeQuestAchievementRewardGroupResource {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestAchievementRewardGroupResource.class);

    private static final String ENTITY_NAME = "mChallengeQuestAchievementRewardGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MChallengeQuestAchievementRewardGroupService mChallengeQuestAchievementRewardGroupService;

    private final MChallengeQuestAchievementRewardGroupQueryService mChallengeQuestAchievementRewardGroupQueryService;

    public MChallengeQuestAchievementRewardGroupResource(MChallengeQuestAchievementRewardGroupService mChallengeQuestAchievementRewardGroupService, MChallengeQuestAchievementRewardGroupQueryService mChallengeQuestAchievementRewardGroupQueryService) {
        this.mChallengeQuestAchievementRewardGroupService = mChallengeQuestAchievementRewardGroupService;
        this.mChallengeQuestAchievementRewardGroupQueryService = mChallengeQuestAchievementRewardGroupQueryService;
    }

    /**
     * {@code POST  /m-challenge-quest-achievement-reward-groups} : Create a new mChallengeQuestAchievementRewardGroup.
     *
     * @param mChallengeQuestAchievementRewardGroupDTO the mChallengeQuestAchievementRewardGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mChallengeQuestAchievementRewardGroupDTO, or with status {@code 400 (Bad Request)} if the mChallengeQuestAchievementRewardGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-challenge-quest-achievement-reward-groups")
    public ResponseEntity<MChallengeQuestAchievementRewardGroupDTO> createMChallengeQuestAchievementRewardGroup(@Valid @RequestBody MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MChallengeQuestAchievementRewardGroup : {}", mChallengeQuestAchievementRewardGroupDTO);
        if (mChallengeQuestAchievementRewardGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mChallengeQuestAchievementRewardGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MChallengeQuestAchievementRewardGroupDTO result = mChallengeQuestAchievementRewardGroupService.save(mChallengeQuestAchievementRewardGroupDTO);
        return ResponseEntity.created(new URI("/api/m-challenge-quest-achievement-reward-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-challenge-quest-achievement-reward-groups} : Updates an existing mChallengeQuestAchievementRewardGroup.
     *
     * @param mChallengeQuestAchievementRewardGroupDTO the mChallengeQuestAchievementRewardGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mChallengeQuestAchievementRewardGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mChallengeQuestAchievementRewardGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mChallengeQuestAchievementRewardGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-challenge-quest-achievement-reward-groups")
    public ResponseEntity<MChallengeQuestAchievementRewardGroupDTO> updateMChallengeQuestAchievementRewardGroup(@Valid @RequestBody MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MChallengeQuestAchievementRewardGroup : {}", mChallengeQuestAchievementRewardGroupDTO);
        if (mChallengeQuestAchievementRewardGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MChallengeQuestAchievementRewardGroupDTO result = mChallengeQuestAchievementRewardGroupService.save(mChallengeQuestAchievementRewardGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mChallengeQuestAchievementRewardGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-challenge-quest-achievement-reward-groups} : get all the mChallengeQuestAchievementRewardGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mChallengeQuestAchievementRewardGroups in body.
     */
    @GetMapping("/m-challenge-quest-achievement-reward-groups")
    public ResponseEntity<List<MChallengeQuestAchievementRewardGroupDTO>> getAllMChallengeQuestAchievementRewardGroups(MChallengeQuestAchievementRewardGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MChallengeQuestAchievementRewardGroups by criteria: {}", criteria);
        Page<MChallengeQuestAchievementRewardGroupDTO> page = mChallengeQuestAchievementRewardGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-challenge-quest-achievement-reward-groups/count} : count all the mChallengeQuestAchievementRewardGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-challenge-quest-achievement-reward-groups/count")
    public ResponseEntity<Long> countMChallengeQuestAchievementRewardGroups(MChallengeQuestAchievementRewardGroupCriteria criteria) {
        log.debug("REST request to count MChallengeQuestAchievementRewardGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mChallengeQuestAchievementRewardGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-challenge-quest-achievement-reward-groups/:id} : get the "id" mChallengeQuestAchievementRewardGroup.
     *
     * @param id the id of the mChallengeQuestAchievementRewardGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mChallengeQuestAchievementRewardGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-challenge-quest-achievement-reward-groups/{id}")
    public ResponseEntity<MChallengeQuestAchievementRewardGroupDTO> getMChallengeQuestAchievementRewardGroup(@PathVariable Long id) {
        log.debug("REST request to get MChallengeQuestAchievementRewardGroup : {}", id);
        Optional<MChallengeQuestAchievementRewardGroupDTO> mChallengeQuestAchievementRewardGroupDTO = mChallengeQuestAchievementRewardGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mChallengeQuestAchievementRewardGroupDTO);
    }

    /**
     * {@code DELETE  /m-challenge-quest-achievement-reward-groups/:id} : delete the "id" mChallengeQuestAchievementRewardGroup.
     *
     * @param id the id of the mChallengeQuestAchievementRewardGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-challenge-quest-achievement-reward-groups/{id}")
    public ResponseEntity<Void> deleteMChallengeQuestAchievementRewardGroup(@PathVariable Long id) {
        log.debug("REST request to delete MChallengeQuestAchievementRewardGroup : {}", id);
        mChallengeQuestAchievementRewardGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
