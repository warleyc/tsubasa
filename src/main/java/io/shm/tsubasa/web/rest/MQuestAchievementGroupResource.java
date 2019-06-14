package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestAchievementGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestAchievementGroupDTO;
import io.shm.tsubasa.service.dto.MQuestAchievementGroupCriteria;
import io.shm.tsubasa.service.MQuestAchievementGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestAchievementGroup}.
 */
@RestController
@RequestMapping("/api")
public class MQuestAchievementGroupResource {

    private final Logger log = LoggerFactory.getLogger(MQuestAchievementGroupResource.class);

    private static final String ENTITY_NAME = "mQuestAchievementGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestAchievementGroupService mQuestAchievementGroupService;

    private final MQuestAchievementGroupQueryService mQuestAchievementGroupQueryService;

    public MQuestAchievementGroupResource(MQuestAchievementGroupService mQuestAchievementGroupService, MQuestAchievementGroupQueryService mQuestAchievementGroupQueryService) {
        this.mQuestAchievementGroupService = mQuestAchievementGroupService;
        this.mQuestAchievementGroupQueryService = mQuestAchievementGroupQueryService;
    }

    /**
     * {@code POST  /m-quest-achievement-groups} : Create a new mQuestAchievementGroup.
     *
     * @param mQuestAchievementGroupDTO the mQuestAchievementGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestAchievementGroupDTO, or with status {@code 400 (Bad Request)} if the mQuestAchievementGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-achievement-groups")
    public ResponseEntity<MQuestAchievementGroupDTO> createMQuestAchievementGroup(@Valid @RequestBody MQuestAchievementGroupDTO mQuestAchievementGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestAchievementGroup : {}", mQuestAchievementGroupDTO);
        if (mQuestAchievementGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestAchievementGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestAchievementGroupDTO result = mQuestAchievementGroupService.save(mQuestAchievementGroupDTO);
        return ResponseEntity.created(new URI("/api/m-quest-achievement-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-achievement-groups} : Updates an existing mQuestAchievementGroup.
     *
     * @param mQuestAchievementGroupDTO the mQuestAchievementGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestAchievementGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestAchievementGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestAchievementGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-achievement-groups")
    public ResponseEntity<MQuestAchievementGroupDTO> updateMQuestAchievementGroup(@Valid @RequestBody MQuestAchievementGroupDTO mQuestAchievementGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestAchievementGroup : {}", mQuestAchievementGroupDTO);
        if (mQuestAchievementGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestAchievementGroupDTO result = mQuestAchievementGroupService.save(mQuestAchievementGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestAchievementGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-achievement-groups} : get all the mQuestAchievementGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestAchievementGroups in body.
     */
    @GetMapping("/m-quest-achievement-groups")
    public ResponseEntity<List<MQuestAchievementGroupDTO>> getAllMQuestAchievementGroups(MQuestAchievementGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestAchievementGroups by criteria: {}", criteria);
        Page<MQuestAchievementGroupDTO> page = mQuestAchievementGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-achievement-groups/count} : count all the mQuestAchievementGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-achievement-groups/count")
    public ResponseEntity<Long> countMQuestAchievementGroups(MQuestAchievementGroupCriteria criteria) {
        log.debug("REST request to count MQuestAchievementGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestAchievementGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-achievement-groups/:id} : get the "id" mQuestAchievementGroup.
     *
     * @param id the id of the mQuestAchievementGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestAchievementGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-achievement-groups/{id}")
    public ResponseEntity<MQuestAchievementGroupDTO> getMQuestAchievementGroup(@PathVariable Long id) {
        log.debug("REST request to get MQuestAchievementGroup : {}", id);
        Optional<MQuestAchievementGroupDTO> mQuestAchievementGroupDTO = mQuestAchievementGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestAchievementGroupDTO);
    }

    /**
     * {@code DELETE  /m-quest-achievement-groups/:id} : delete the "id" mQuestAchievementGroup.
     *
     * @param id the id of the mQuestAchievementGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-achievement-groups/{id}")
    public ResponseEntity<Void> deleteMQuestAchievementGroup(@PathVariable Long id) {
        log.debug("REST request to delete MQuestAchievementGroup : {}", id);
        mQuestAchievementGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
