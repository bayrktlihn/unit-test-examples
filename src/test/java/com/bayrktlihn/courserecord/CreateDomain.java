package com.bayrktlihn.courserecord;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public interface CreateDomain<T> {
    T createDomain();

    @Test
    default void createDomainShouldBeImplemented() {
        assertNotNull(createDomain());
    }
}
