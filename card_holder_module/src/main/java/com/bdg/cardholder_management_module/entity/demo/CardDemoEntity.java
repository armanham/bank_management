package com.bdg.cardholder_management_module.entity.demo;

import com.bdg.cardholder_management_module.entity.CardHolderEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "card_demo")
public class CardDemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "card_holder_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_card_card_holder_id")
            )
    private CardHolderEntity cardHolder;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public CardHolderEntity getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(CardHolderEntity cardHolder) {
        this.cardHolder = cardHolder;
    }
}