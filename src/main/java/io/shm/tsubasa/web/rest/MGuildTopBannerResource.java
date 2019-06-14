package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MGuildTopBannerService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MGuildTopBannerDTO;
import io.shm.tsubasa.service.dto.MGuildTopBannerCriteria;
import io.shm.tsubasa.service.MGuildTopBannerQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MGuildTopBanner}.
 */
@RestController
@RequestMapping("/api")
public class MGuildTopBannerResource {

    private final Logger log = LoggerFactory.getLogger(MGuildTopBannerResource.class);

    private static final String ENTITY_NAME = "mGuildTopBanner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MGuildTopBannerService mGuildTopBannerService;

    private final MGuildTopBannerQueryService mGuildTopBannerQueryService;

    public MGuildTopBannerResource(MGuildTopBannerService mGuildTopBannerService, MGuildTopBannerQueryService mGuildTopBannerQueryService) {
        this.mGuildTopBannerService = mGuildTopBannerService;
        this.mGuildTopBannerQueryService = mGuildTopBannerQueryService;
    }

    /**
     * {@code POST  /m-guild-top-banners} : Create a new mGuildTopBanner.
     *
     * @param mGuildTopBannerDTO the mGuildTopBannerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mGuildTopBannerDTO, or with status {@code 400 (Bad Request)} if the mGuildTopBanner has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-guild-top-banners")
    public ResponseEntity<MGuildTopBannerDTO> createMGuildTopBanner(@Valid @RequestBody MGuildTopBannerDTO mGuildTopBannerDTO) throws URISyntaxException {
        log.debug("REST request to save MGuildTopBanner : {}", mGuildTopBannerDTO);
        if (mGuildTopBannerDTO.getId() != null) {
            throw new BadRequestAlertException("A new mGuildTopBanner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MGuildTopBannerDTO result = mGuildTopBannerService.save(mGuildTopBannerDTO);
        return ResponseEntity.created(new URI("/api/m-guild-top-banners/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-guild-top-banners} : Updates an existing mGuildTopBanner.
     *
     * @param mGuildTopBannerDTO the mGuildTopBannerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mGuildTopBannerDTO,
     * or with status {@code 400 (Bad Request)} if the mGuildTopBannerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mGuildTopBannerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-guild-top-banners")
    public ResponseEntity<MGuildTopBannerDTO> updateMGuildTopBanner(@Valid @RequestBody MGuildTopBannerDTO mGuildTopBannerDTO) throws URISyntaxException {
        log.debug("REST request to update MGuildTopBanner : {}", mGuildTopBannerDTO);
        if (mGuildTopBannerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MGuildTopBannerDTO result = mGuildTopBannerService.save(mGuildTopBannerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mGuildTopBannerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-guild-top-banners} : get all the mGuildTopBanners.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mGuildTopBanners in body.
     */
    @GetMapping("/m-guild-top-banners")
    public ResponseEntity<List<MGuildTopBannerDTO>> getAllMGuildTopBanners(MGuildTopBannerCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MGuildTopBanners by criteria: {}", criteria);
        Page<MGuildTopBannerDTO> page = mGuildTopBannerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-guild-top-banners/count} : count all the mGuildTopBanners.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-guild-top-banners/count")
    public ResponseEntity<Long> countMGuildTopBanners(MGuildTopBannerCriteria criteria) {
        log.debug("REST request to count MGuildTopBanners by criteria: {}", criteria);
        return ResponseEntity.ok().body(mGuildTopBannerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-guild-top-banners/:id} : get the "id" mGuildTopBanner.
     *
     * @param id the id of the mGuildTopBannerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mGuildTopBannerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-guild-top-banners/{id}")
    public ResponseEntity<MGuildTopBannerDTO> getMGuildTopBanner(@PathVariable Long id) {
        log.debug("REST request to get MGuildTopBanner : {}", id);
        Optional<MGuildTopBannerDTO> mGuildTopBannerDTO = mGuildTopBannerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mGuildTopBannerDTO);
    }

    /**
     * {@code DELETE  /m-guild-top-banners/:id} : delete the "id" mGuildTopBanner.
     *
     * @param id the id of the mGuildTopBannerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-guild-top-banners/{id}")
    public ResponseEntity<Void> deleteMGuildTopBanner(@PathVariable Long id) {
        log.debug("REST request to delete MGuildTopBanner : {}", id);
        mGuildTopBannerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
