package io.shm.tsubasa.web.rest;

import io.shm.tsubasa.service.MRecommendShopMessageService;
import io.shm.tsubasa.web.rest.errors.BadRequestAlertException;
import io.shm.tsubasa.service.dto.MRecommendShopMessageDTO;
import io.shm.tsubasa.service.dto.MRecommendShopMessageCriteria;
import io.shm.tsubasa.service.MRecommendShopMessageQueryService;

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
 * REST controller for managing {@link io.shm.tsubasa.domain.MRecommendShopMessage}.
 */
@RestController
@RequestMapping("/api")
public class MRecommendShopMessageResource {

    private final Logger log = LoggerFactory.getLogger(MRecommendShopMessageResource.class);

    private static final String ENTITY_NAME = "mRecommendShopMessage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MRecommendShopMessageService mRecommendShopMessageService;

    private final MRecommendShopMessageQueryService mRecommendShopMessageQueryService;

    public MRecommendShopMessageResource(MRecommendShopMessageService mRecommendShopMessageService, MRecommendShopMessageQueryService mRecommendShopMessageQueryService) {
        this.mRecommendShopMessageService = mRecommendShopMessageService;
        this.mRecommendShopMessageQueryService = mRecommendShopMessageQueryService;
    }

    /**
     * {@code POST  /m-recommend-shop-messages} : Create a new mRecommendShopMessage.
     *
     * @param mRecommendShopMessageDTO the mRecommendShopMessageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mRecommendShopMessageDTO, or with status {@code 400 (Bad Request)} if the mRecommendShopMessage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-recommend-shop-messages")
    public ResponseEntity<MRecommendShopMessageDTO> createMRecommendShopMessage(@Valid @RequestBody MRecommendShopMessageDTO mRecommendShopMessageDTO) throws URISyntaxException {
        log.debug("REST request to save MRecommendShopMessage : {}", mRecommendShopMessageDTO);
        if (mRecommendShopMessageDTO.getId() != null) {
            throw new BadRequestAlertException("A new mRecommendShopMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MRecommendShopMessageDTO result = mRecommendShopMessageService.save(mRecommendShopMessageDTO);
        return ResponseEntity.created(new URI("/api/m-recommend-shop-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-recommend-shop-messages} : Updates an existing mRecommendShopMessage.
     *
     * @param mRecommendShopMessageDTO the mRecommendShopMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mRecommendShopMessageDTO,
     * or with status {@code 400 (Bad Request)} if the mRecommendShopMessageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mRecommendShopMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-recommend-shop-messages")
    public ResponseEntity<MRecommendShopMessageDTO> updateMRecommendShopMessage(@Valid @RequestBody MRecommendShopMessageDTO mRecommendShopMessageDTO) throws URISyntaxException {
        log.debug("REST request to update MRecommendShopMessage : {}", mRecommendShopMessageDTO);
        if (mRecommendShopMessageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MRecommendShopMessageDTO result = mRecommendShopMessageService.save(mRecommendShopMessageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mRecommendShopMessageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /m-recommend-shop-messages} : get all the mRecommendShopMessages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mRecommendShopMessages in body.
     */
    @GetMapping("/m-recommend-shop-messages")
    public ResponseEntity<List<MRecommendShopMessageDTO>> getAllMRecommendShopMessages(MRecommendShopMessageCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MRecommendShopMessages by criteria: {}", criteria);
        Page<MRecommendShopMessageDTO> page = mRecommendShopMessageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /m-recommend-shop-messages/count} : count all the mRecommendShopMessages.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/m-recommend-shop-messages/count")
    public ResponseEntity<Long> countMRecommendShopMessages(MRecommendShopMessageCriteria criteria) {
        log.debug("REST request to count MRecommendShopMessages by criteria: {}", criteria);
        return ResponseEntity.ok().body(mRecommendShopMessageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /m-recommend-shop-messages/:id} : get the "id" mRecommendShopMessage.
     *
     * @param id the id of the mRecommendShopMessageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mRecommendShopMessageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-recommend-shop-messages/{id}")
    public ResponseEntity<MRecommendShopMessageDTO> getMRecommendShopMessage(@PathVariable Long id) {
        log.debug("REST request to get MRecommendShopMessage : {}", id);
        Optional<MRecommendShopMessageDTO> mRecommendShopMessageDTO = mRecommendShopMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mRecommendShopMessageDTO);
    }

    /**
     * {@code DELETE  /m-recommend-shop-messages/:id} : delete the "id" mRecommendShopMessage.
     *
     * @param id the id of the mRecommendShopMessageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-recommend-shop-messages/{id}")
    public ResponseEntity<Void> deleteMRecommendShopMessage(@PathVariable Long id) {
        log.debug("REST request to delete MRecommendShopMessage : {}", id);
        mRecommendShopMessageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
