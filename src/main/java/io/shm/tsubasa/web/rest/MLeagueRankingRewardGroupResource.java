package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MLeagueRankingRewardGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardGroupDTO;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardGroupCriteria;
import io.shm.tsubasa.service.MLeagueRankingRewardGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MLeagueRankingRewardGroup}.
 */
@RestController
@RequestMapping("/api")
public class MLeagueRankingRewardGroupResource {

    private final Logger log = LoggerFactory.getLogger(MLeagueRankingRewardGroupResource.class);

    private static final String ENTITY_NAME = "mLeagueRankingRewardGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLeagueRankingRewardGroupService mLeagueRankingRewardGroupService;

    private final MLeagueRankingRewardGroupQueryService mLeagueRankingRewardGroupQueryService;

    public MLeagueRankingRewardGroupResource(MLeagueRankingRewardGroupService mLeagueRankingRewardGroupService, MLeagueRankingRewardGroupQueryService mLeagueRankingRewardGroupQueryService) {
        this.mLeagueRankingRewardGroupService = mLeagueRankingRewardGroupService;
        this.mLeagueRankingRewardGroupQueryService = mLeagueRankingRewardGroupQueryService;
    }

    /**
     * {@code POST  /m-league-ranking-reward-groups} : Create a new mLeagueRankingRewardGroup.
     *
     * @param mLeagueRankingRewardGroupDTO the mLeagueRankingRewardGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLeagueRankingRewardGroupDTO, or with status {@code 400 (Bad Request)} if the mLeagueRankingRewardGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-league-ranking-reward-groups")
    public ResponseEntity<MLeagueRankingRewardGroupDTO> createMLeagueRankingRewardGroup(@Valid @RequestBody MLeagueRankingRewardGroupDTO mLeagueRankingRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MLeagueRankingRewardGroup : {}", mLeagueRankingRewardGroupDTO);
        if (mLeagueRankingRewardGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mLeagueRankingRewardGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLeagueRankingRewardGroupDTO result = mLeagueRankingRewardGroupService.save(mLeagueRankingRewardGroupDTO);
        return ResponseEntity.created(new URI("/api/m-league-ranking-reward-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-league-ranking-reward-groups} : Updates an existing mLeagueRankingRewardGroup.
     *
     * @param mLeagueRankingRewardGroupDTO the mLeagueRankingRewardGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLeagueRankingRewardGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mLeagueRankingRewardGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLeagueRankingRewardGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-league-ranking-reward-groups")
    public ResponseEntity<MLeagueRankingRewardGroupDTO> updateMLeagueRankingRewardGroup(@Valid @RequestBody MLeagueRankingRewardGroupDTO mLeagueRankingRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MLeagueRankingRewardGroup : {}", mLeagueRankingRewardGroupDTO);
        if (mLeagueRankingRewardGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MLeagueRankingRewardGroupDTO result = mLeagueRankingRewardGroupService.save(mLeagueRankingRewardGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mLeagueRankingRewardGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-league-ranking-reward-groups} : get all the mLeagueRankingRewardGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLeagueRankingRewardGroups in body.
     */
    @GetMapping("/m-league-ranking-reward-groups")
    public ResponseEntity<List<MLeagueRankingRewardGroupDTO>> getAllMLeagueRankingRewardGroups(MLeagueRankingRewardGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MLeagueRankingRewardGroups by criteria: {}", criteria);
        Page<MLeagueRankingRewardGroupDTO> page = mLeagueRankingRewardGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-league-ranking-reward-groups/count} : count all the mLeagueRankingRewardGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-league-ranking-reward-groups/count")
    public ResponseEntity<Long> countMLeagueRankingRewardGroups(MLeagueRankingRewardGroupCriteria criteria) {
        log.debug("REST request to count MLeagueRankingRewardGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mLeagueRankingRewardGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-league-ranking-reward-groups/:id} : get the "id" mLeagueRankingRewardGroup.
     *
     * @param id the id of the mLeagueRankingRewardGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLeagueRankingRewardGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-league-ranking-reward-groups/{id}")
    public ResponseEntity<MLeagueRankingRewardGroupDTO> getMLeagueRankingRewardGroup(@PathVariable Long id) {
        log.debug("REST request to get MLeagueRankingRewardGroup : {}", id);
        Optional<MLeagueRankingRewardGroupDTO> mLeagueRankingRewardGroupDTO = mLeagueRankingRewardGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mLeagueRankingRewardGroupDTO);
    }

    /**
     * {@code DELETE  /m-league-ranking-reward-groups/:id} : delete the "id" mLeagueRankingRewardGroup.
     *
     * @param id the id of the mLeagueRankingRewardGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-league-ranking-reward-groups/{id}")
    public ResponseEntity<Void> deleteMLeagueRankingRewardGroup(@PathVariable Long id) {
        log.debug("REST request to delete MLeagueRankingRewardGroup : {}", id);
        mLeagueRankingRewardGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
