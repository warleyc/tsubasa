package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MQuestRewardGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MQuestRewardGroupDTO;
import io.shm.tsubasa.service.dto.MQuestRewardGroupCriteria;
import io.shm.tsubasa.service.MQuestRewardGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MQuestRewardGroup}.
 */
@RestController
@RequestMapping("/api")
public class MQuestRewardGroupResource {

    private final Logger log = LoggerFactory.getLogger(MQuestRewardGroupResource.class);

    private static final String ENTITY_NAME = "mQuestRewardGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MQuestRewardGroupService mQuestRewardGroupService;

    private final MQuestRewardGroupQueryService mQuestRewardGroupQueryService;

    public MQuestRewardGroupResource(MQuestRewardGroupService mQuestRewardGroupService, MQuestRewardGroupQueryService mQuestRewardGroupQueryService) {
        this.mQuestRewardGroupService = mQuestRewardGroupService;
        this.mQuestRewardGroupQueryService = mQuestRewardGroupQueryService;
    }

    /**
     * {@code POST  /m-quest-reward-groups} : Create a new mQuestRewardGroup.
     *
     * @param mQuestRewardGroupDTO the mQuestRewardGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mQuestRewardGroupDTO, or with status {@code 400 (Bad Request)} if the mQuestRewardGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-quest-reward-groups")
    public ResponseEntity<MQuestRewardGroupDTO> createMQuestRewardGroup(@Valid @RequestBody MQuestRewardGroupDTO mQuestRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MQuestRewardGroup : {}", mQuestRewardGroupDTO);
        if (mQuestRewardGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mQuestRewardGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MQuestRewardGroupDTO result = mQuestRewardGroupService.save(mQuestRewardGroupDTO);
        return ResponseEntity.created(new URI("/api/m-quest-reward-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-quest-reward-groups} : Updates an existing mQuestRewardGroup.
     *
     * @param mQuestRewardGroupDTO the mQuestRewardGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mQuestRewardGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mQuestRewardGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mQuestRewardGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-quest-reward-groups")
    public ResponseEntity<MQuestRewardGroupDTO> updateMQuestRewardGroup(@Valid @RequestBody MQuestRewardGroupDTO mQuestRewardGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MQuestRewardGroup : {}", mQuestRewardGroupDTO);
        if (mQuestRewardGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MQuestRewardGroupDTO result = mQuestRewardGroupService.save(mQuestRewardGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mQuestRewardGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-quest-reward-groups} : get all the mQuestRewardGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mQuestRewardGroups in body.
     */
    @GetMapping("/m-quest-reward-groups")
    public ResponseEntity<List<MQuestRewardGroupDTO>> getAllMQuestRewardGroups(MQuestRewardGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MQuestRewardGroups by criteria: {}", criteria);
        Page<MQuestRewardGroupDTO> page = mQuestRewardGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-quest-reward-groups/count} : count all the mQuestRewardGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-quest-reward-groups/count")
    public ResponseEntity<Long> countMQuestRewardGroups(MQuestRewardGroupCriteria criteria) {
        log.debug("REST request to count MQuestRewardGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mQuestRewardGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-quest-reward-groups/:id} : get the "id" mQuestRewardGroup.
     *
     * @param id the id of the mQuestRewardGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mQuestRewardGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-quest-reward-groups/{id}")
    public ResponseEntity<MQuestRewardGroupDTO> getMQuestRewardGroup(@PathVariable Long id) {
        log.debug("REST request to get MQuestRewardGroup : {}", id);
        Optional<MQuestRewardGroupDTO> mQuestRewardGroupDTO = mQuestRewardGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mQuestRewardGroupDTO);
    }

    /**
     * {@code DELETE  /m-quest-reward-groups/:id} : delete the "id" mQuestRewardGroup.
     *
     * @param id the id of the mQuestRewardGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-quest-reward-groups/{id}")
    public ResponseEntity<Void> deleteMQuestRewardGroup(@PathVariable Long id) {
        log.debug("REST request to delete MQuestRewardGroup : {}", id);
        mQuestRewardGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
