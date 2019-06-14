package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MStageStoryService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MStageStoryDTO;
import io.shm.tsubasa.service.dto.MStageStoryCriteria;
import io.shm.tsubasa.service.MStageStoryQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MStageStory}.
 */
@RestController
@RequestMapping("/api")
public class MStageStoryResource {

    private final Logger log = LoggerFactory.getLogger(MStageStoryResource.class);

    private static final String ENTITY_NAME = "mStageStory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MStageStoryService mStageStoryService;

    private final MStageStoryQueryService mStageStoryQueryService;

    public MStageStoryResource(MStageStoryService mStageStoryService, MStageStoryQueryService mStageStoryQueryService) {
        this.mStageStoryService = mStageStoryService;
        this.mStageStoryQueryService = mStageStoryQueryService;
    }

    /**
     * {@code POST  /m-stage-stories} : Create a new mStageStory.
     *
     * @param mStageStoryDTO the mStageStoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mStageStoryDTO, or with status {@code 400 (Bad Request)} if the mStageStory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-stage-stories")
    public ResponseEntity<MStageStoryDTO> createMStageStory(@Valid @RequestBody MStageStoryDTO mStageStoryDTO) throws URISyntaxException {
        log.debug("REST request to save MStageStory : {}", mStageStoryDTO);
        if (mStageStoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new mStageStory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MStageStoryDTO result = mStageStoryService.save(mStageStoryDTO);
        return ResponseEntity.created(new URI("/api/m-stage-stories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-stage-stories} : Updates an existing mStageStory.
     *
     * @param mStageStoryDTO the mStageStoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mStageStoryDTO,
     * or with status {@code 400 (Bad Request)} if the mStageStoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mStageStoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-stage-stories")
    public ResponseEntity<MStageStoryDTO> updateMStageStory(@Valid @RequestBody MStageStoryDTO mStageStoryDTO) throws URISyntaxException {
        log.debug("REST request to update MStageStory : {}", mStageStoryDTO);
        if (mStageStoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MStageStoryDTO result = mStageStoryService.save(mStageStoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mStageStoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-stage-stories} : get all the mStageStories.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mStageStories in body.
     */
    @GetMapping("/m-stage-stories")
    public ResponseEntity<List<MStageStoryDTO>> getAllMStageStories(MStageStoryCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MStageStories by criteria: {}", criteria);
        Page<MStageStoryDTO> page = mStageStoryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-stage-stories/count} : count all the mStageStories.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-stage-stories/count")
    public ResponseEntity<Long> countMStageStories(MStageStoryCriteria criteria) {
        log.debug("REST request to count MStageStories by criteria: {}", criteria);
        return ResponseEntity.ok().body(mStageStoryQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-stage-stories/:id} : get the "id" mStageStory.
     *
     * @param id the id of the mStageStoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mStageStoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-stage-stories/{id}")
    public ResponseEntity<MStageStoryDTO> getMStageStory(@PathVariable Long id) {
        log.debug("REST request to get MStageStory : {}", id);
        Optional<MStageStoryDTO> mStageStoryDTO = mStageStoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mStageStoryDTO);
    }

    /**
     * {@code DELETE  /m-stage-stories/:id} : delete the "id" mStageStory.
     *
     * @param id the id of the mStageStoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-stage-stories/{id}")
    public ResponseEntity<Void> deleteMStageStory(@PathVariable Long id) {
        log.debug("REST request to delete MStageStory : {}", id);
        mStageStoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
