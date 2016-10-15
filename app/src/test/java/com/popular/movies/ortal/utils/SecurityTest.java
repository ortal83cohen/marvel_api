package com.popular.movies.ortal.utils;


import com.marvel.api.ortal.utils.Security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SecurityTest {

    @Test
    public void md5() {
        assertEquals(Security.md5("ThisIsTest1"), "1fb81916b94ae73ddd71ac6fcf5a6e01");
    }


}
