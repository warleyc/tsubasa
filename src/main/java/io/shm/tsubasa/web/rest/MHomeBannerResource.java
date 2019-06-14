package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MHomeBannerService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MHomeBannerDTO;
import io.shm.tsubasa.service.dto.MHomeBannerCriteria;
import io.shm.tsubasa.service.MHomeBannerQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MHomeBanner}.
 */
@RestController
@RequestMapping("/api")
public class MHomeBannerResource {

    private final Logger log = LoggerFactory.getLogger(MHomeBannerResource.class);

    private static final String ENTITY_NAME = "mHomeBanner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MHomeBannerService mHomeBannerService;

    private final MHomeBannerQueryService mHomeBannerQueryService;

    public MHomeBannerResource(MHomeBannerService mHomeBannerService, MHomeBannerQueryService mHomeBannerQueryService) {
        this.mHomeBannerService = mHomeBannerService;
        this.mHomeBannerQueryService = mHomeBannerQueryService;
    }

    /**
     * {@code POST  /m-home-banners} : Create a new mHomeBanner.
     *
     * @param mHomeBannerDTO the mHomeBannerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mHomeBannerDTO, or with status {@code 400 (Bad Request)} if the mHomeBanner has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-home-banners")
    public ResponseEntity<MHomeBannerDTO> createMHomeBanner(@Valid @RequestBody MHomeBannerDTO mHomeBannerDTO) throws URISyntaxException {
        log.debug("REST request to save MHomeBanner : {}", mHomeBannerDTO);
        if (mHomeBannerDTO.getId() != null) {
            throw new BadRequestAlertException("A new mHomeBanner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MHomeBannerDTO result = mHomeBannerService.save(mHomeBannerDTO);
        return ResponseEntity.created(new URI("/api/m-home-banners/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-home-banners} : Updates an existing mHomeBanner.
     *
     * @param mHomeBannerDTO the mHomeBannerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mHomeBannerDTO,
     * or with status {@code 400 (Bad Request)} if the mHomeBannerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mHomeBannerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-home-banners")
    public ResponseEntity<MHomeBannerDTO> updateMHomeBanner(@Valid @RequestBody MHomeBannerDTO mHomeBannerDTO) throws URISyntaxException {
        log.debug("REST request to update MHomeBanner : {}", mHomeBannerDTO);
        if (mHomeBannerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MHomeBannerDTO result = mHomeBannerService.save(mHomeBannerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mHomeBannerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-home-banners} : get all the mHomeBanners.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mHomeBanners in body.
     */
    @GetMapping("/m-home-banners")
    public ResponseEntity<List<MHomeBannerDTO>> getAllMHomeBanners(MHomeBannerCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MHomeBanners by criteria: {}", criteria);
        Page<MHomeBannerDTO> page = mHomeBannerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-home-banners/count} : count all the mHomeBanners.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-home-banners/count")
    public ResponseEntity<Long> countMHomeBanners(MHomeBannerCriteria criteria) {
        log.debug("REST request to count MHomeBanners by criteria: {}", criteria);
        return ResponseEntity.ok().body(mHomeBannerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-home-banners/:id} : get the "id" mHomeBanner.
     *
     * @param id the id of the mHomeBannerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mHomeBannerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-home-banners/{id}")
    public ResponseEntity<MHomeBannerDTO> getMHomeBanner(@PathVariable Long id) {
        log.debug("REST request to get MHomeBanner : {}", id);
        Optional<MHomeBannerDTO> mHomeBannerDTO = mHomeBannerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mHomeBannerDTO);
    }

    /**
     * {@code DELETE  /m-home-banners/:id} : delete the "id" mHomeBanner.
     *
     * @param id the id of the mHomeBannerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-home-banners/{id}")
    public ResponseEntity<Void> deleteMHomeBanner(@PathVariable Long id) {
        log.debug("REST request to delete MHomeBanner : {}", id);
        mHomeBannerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
