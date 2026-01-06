package com.rsa.expense.tracker.model;

import java.time.Instant;

public interface BaseEntityAuditable {

    Long getId();

    void setId(Long id);

    Instant getCreatedAt();

    void setCreatedAt(Instant now);

    Instant getUpdatedAt();

    void setUpdatedAt(Instant now);

}
