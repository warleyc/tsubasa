package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MApRecoveryItemService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MApRecoveryItemDTO;
import io.shm.tsubasa.service.dto.MApRecoveryItemCriteria;
import io.shm.tsubasa.service.MApRecoveryItemQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MApRecoveryItem}.
 */
@RestController
@RequestMapping("/api")
public class MApRecoveryItemResource {

    private final Logger log = LoggerFactory.getLogger(MApRecoveryItemResource.class);

    private static final String ENTITY_NAME = "mApRecoveryItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MApRecoveryItemService mApRecoveryItemService;

    private final MApRecoveryItemQueryService mApRecoveryItemQueryService;

    public MApRecoveryItemResource(MApRecoveryItemService mApRecoveryItemService, MApRecoveryItemQueryService mApRecoveryItemQueryService) {
        this.mApRecoveryItemService = mApRecoveryItemService;
        this.mApRecoveryItemQueryService = mApRecoveryItemQueryService;
    }

    /**
     * {@code POST  /m-ap-recovery-items} : Create a new mApRecoveryItem.
     *
     * @param mApRecoveryItemDTO the mApRecoveryItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mApRecoveryItemDTO, or with status {@code 400 (Bad Request)} if the mApRecoveryItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-ap-recovery-items")
    public ResponseEntity<MApRecoveryItemDTO> createMApRecoveryItem(@Valid @RequestBody MApRecoveryItemDTO mApRecoveryItemDTO) throws URISyntaxException {
        log.debug("REST request to save MApRecoveryItem : {}", mApRecoveryItemDTO);
        if (mApRecoveryItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new mApRecoveryItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MApRecoveryItemDTO result = mApRecoveryItemService.save(mApRecoveryItemDTO);
        return ResponseEntity.created(new URI("/api/m-ap-recovery-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-ap-recovery-items} : Updates an existing mApRecoveryItem.
     *
     * @param mApRecoveryItemDTO the mApRecoveryItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mApRecoveryItemDTO,
     * or with status {@code 400 (Bad Request)} if the mApRecoveryItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mApRecoveryItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-ap-recovery-items")
    public ResponseEntity<MApRecoveryItemDTO> updateMApRecoveryItem(@Valid @RequestBody MApRecoveryItemDTO mApRecoveryItemDTO) throws URISyntaxException {
        log.debug("REST request to update MApRecoveryItem : {}", mApRecoveryItemDTO);
        if (mApRecoveryItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MApRecoveryItemDTO result = mApRecoveryItemService.save(mApRecoveryItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mApRecoveryItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-ap-recovery-items} : get all the mApRecoveryItems.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mApRecoveryItems in body.
     */
    @GetMapping("/m-ap-recovery-items")
    public ResponseEntity<List<MApRecoveryItemDTO>> getAllMApRecoveryItems(MApRecoveryItemCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MApRecoveryItems by criteria: {}", criteria);
        Page<MApRecoveryItemDTO> page = mApRecoveryItemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-ap-recovery-items/count} : count all the mApRecoveryItems.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-ap-recovery-items/count")
    public ResponseEntity<Long> countMApRecoveryItems(MApRecoveryItemCriteria criteria) {
        log.debug("REST request to count MApRecoveryItems by criteria: {}", criteria);
        return ResponseEntity.ok().body(mApRecoveryItemQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-ap-recovery-items/:id} : get the "id" mApRecoveryItem.
     *
     * @param id the id of the mApRecoveryItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mApRecoveryItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-ap-recovery-items/{id}")
    public ResponseEntity<MApRecoveryItemDTO> getMApRecoveryItem(@PathVariable Long id) {
        log.debug("REST request to get MApRecoveryItem : {}", id);
        Optional<MApRecoveryItemDTO> mApRecoveryItemDTO = mApRecoveryItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mApRecoveryItemDTO);
    }

    /**
     * {@code DELETE  /m-ap-recovery-items/:id} : delete the "id" mApRecoveryItem.
     *
     * @param id the id of the mApRecoveryItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-ap-recovery-items/{id}")
    public ResponseEntity<Void> deleteMApRecoveryItem(@PathVariable Long id) {
        log.debug("REST request to delete MApRecoveryItem : {}", id);
        mApRecoveryItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
