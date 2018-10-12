package org.metafacture.morphviz;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new MorphVizCommand());
        commandLine.parse(args);

        if (commandLine.isUsageHelpRequested()) {
            CommandLine.usage(new MorphVizCommand(), System.out);
            return;
        }

        if (commandLine.isVersionHelpRequested()) {
            commandLine.printVersionHelp(System.out);
            return;
        }

        MorphVizCommand app = CommandLine.populateCommand(new MorphVizCommand(), args);
        app.run();
    }
}
