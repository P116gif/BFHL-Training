package com.example.booking.infrastructure.persistence.Entities;

import java.math.BigDecimal;

import com.example.booking.domain.models.SeatCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="show_seat_prices",
        uniqueConstraints=@UniqueConstraint(
            columnNames={"show_id", "category"}
        )
)
public class ShowSeatPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private ShowEntity show;

    @Enumerated(EnumType.STRING)
    private SeatCategory category;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public ShowEntity getShow() {
        return show;
    }

    public void setShow(ShowEntity show) {
        this.show = show;
    }

    public SeatCategory getCategory() {
        return category;
    }

    public void setCategory(SeatCategory category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    
}
