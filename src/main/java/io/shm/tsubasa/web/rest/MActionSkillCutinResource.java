package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MActionSkillCutinService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MActionSkillCutinDTO;
import io.shm.tsubasa.service.dto.MActionSkillCutinCriteria;
import io.shm.tsubasa.service.MActionSkillCutinQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MActionSkillCutin}.
 */
@RestController
@RequestMapping("/api")
public class MActionSkillCutinResource {

    private final Logger log = LoggerFactory.getLogger(MActionSkillCutinResource.class);

    private static final String ENTITY_NAME = "mActionSkillCutin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MActionSkillCutinService mActionSkillCutinService;

    private final MActionSkillCutinQueryService mActionSkillCutinQueryService;

    public MActionSkillCutinResource(MActionSkillCutinService mActionSkillCutinService, MActionSkillCutinQueryService mActionSkillCutinQueryService) {
        this.mActionSkillCutinService = mActionSkillCutinService;
        this.mActionSkillCutinQueryService = mActionSkillCutinQueryService;
    }

    /**
     * {@code POST  /m-action-skill-cutins} : Create a new mActionSkillCutin.
     *
     * @param mActionSkillCutinDTO the mActionSkillCutinDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mActionSkillCutinDTO, or with status {@code 400 (Bad Request)} if the mActionSkillCutin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-action-skill-cutins")
    public ResponseEntity<MActionSkillCutinDTO> createMActionSkillCutin(@Valid @RequestBody MActionSkillCutinDTO mActionSkillCutinDTO) throws URISyntaxException {
        log.debug("REST request to save MActionSkillCutin : {}", mActionSkillCutinDTO);
        if (mActionSkillCutinDTO.getId() != null) {
            throw new BadRequestAlertException("A new mActionSkillCutin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MActionSkillCutinDTO result = mActionSkillCutinService.save(mActionSkillCutinDTO);
        return ResponseEntity.created(new URI("/api/m-action-skill-cutins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-action-skill-cutins} : Updates an existing mActionSkillCutin.
     *
     * @param mActionSkillCutinDTO the mActionSkillCutinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mActionSkillCutinDTO,
     * or with status {@code 400 (Bad Request)} if the mActionSkillCutinDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mActionSkillCutinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-action-skill-cutins")
    public ResponseEntity<MActionSkillCutinDTO> updateMActionSkillCutin(@Valid @RequestBody MActionSkillCutinDTO mActionSkillCutinDTO) throws URISyntaxException {
        log.debug("REST request to update MActionSkillCutin : {}", mActionSkillCutinDTO);
        if (mActionSkillCutinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MActionSkillCutinDTO result = mActionSkillCutinService.save(mActionSkillCutinDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mActionSkillCutinDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-action-skill-cutins} : get all the mActionSkillCutins.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mActionSkillCutins in body.
     */
    @GetMapping("/m-action-skill-cutins")
    public ResponseEntity<List<MActionSkillCutinDTO>> getAllMActionSkillCutins(MActionSkillCutinCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MActionSkillCutins by criteria: {}", criteria);
        Page<MActionSkillCutinDTO> page = mActionSkillCutinQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-action-skill-cutins/count} : count all the mActionSkillCutins.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-action-skill-cutins/count")
    public ResponseEntity<Long> countMActionSkillCutins(MActionSkillCutinCriteria criteria) {
        log.debug("REST request to count MActionSkillCutins by criteria: {}", criteria);
        return ResponseEntity.ok().body(mActionSkillCutinQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-action-skill-cutins/:id} : get the "id" mActionSkillCutin.
     *
     * @param id the id of the mActionSkillCutinDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mActionSkillCutinDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-action-skill-cutins/{id}")
    public ResponseEntity<MActionSkillCutinDTO> getMActionSkillCutin(@PathVariable Long id) {
        log.debug("REST request to get MActionSkillCutin : {}", id);
        Optional<MActionSkillCutinDTO> mActionSkillCutinDTO = mActionSkillCutinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mActionSkillCutinDTO);
    }

    /**
     * {@code DELETE  /m-action-skill-cutins/:id} : delete the "id" mActionSkillCutin.
     *
     * @param id the id of the mActionSkillCutinDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-action-skill-cutins/{id}")
    public ResponseEntity<Void> deleteMActionSkillCutin(@PathVariable Long id) {
        log.debug("REST request to delete MActionSkillCutin : {}", id);
        mActionSkillCutinService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
