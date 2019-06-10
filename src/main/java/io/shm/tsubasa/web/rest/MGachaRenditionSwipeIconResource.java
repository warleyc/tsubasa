package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGachaRenditionSwipeIconService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGachaRenditionSwipeIconDTO;
import io.shm.tsubasa.service.dto.MGachaRenditionSwipeIconCriteria;
import io.shm.tsubasa.service.MGachaRenditionSwipeIconQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGachaRenditionSwipeIcon}.
 */
@RestController
@RequestMapping("/api")
public class MGachaRenditionSwipeIconResource {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionSwipeIconResource.class);

    private static final String ENTITY_NAME = "mGachaRenditionSwipeIcon";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGachaRenditionSwipeIconService mGachaRenditionSwipeIconService;

    private final MGachaRenditionSwipeIconQueryService mGachaRenditionSwipeIconQueryService;

    public MGachaRenditionSwipeIconResource(MGachaRenditionSwipeIconService mGachaRenditionSwipeIconService, MGachaRenditionSwipeIconQueryService mGachaRenditionSwipeIconQueryService) {
        this.mGachaRenditionSwipeIconService = mGachaRenditionSwipeIconService;
        this.mGachaRenditionSwipeIconQueryService = mGachaRenditionSwipeIconQueryService;
    }

    /**
     * {@code POST  /m-gacha-rendition-swipe-icons} : Create a new mGachaRenditionSwipeIcon.
     *
     * @param mGachaRenditionSwipeIconDTO the mGachaRenditionSwipeIconDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGachaRenditionSwipeIconDTO, or with status {@code 400 (Bad Request)} if the mGachaRenditionSwipeIcon has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-gacha-rendition-swipe-icons")
    public ResponseEntity<MGachaRenditionSwipeIconDTO> createMGachaRenditionSwipeIcon(@Valid @RequestBody MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO) throws URISyntaxException {
        log.debug("REST request to save MGachaRenditionSwipeIcon : {}", mGachaRenditionSwipeIconDTO);
        if (mGachaRenditionSwipeIconDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGachaRenditionSwipeIcon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGachaRenditionSwipeIconDTO result = mGachaRenditionSwipeIconService.save(mGachaRenditionSwipeIconDTO);
        return ResponseEntity.created(new URI("/api/m-gacha-rendition-swipe-icons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-gacha-rendition-swipe-icons} : Updates an existing mGachaRenditionSwipeIcon.
     *
     * @param mGachaRenditionSwipeIconDTO the mGachaRenditionSwipeIconDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGachaRenditionSwipeIconDTO,
     * or with status {@code 400 (Bad Request)} if the mGachaRenditionSwipeIconDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGachaRenditionSwipeIconDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-gacha-rendition-swipe-icons")
    public ResponseEntity<MGachaRenditionSwipeIconDTO> updateMGachaRenditionSwipeIcon(@Valid @RequestBody MGachaRenditionSwipeIconDTO mGachaRenditionSwipeIconDTO) throws URISyntaxException {
        log.debug("REST request to update MGachaRenditionSwipeIcon : {}", mGachaRenditionSwipeIconDTO);
        if (mGachaRenditionSwipeIconDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGachaRenditionSwipeIconDTO result = mGachaRenditionSwipeIconService.save(mGachaRenditionSwipeIconDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGachaRenditionSwipeIconDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-gacha-rendition-swipe-icons} : get all the mGachaRenditionSwipeIcons.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGachaRenditionSwipeIcons in body.
     */
    @GetMapping("/m-gacha-rendition-swipe-icons")
    public ResponseEntity<List<MGachaRenditionSwipeIconDTO>> getAllMGachaRenditionSwipeIcons(MGachaRenditionSwipeIconCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGachaRenditionSwipeIcons by criteria: {}", criteria);
        Page<MGachaRenditionSwipeIconDTO> page = mGachaRenditionSwipeIconQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-gacha-rendition-swipe-icons/count} : count all the mGachaRenditionSwipeIcons.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-gacha-rendition-swipe-icons/count")
    public ResponseEntity<Long> countMGachaRenditionSwipeIcons(MGachaRenditionSwipeIconCriteria criteria) {
        log.debug("REST request to count MGachaRenditionSwipeIcons by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGachaRenditionSwipeIconQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-gacha-rendition-swipe-icons/:id} : get the "id" mGachaRenditionSwipeIcon.
     *
     * @param id the id of the mGachaRenditionSwipeIconDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGachaRenditionSwipeIconDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-gacha-rendition-swipe-icons/{id}")
    public ResponseEntity<MGachaRenditionSwipeIconDTO> getMGachaRenditionSwipeIcon(@PathVariable Long id) {
        log.debug("REST request to get MGachaRenditionSwipeIcon : {}", id);
        Optional<MGachaRenditionSwipeIconDTO> mGachaRenditionSwipeIconDTO = mGachaRenditionSwipeIconService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGachaRenditionSwipeIconDTO);
    }

    /**
     * {@code DELETE  /m-gacha-rendition-swipe-icons/:id} : delete the "id" mGachaRenditionSwipeIcon.
     *
     * @param id the id of the mGachaRenditionSwipeIconDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-gacha-rendition-swipe-icons/{id}")
    public ResponseEntity<Void> deleteMGachaRenditionSwipeIcon(@PathVariable Long id) {
        log.debug("REST request to delete MGachaRenditionSwipeIcon : {}", id);
        mGachaRenditionSwipeIconService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
