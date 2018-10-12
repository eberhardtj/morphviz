package org.metafacture.morphviz;

import picocli.CommandLine;

import java.io.*;

@CommandLine.Command(
        description = "Exports Metamorph-XML into DOT format.",
        name = "morphviz",
        versionProvider = ManifestVersionProvider.class)
public class MorphVizCommand implements Runnable {

    @CommandLine.Option(
            names = {"-i", "--input"},
            description = "Input file. Use '-' for stdin.",
            required = true)
    File inputFile;

    @CommandLine.Option(
            names = {"-o", "--output"},
            description = "Output file. Use '-' for stdout. Default: stdout.",
            required = false)
    File outputFile;

    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true, description = "Display version info.")
    boolean versionInfoRequested;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this help message.")
    boolean usageHelpRequested;

    @Override
    public void run() {
        boolean useStdin = inputFile.getName().equals("-");
        boolean useStdout = false;
        if (outputFile == null || outputFile.getName().equals("-")) {
            useStdout = true;
        }

        try (InputStream morphFileStream = useStdin ? System.in : new FileInputStream(inputFile);
             OutputStream asDotFileStream = useStdout ? System.out : new FileOutputStream(outputFile))
        {
            DotFormatExporter.export(morphFileStream, asDotFileStream);
        } catch (IOException e) {
            System.err.println("Could not process file " + inputFile.getName() + " ." +
                    e.getMessage());
            System.exit(1);
        }
    }
}
