package com.example.inertiaspringpoc;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Counter {
    @Id
    @Nonnull
    private Integer id;
    @Nullable
    private Date updated;
    @Nonnull
    @Column(name = "val")
    private Integer value;

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
