package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MNpcPersonalityService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MNpcPersonalityDTO;
import io.shm.tsubasa.service.dto.MNpcPersonalityCriteria;
import io.shm.tsubasa.service.MNpcPersonalityQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MNpcPersonality}.
 */
@RestController
@RequestMapping("/api")
public class MNpcPersonalityResource {

    private final Logger log = LoggerFactory.getLogger(MNpcPersonalityResource.class);

    private static final String ENTITY_NAME = "mNpcPersonality";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MNpcPersonalityService mNpcPersonalityService;

    private final MNpcPersonalityQueryService mNpcPersonalityQueryService;

    public MNpcPersonalityResource(MNpcPersonalityService mNpcPersonalityService, MNpcPersonalityQueryService mNpcPersonalityQueryService) {
        this.mNpcPersonalityService = mNpcPersonalityService;
        this.mNpcPersonalityQueryService = mNpcPersonalityQueryService;
    }

    /**
     * {@code POST  /m-npc-personalities} : Create a new mNpcPersonality.
     *
     * @param mNpcPersonalityDTO the mNpcPersonalityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mNpcPersonalityDTO, or with status {@code 400 (Bad Request)} if the mNpcPersonality has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-npc-personalities")
    public ResponseEntity<MNpcPersonalityDTO> createMNpcPersonality(@Valid @RequestBody MNpcPersonalityDTO mNpcPersonalityDTO) throws URISyntaxException {
        log.debug("REST request to save MNpcPersonality : {}", mNpcPersonalityDTO);
        if (mNpcPersonalityDTO.getId() != null) {
            throw new BadRequestAlertException("A new mNpcPersonality cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MNpcPersonalityDTO result = mNpcPersonalityService.save(mNpcPersonalityDTO);
        return ResponseEntity.created(new URI("/api/m-npc-personalities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-npc-personalities} : Updates an existing mNpcPersonality.
     *
     * @param mNpcPersonalityDTO the mNpcPersonalityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mNpcPersonalityDTO,
     * or with status {@code 400 (Bad Request)} if the mNpcPersonalityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mNpcPersonalityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-npc-personalities")
    public ResponseEntity<MNpcPersonalityDTO> updateMNpcPersonality(@Valid @RequestBody MNpcPersonalityDTO mNpcPersonalityDTO) throws URISyntaxException {
        log.debug("REST request to update MNpcPersonality : {}", mNpcPersonalityDTO);
        if (mNpcPersonalityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MNpcPersonalityDTO result = mNpcPersonalityService.save(mNpcPersonalityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mNpcPersonalityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-npc-personalities} : get all the mNpcPersonalities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mNpcPersonalities in body.
     */
    @GetMapping("/m-npc-personalities")
    public ResponseEntity<List<MNpcPersonalityDTO>> getAllMNpcPersonalities(MNpcPersonalityCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MNpcPersonalities by criteria: {}", criteria);
        Page<MNpcPersonalityDTO> page = mNpcPersonalityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-npc-personalities/count} : count all the mNpcPersonalities.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-npc-personalities/count")
    public ResponseEntity<Long> countMNpcPersonalities(MNpcPersonalityCriteria criteria) {
        log.debug("REST request to count MNpcPersonalities by criteria: {}", criteria);
        return ResponseEntity.ok().body(mNpcPersonalityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-npc-personalities/:id} : get the "id" mNpcPersonality.
     *
     * @param id the id of the mNpcPersonalityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mNpcPersonalityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-npc-personalities/{id}")
    public ResponseEntity<MNpcPersonalityDTO> getMNpcPersonality(@PathVariable Long id) {
        log.debug("REST request to get MNpcPersonality : {}", id);
        Optional<MNpcPersonalityDTO> mNpcPersonalityDTO = mNpcPersonalityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mNpcPersonalityDTO);
    }

    /**
     * {@code DELETE  /m-npc-personalities/:id} : delete the "id" mNpcPersonality.
     *
     * @param id the id of the mNpcPersonalityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-npc-personalities/{id}")
    public ResponseEntity<Void> deleteMNpcPersonality(@PathVariable Long id) {
        log.debug("REST request to delete MNpcPersonality : {}", id);
        mNpcPersonalityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
