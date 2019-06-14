package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MSellCardCoin;
import io.shm.tsubasa.repository.MSellCardCoinRepository;
import io.shm.tsubasa.service.dto.MSellCardCoinDTO;
import io.shm.tsubasa.service.mapper.MSellCardCoinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MSellCardCoin}.
 */
@Service
@Transactional
public class MSellCardCoinService {

    private final Logger log = LoggerFactory.getLogger(MSellCardCoinService.class);

    private final MSellCardCoinRepository mSellCardCoinRepository;

    private final MSellCardCoinMapper mSellCardCoinMapper;

    public MSellCardCoinService(MSellCardCoinRepository mSellCardCoinRepository, MSellCardCoinMapper mSellCardCoinMapper) {
        this.mSellCardCoinRepository = mSellCardCoinRepository;
        this.mSellCardCoinMapper = mSellCardCoinMapper;
    }

    /**
     * Save a mSellCardCoin.
     *
     * @param mSellCardCoinDTO the entity to save.
     * @return the persisted entity.
     */
    public MSellCardCoinDTO save(MSellCardCoinDTO mSellCardCoinDTO) {
        log.debug("Request to save MSellCardCoin : {}", mSellCardCoinDTO);
        MSellCardCoin mSellCardCoin = mSellCardCoinMapper.toEntity(mSellCardCoinDTO);
        mSellCardCoin = mSellCardCoinRepository.save(mSellCardCoin);
        return mSellCardCoinMapper.toDto(mSellCardCoin);
    }

    /**
     * Get all the mSellCardCoins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MSellCardCoinDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MSellCardCoins");
        return mSellCardCoinRepository.findAll(pageable)
            .map(mSellCardCoinMapper::toDto);
    }


    /**
     * Get one mSellCardCoin by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MSellCardCoinDTO> findOne(Long id) {
        log.debug("Request to get MSellCardCoin : {}", id);
        return mSellCardCoinRepository.findById(id)
            .map(mSellCardCoinMapper::toDto);
    }

    /**
     * Delete the mSellCardCoin by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MSellCardCoin : {}", id);
        mSellCardCoinRepository.deleteById(id);
    }
}
