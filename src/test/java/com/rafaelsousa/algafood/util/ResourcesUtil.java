package com.rafaelsousa.algafood.util;

import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ResourcesUtil {

    public static String getContentFromResource(String resourceName) {
        try {
            InputStream stream = ResourceUtils.class.getResourceAsStream(resourceName);
            return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
