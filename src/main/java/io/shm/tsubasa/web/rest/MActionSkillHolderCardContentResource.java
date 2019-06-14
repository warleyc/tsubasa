package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MActionSkillHolderCardContentService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardContentDTO;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardContentCriteria;
import io.shm.tsubasa.service.MActionSkillHolderCardContentQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MActionSkillHolderCardContent}.
 */
@RestController
@RequestMapping("/api")
public class MActionSkillHolderCardContentResource {

    private final Logger log = LoggerFactory.getLogger(MActionSkillHolderCardContentResource.class);

    private static final String ENTITY_NAME = "mActionSkillHolderCardContent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MActionSkillHolderCardContentService mActionSkillHolderCardContentService;

    private final MActionSkillHolderCardContentQueryService mActionSkillHolderCardContentQueryService;

    public MActionSkillHolderCardContentResource(MActionSkillHolderCardContentService mActionSkillHolderCardContentService, MActionSkillHolderCardContentQueryService mActionSkillHolderCardContentQueryService) {
        this.mActionSkillHolderCardContentService = mActionSkillHolderCardContentService;
        this.mActionSkillHolderCardContentQueryService = mActionSkillHolderCardContentQueryService;
    }

    /**
     * {@code POST  /m-action-skill-holder-card-contents} : Create a new mActionSkillHolderCardContent.
     *
     * @param mActionSkillHolderCardContentDTO the mActionSkillHolderCardContentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mActionSkillHolderCardContentDTO, or with status {@code 400 (Bad Request)} if the mActionSkillHolderCardContent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-action-skill-holder-card-contents")
    public ResponseEntity<MActionSkillHolderCardContentDTO> createMActionSkillHolderCardContent(@Valid @RequestBody MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO) throws URISyntaxException {
        log.debug("REST request to save MActionSkillHolderCardContent : {}", mActionSkillHolderCardContentDTO);
        if (mActionSkillHolderCardContentDTO.getId() != null) {
            throw new BadRequestAlertException("A new mActionSkillHolderCardContent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MActionSkillHolderCardContentDTO result = mActionSkillHolderCardContentService.save(mActionSkillHolderCardContentDTO);
        return ResponseEntity.created(new URI("/api/m-action-skill-holder-card-contents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-action-skill-holder-card-contents} : Updates an existing mActionSkillHolderCardContent.
     *
     * @param mActionSkillHolderCardContentDTO the mActionSkillHolderCardContentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mActionSkillHolderCardContentDTO,
     * or with status {@code 400 (Bad Request)} if the mActionSkillHolderCardContentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mActionSkillHolderCardContentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-action-skill-holder-card-contents")
    public ResponseEntity<MActionSkillHolderCardContentDTO> updateMActionSkillHolderCardContent(@Valid @RequestBody MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO) throws URISyntaxException {
        log.debug("REST request to update MActionSkillHolderCardContent : {}", mActionSkillHolderCardContentDTO);
        if (mActionSkillHolderCardContentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MActionSkillHolderCardContentDTO result = mActionSkillHolderCardContentService.save(mActionSkillHolderCardContentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mActionSkillHolderCardContentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-action-skill-holder-card-contents} : get all the mActionSkillHolderCardContents.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mActionSkillHolderCardContents in body.
     */
    @GetMapping("/m-action-skill-holder-card-contents")
    public ResponseEntity<List<MActionSkillHolderCardContentDTO>> getAllMActionSkillHolderCardContents(MActionSkillHolderCardContentCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MActionSkillHolderCardContents by criteria: {}", criteria);
        Page<MActionSkillHolderCardContentDTO> page = mActionSkillHolderCardContentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-action-skill-holder-card-contents/count} : count all the mActionSkillHolderCardContents.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-action-skill-holder-card-contents/count")
    public ResponseEntity<Long> countMActionSkillHolderCardContents(MActionSkillHolderCardContentCriteria criteria) {
        log.debug("REST request to count MActionSkillHolderCardContents by criteria: {}", criteria);
        return ResponseEntity.ok().body(mActionSkillHolderCardContentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-action-skill-holder-card-contents/:id} : get the "id" mActionSkillHolderCardContent.
     *
     * @param id the id of the mActionSkillHolderCardContentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mActionSkillHolderCardContentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-action-skill-holder-card-contents/{id}")
    public ResponseEntity<MActionSkillHolderCardContentDTO> getMActionSkillHolderCardContent(@PathVariable Long id) {
        log.debug("REST request to get MActionSkillHolderCardContent : {}", id);
        Optional<MActionSkillHolderCardContentDTO> mActionSkillHolderCardContentDTO = mActionSkillHolderCardContentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mActionSkillHolderCardContentDTO);
    }

    /**
     * {@code DELETE  /m-action-skill-holder-card-contents/:id} : delete the "id" mActionSkillHolderCardContent.
     *
     * @param id the id of the mActionSkillHolderCardContentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-action-skill-holder-card-contents/{id}")
    public ResponseEntity<Void> deleteMActionSkillHolderCardContent(@PathVariable Long id) {
        log.debug("REST request to delete MActionSkillHolderCardContent : {}", id);
        mActionSkillHolderCardContentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
