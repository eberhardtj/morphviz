package org.metafacture.morphviz;

import org.culturegraph.mf.morph.MorphVisualizer;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DotFormatExporter {
    public static void export(InputStream inputStream, OutputStream outputStream) {
        Charset utf8 = StandardCharsets.UTF_8;
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, utf8);

        MorphVisualizer visualizer = new MorphVisualizer(writer);
        InputSource inputSource = new InputSource(inputStream);

        visualizer.walk(inputSource);
    }
}
