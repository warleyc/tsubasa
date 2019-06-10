package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MArousalItemService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MArousalItemDTO;
import io.shm.tsubasa.service.dto.MArousalItemCriteria;
import io.shm.tsubasa.service.MArousalItemQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MArousalItem}.
 */
@RestController
@RequestMapping("/api")
public class MArousalItemResource {

    private final Logger log = LoggerFactory.getLogger(MArousalItemResource.class);

    private static final String ENTITY_NAME = "mArousalItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MArousalItemService mArousalItemService;

    private final MArousalItemQueryService mArousalItemQueryService;

    public MArousalItemResource(MArousalItemService mArousalItemService, MArousalItemQueryService mArousalItemQueryService) {
        this.mArousalItemService = mArousalItemService;
        this.mArousalItemQueryService = mArousalItemQueryService;
    }

    /**
     * {@code POST  /m-arousal-items} : Create a new mArousalItem.
     *
     * @param mArousalItemDTO the mArousalItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mArousalItemDTO, or with status {@code 400 (Bad Request)} if the mArousalItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-arousal-items")
    public ResponseEntity<MArousalItemDTO> createMArousalItem(@Valid @RequestBody MArousalItemDTO mArousalItemDTO) throws URISyntaxException {
        log.debug("REST request to save MArousalItem : {}", mArousalItemDTO);
        if (mArousalItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new mArousalItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MArousalItemDTO result = mArousalItemService.save(mArousalItemDTO);
        return ResponseEntity.created(new URI("/api/m-arousal-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-arousal-items} : Updates an existing mArousalItem.
     *
     * @param mArousalItemDTO the mArousalItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mArousalItemDTO,
     * or with status {@code 400 (Bad Request)} if the mArousalItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mArousalItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-arousal-items")
    public ResponseEntity<MArousalItemDTO> updateMArousalItem(@Valid @RequestBody MArousalItemDTO mArousalItemDTO) throws URISyntaxException {
        log.debug("REST request to update MArousalItem : {}", mArousalItemDTO);
        if (mArousalItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MArousalItemDTO result = mArousalItemService.save(mArousalItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mArousalItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-arousal-items} : get all the mArousalItems.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mArousalItems in body.
     */
    @GetMapping("/m-arousal-items")
    public ResponseEntity<List<MArousalItemDTO>> getAllMArousalItems(MArousalItemCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MArousalItems by criteria: {}", criteria);
        Page<MArousalItemDTO> page = mArousalItemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-arousal-items/count} : count all the mArousalItems.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-arousal-items/count")
    public ResponseEntity<Long> countMArousalItems(MArousalItemCriteria criteria) {
        log.debug("REST request to count MArousalItems by criteria: {}", criteria);
        return ResponseEntity.ok().body(mArousalItemQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-arousal-items/:id} : get the "id" mArousalItem.
     *
     * @param id the id of the mArousalItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mArousalItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-arousal-items/{id}")
    public ResponseEntity<MArousalItemDTO> getMArousalItem(@PathVariable Long id) {
        log.debug("REST request to get MArousalItem : {}", id);
        Optional<MArousalItemDTO> mArousalItemDTO = mArousalItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mArousalItemDTO);
    }

    /**
     * {@code DELETE  /m-arousal-items/:id} : delete the "id" mArousalItem.
     *
     * @param id the id of the mArousalItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-arousal-items/{id}")
    public ResponseEntity<Void> deleteMArousalItem(@PathVariable Long id) {
        log.debug("REST request to delete MArousalItem : {}", id);
        mArousalItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
