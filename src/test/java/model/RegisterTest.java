package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {

    private Register register;

    @BeforeEach
    void setUp() {
        register = new Register();
    }

    @Test
    void constructor_ShouldInitializeTotalToZero() {
        assertEquals(0f, register.getTotal(), 0.0001f);
    }

    @Test
    void setAndGetTotal_ShouldWorkCorrectly() {
        register.setTotal(100.5f);
        assertEquals(100.5f, register.getTotal(), 0.0001f);
    }

    @Test
    void updateTotal_ShouldIncreaseTotalCorrectly() {
        register.updateTotal(50f);
        assertEquals(50f, register.getTotal(), 0.0001f);

        register.updateTotal(25.25f);
        assertEquals(75.25f, register.getTotal(), 0.0001f);
    }

    @Test
    void updateTotal_WithNegativeValue_ShouldDecreaseTotalCorrectly() {
        register.updateTotal(100f);
        register.updateTotal(-40f);
        assertEquals(60f, register.getTotal(), 0.0001f);
    }
}
