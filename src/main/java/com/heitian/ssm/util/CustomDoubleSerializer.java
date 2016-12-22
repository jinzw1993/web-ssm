package com.heitian.ssm.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by oasis on 2016/12/22.
 */
public class CustomDoubleSerializer extends JsonSerializer<Double> {
    private DecimalFormat df = new DecimalFormat("##.00");

    public void serialize(Double value,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeString(df.format(value));
    }
}
