package com.example.inertiaspringpoc;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Counter {
    @Id
    @Nonnull
    private Integer id;
    @Nullable
    private Date updated;
    @Nonnull
    @Column(name = "val")
    private Integer value;

    public Counter(Integer id, Integer value) {
        this.id = id;
        this.value = value;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
