package org.metafacture.morphviz;

import picocli.CommandLine;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class ManifestVersionProvider implements CommandLine.IVersionProvider {
    public String[] getVersion() throws Exception
    {
        Enumeration<URL> resources = CommandLine.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
        if (resources.hasMoreElements())
        {
            URL url = resources.nextElement();
            try
            {
                Manifest manifest = new Manifest(url.openStream());
                Attributes attr = manifest.getMainAttributes();

                Object version = get(attr, "Implementation-Version");
                String result = version == null ? "Not provided." : (String) version;
                return new String[] { "Implementation-Version: " + result };
            }
            catch (IOException ex)
            {
                return new String[] { "Unable to read from " + url + ": " + ex };
            }
        }
        return new String[] { "Unknown" };
    }

    private static Object get(Attributes attributes, String key) {
        return attributes.get(new Attributes.Name(key));
    }
}
