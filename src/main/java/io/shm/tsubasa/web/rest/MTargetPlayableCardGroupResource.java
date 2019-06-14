package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MTargetPlayableCardGroupService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MTargetPlayableCardGroupDTO;
import io.shm.tsubasa.service.dto.MTargetPlayableCardGroupCriteria;
import io.shm.tsubasa.service.MTargetPlayableCardGroupQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MTargetPlayableCardGroup}.
 */
@RestController
@RequestMapping("/api")
public class MTargetPlayableCardGroupResource {

    private final Logger log = LoggerFactory.getLogger(MTargetPlayableCardGroupResource.class);

    private static final String ENTITY_NAME = "mTargetPlayableCardGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MTargetPlayableCardGroupService mTargetPlayableCardGroupService;

    private final MTargetPlayableCardGroupQueryService mTargetPlayableCardGroupQueryService;

    public MTargetPlayableCardGroupResource(MTargetPlayableCardGroupService mTargetPlayableCardGroupService, MTargetPlayableCardGroupQueryService mTargetPlayableCardGroupQueryService) {
        this.mTargetPlayableCardGroupService = mTargetPlayableCardGroupService;
        this.mTargetPlayableCardGroupQueryService = mTargetPlayableCardGroupQueryService;
    }

    /**
     * {@code POST  /m-target-playable-card-groups} : Create a new mTargetPlayableCardGroup.
     *
     * @param mTargetPlayableCardGroupDTO the mTargetPlayableCardGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mTargetPlayableCardGroupDTO, or with status {@code 400 (Bad Request)} if the mTargetPlayableCardGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-target-playable-card-groups")
    public ResponseEntity<MTargetPlayableCardGroupDTO> createMTargetPlayableCardGroup(@Valid @RequestBody MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MTargetPlayableCardGroup : {}", mTargetPlayableCardGroupDTO);
        if (mTargetPlayableCardGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new mTargetPlayableCardGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MTargetPlayableCardGroupDTO result = mTargetPlayableCardGroupService.save(mTargetPlayableCardGroupDTO);
        return ResponseEntity.created(new URI("/api/m-target-playable-card-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-target-playable-card-groups} : Updates an existing mTargetPlayableCardGroup.
     *
     * @param mTargetPlayableCardGroupDTO the mTargetPlayableCardGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mTargetPlayableCardGroupDTO,
     * or with status {@code 400 (Bad Request)} if the mTargetPlayableCardGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mTargetPlayableCardGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-target-playable-card-groups")
    public ResponseEntity<MTargetPlayableCardGroupDTO> updateMTargetPlayableCardGroup(@Valid @RequestBody MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO) throws URISyntaxException {
        log.debug("REST request to update MTargetPlayableCardGroup : {}", mTargetPlayableCardGroupDTO);
        if (mTargetPlayableCardGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MTargetPlayableCardGroupDTO result = mTargetPlayableCardGroupService.save(mTargetPlayableCardGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mTargetPlayableCardGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-target-playable-card-groups} : get all the mTargetPlayableCardGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mTargetPlayableCardGroups in body.
     */
    @GetMapping("/m-target-playable-card-groups")
    public ResponseEntity<List<MTargetPlayableCardGroupDTO>> getAllMTargetPlayableCardGroups(MTargetPlayableCardGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MTargetPlayableCardGroups by criteria: {}", criteria);
        Page<MTargetPlayableCardGroupDTO> page = mTargetPlayableCardGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-target-playable-card-groups/count} : count all the mTargetPlayableCardGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-target-playable-card-groups/count")
    public ResponseEntity<Long> countMTargetPlayableCardGroups(MTargetPlayableCardGroupCriteria criteria) {
        log.debug("REST request to count MTargetPlayableCardGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(mTargetPlayableCardGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-target-playable-card-groups/:id} : get the "id" mTargetPlayableCardGroup.
     *
     * @param id the id of the mTargetPlayableCardGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mTargetPlayableCardGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-target-playable-card-groups/{id}")
    public ResponseEntity<MTargetPlayableCardGroupDTO> getMTargetPlayableCardGroup(@PathVariable Long id) {
        log.debug("REST request to get MTargetPlayableCardGroup : {}", id);
        Optional<MTargetPlayableCardGroupDTO> mTargetPlayableCardGroupDTO = mTargetPlayableCardGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mTargetPlayableCardGroupDTO);
    }

    /**
     * {@code DELETE  /m-target-playable-card-groups/:id} : delete the "id" mTargetPlayableCardGroup.
     *
     * @param id the id of the mTargetPlayableCardGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-target-playable-card-groups/{id}")
    public ResponseEntity<Void> deleteMTargetPlayableCardGroup(@PathVariable Long id) {
        log.debug("REST request to delete MTargetPlayableCardGroup : {}", id);
        mTargetPlayableCardGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
