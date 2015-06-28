package com.elgassia.bridge.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Streams {

    public static byte[] read(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];

        int offset = 0;
        int n;

        while (true) {
            n = inputStream.read(buffer);
            if (n == -1) {
                break;
            }
            byteArrayOutputStream.write(buffer, offset, n);
            offset += n;
        }

        return byteArrayOutputStream.toByteArray();
    }
}
