package com.github.phasebash.katas.vendingmachine.core

import org.gmock.WithGMock
import org.junit.Before
import org.junit.Test

/**
 * Tests of the VendingMachine class.
 */
@WithGMock
class VendingMachineTest {

    private VendingMachine machine

    @Before
    void setUp() {
        machine = new VendingMachine()
    }

    @Test
    void 'should have a test that matters...'() {
        // stub test until we have an implementation.
        assert machine
    }
}
