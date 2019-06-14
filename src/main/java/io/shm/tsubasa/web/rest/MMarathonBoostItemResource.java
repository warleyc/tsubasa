package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MMarathonBoostItemService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MMarathonBoostItemDTO;
import io.shm.tsubasa.service.dto.MMarathonBoostItemCriteria;
import io.shm.tsubasa.service.MMarathonBoostItemQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MMarathonBoostItem}.
 */
@RestController
@RequestMapping("/api")
public class MMarathonBoostItemResource {

    private final Logger log = LoggerFactory.getLogger(MMarathonBoostItemResource.class);

    private static final String ENTITY_NAME = "mMarathonBoostItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MMarathonBoostItemService mMarathonBoostItemService;

    private final MMarathonBoostItemQueryService mMarathonBoostItemQueryService;

    public MMarathonBoostItemResource(MMarathonBoostItemService mMarathonBoostItemService, MMarathonBoostItemQueryService mMarathonBoostItemQueryService) {
        this.mMarathonBoostItemService = mMarathonBoostItemService;
        this.mMarathonBoostItemQueryService = mMarathonBoostItemQueryService;
    }

    /**
     * {@code POST  /m-marathon-boost-items} : Create a new mMarathonBoostItem.
     *
     * @param mMarathonBoostItemDTO the mMarathonBoostItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mMarathonBoostItemDTO, or with status {@code 400 (Bad Request)} if the mMarathonBoostItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-marathon-boost-items")
    public ResponseEntity<MMarathonBoostItemDTO> createMMarathonBoostItem(@Valid @RequestBody MMarathonBoostItemDTO mMarathonBoostItemDTO) throws URISyntaxException {
        log.debug("REST request to save MMarathonBoostItem : {}", mMarathonBoostItemDTO);
        if (mMarathonBoostItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new mMarathonBoostItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MMarathonBoostItemDTO result = mMarathonBoostItemService.save(mMarathonBoostItemDTO);
        return ResponseEntity.created(new URI("/api/m-marathon-boost-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-marathon-boost-items} : Updates an existing mMarathonBoostItem.
     *
     * @param mMarathonBoostItemDTO the mMarathonBoostItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mMarathonBoostItemDTO,
     * or with status {@code 400 (Bad Request)} if the mMarathonBoostItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mMarathonBoostItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-marathon-boost-items")
    public ResponseEntity<MMarathonBoostItemDTO> updateMMarathonBoostItem(@Valid @RequestBody MMarathonBoostItemDTO mMarathonBoostItemDTO) throws URISyntaxException {
        log.debug("REST request to update MMarathonBoostItem : {}", mMarathonBoostItemDTO);
        if (mMarathonBoostItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMarathonBoostItemDTO result = mMarathonBoostItemService.save(mMarathonBoostItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mMarathonBoostItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-marathon-boost-items} : get all the mMarathonBoostItems.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mMarathonBoostItems in body.
     */
    @GetMapping("/m-marathon-boost-items")
    public ResponseEntity<List<MMarathonBoostItemDTO>> getAllMMarathonBoostItems(MMarathonBoostItemCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MMarathonBoostItems by criteria: {}", criteria);
        Page<MMarathonBoostItemDTO> page = mMarathonBoostItemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-marathon-boost-items/count} : count all the mMarathonBoostItems.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-marathon-boost-items/count")
    public ResponseEntity<Long> countMMarathonBoostItems(MMarathonBoostItemCriteria criteria) {
        log.debug("REST request to count MMarathonBoostItems by criteria: {}", criteria);
        return ResponseEntity.ok().body(mMarathonBoostItemQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-marathon-boost-items/:id} : get the "id" mMarathonBoostItem.
     *
     * @param id the id of the mMarathonBoostItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mMarathonBoostItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-marathon-boost-items/{id}")
    public ResponseEntity<MMarathonBoostItemDTO> getMMarathonBoostItem(@PathVariable Long id) {
        log.debug("REST request to get MMarathonBoostItem : {}", id);
        Optional<MMarathonBoostItemDTO> mMarathonBoostItemDTO = mMarathonBoostItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mMarathonBoostItemDTO);
    }

    /**
     * {@code DELETE  /m-marathon-boost-items/:id} : delete the "id" mMarathonBoostItem.
     *
     * @param id the id of the mMarathonBoostItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-marathon-boost-items/{id}")
    public ResponseEntity<Void> deleteMMarathonBoostItem(@PathVariable Long id) {
        log.debug("REST request to delete MMarathonBoostItem : {}", id);
        mMarathonBoostItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
