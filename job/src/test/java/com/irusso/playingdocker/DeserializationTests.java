package com.irusso.playingdocker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irusso.playingdocker.model.oecd.WealthResponse;
import org.junit.Test;

public class DeserializationTests {

    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        WealthResponse output = objectMapper.readValue(TestData.WEALTH_DISTRIBUTION_RESPONSE, WealthResponse.class);
        System.out.println(output);
    }
}
