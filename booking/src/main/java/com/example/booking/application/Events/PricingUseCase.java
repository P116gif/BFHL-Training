package com.example.booking.application.Events;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.booking.domain.models.SeatCategory;
import com.example.booking.domain.repository.ShowRepository;
import com.example.booking.domain.repository.ShowSeatPriceRepository;
import com.example.booking.infrastructure.persistence.Entities.ShowEntity;
import com.example.booking.infrastructure.persistence.Entities.ShowSeatPriceEntity;

@Service
public class PricingUseCase {
    
    private final ShowSeatPriceRepository priceRepository;
    private final ShowRepository showRepository;

    public PricingUseCase(ShowSeatPriceRepository priceRepo, ShowRepository showRepository) {
        this.priceRepository = priceRepo;
        this.showRepository = showRepository;
    }

    // ADMIN ONLY
    public ShowSeatPriceEntity setPrice(Long showId, SeatCategory category, BigDecimal price) {

        ShowSeatPriceEntity seatPrice = priceRepository
                        .findByShowIdAndCategory(showId, category)
                        .orElse(new ShowSeatPriceEntity());
        
        ShowEntity show = showRepository.findById(showId)
                            .orElseThrow(() -> new RuntimeException("Show not found by Id"));

        seatPrice.setShow(show);
        seatPrice.setCategory(category);
        seatPrice.setPrice(price);

        return priceRepository.save(seatPrice);
    }

    public BigDecimal getPrice(Long showId, SeatCategory category) {
        return priceRepository.findByShowIdAndCategory(showId, category)
                .orElseThrow(() -> new RuntimeException("Price not set"))
                .getPrice();
    }

}
