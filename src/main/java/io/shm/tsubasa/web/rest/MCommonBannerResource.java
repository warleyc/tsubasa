package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MCommonBannerService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MCommonBannerDTO;
import io.shm.tsubasa.service.dto.MCommonBannerCriteria;
import io.shm.tsubasa.service.MCommonBannerQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MCommonBanner}.
 */
@RestController
@RequestMapping("/api")
public class MCommonBannerResource {

    private final Logger log = LoggerFactory.getLogger(MCommonBannerResource.class);

    private static final String ENTITY_NAME = "mCommonBanner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MCommonBannerService mCommonBannerService;

    private final MCommonBannerQueryService mCommonBannerQueryService;

    public MCommonBannerResource(MCommonBannerService mCommonBannerService, MCommonBannerQueryService mCommonBannerQueryService) {
        this.mCommonBannerService = mCommonBannerService;
        this.mCommonBannerQueryService = mCommonBannerQueryService;
    }

    /**
     * {@code POST  /m-common-banners} : Create a new mCommonBanner.
     *
     * @param mCommonBannerDTO the mCommonBannerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mCommonBannerDTO, or with status {@code 400 (Bad Request)} if the mCommonBanner has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-common-banners")
    public ResponseEntity<MCommonBannerDTO> createMCommonBanner(@Valid @RequestBody MCommonBannerDTO mCommonBannerDTO) throws URISyntaxException {
        log.debug("REST request to save MCommonBanner : {}", mCommonBannerDTO);
        if (mCommonBannerDTO.getId() != null) {
            throw new BadRequestAlertException("A new mCommonBanner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MCommonBannerDTO result = mCommonBannerService.save(mCommonBannerDTO);
        return ResponseEntity.created(new URI("/api/m-common-banners/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-common-banners} : Updates an existing mCommonBanner.
     *
     * @param mCommonBannerDTO the mCommonBannerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mCommonBannerDTO,
     * or with status {@code 400 (Bad Request)} if the mCommonBannerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mCommonBannerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-common-banners")
    public ResponseEntity<MCommonBannerDTO> updateMCommonBanner(@Valid @RequestBody MCommonBannerDTO mCommonBannerDTO) throws URISyntaxException {
        log.debug("REST request to update MCommonBanner : {}", mCommonBannerDTO);
        if (mCommonBannerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MCommonBannerDTO result = mCommonBannerService.save(mCommonBannerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mCommonBannerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-common-banners} : get all the mCommonBanners.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mCommonBanners in body.
     */
    @GetMapping("/m-common-banners")
    public ResponseEntity<List<MCommonBannerDTO>> getAllMCommonBanners(MCommonBannerCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MCommonBanners by criteria: {}", criteria);
        Page<MCommonBannerDTO> page = mCommonBannerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-common-banners/count} : count all the mCommonBanners.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-common-banners/count")
    public ResponseEntity<Long> countMCommonBanners(MCommonBannerCriteria criteria) {
        log.debug("REST request to count MCommonBanners by criteria: {}", criteria);
        return ResponseEntity.ok().body(mCommonBannerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-common-banners/:id} : get the "id" mCommonBanner.
     *
     * @param id the id of the mCommonBannerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mCommonBannerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-common-banners/{id}")
    public ResponseEntity<MCommonBannerDTO> getMCommonBanner(@PathVariable Long id) {
        log.debug("REST request to get MCommonBanner : {}", id);
        Optional<MCommonBannerDTO> mCommonBannerDTO = mCommonBannerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mCommonBannerDTO);
    }

    /**
     * {@code DELETE  /m-common-banners/:id} : delete the "id" mCommonBanner.
     *
     * @param id the id of the mCommonBannerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-common-banners/{id}")
    public ResponseEntity<Void> deleteMCommonBanner(@PathVariable Long id) {
        log.debug("REST request to delete MCommonBanner : {}", id);
        mCommonBannerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
