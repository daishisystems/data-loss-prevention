package com.dlp.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.cloud.ServiceOptions;
import com.google.privacy.dlp.v2.InfoType;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class App {
    public static void main(String[] args) throws Exception {

        // OptionGroup optionsGroup = new OptionGroup();
        // optionsGroup.setRequired(true);

        // Option deidentifyMaskingOption = new Option("m", "mask", true, "Deidentify
        // with character masking.");
        // optionsGroup.addOption(deidentifyMaskingOption);

        // Options commandLineOptions = new Options();
        // commandLineOptions.addOptionGroup(optionsGroup);

        // Option infoTypesOption =
        // Option.builder("infoTypes").hasArg(true).required(false).build();
        // infoTypesOption.setArgs(Option.UNLIMITED_VALUES);
        // commandLineOptions.addOption(infoTypesOption);

        // Option maskingCharacterOption =
        // Option.builder("maskingCharacter").hasArg(true).required(false).build();
        // commandLineOptions.addOption(maskingCharacterOption);

        // Option surrogateTypeOption =
        // Option.builder("surrogateType").hasArg(true).required(false).build();
        // commandLineOptions.addOption(surrogateTypeOption);

        // Option numberToMaskOption =
        // Option.builder("numberToMask").hasArg(true).required(false).build();
        // commandLineOptions.addOption(numberToMaskOption);

        // Option projectIdOption =
        // Option.builder("projectId").hasArg(true).required(false).build();
        // commandLineOptions.addOption(projectIdOption);

        // CommandLineParser parser = new DefaultParser();
        // HelpFormatter formatter = new HelpFormatter();
        // CommandLine cmd;

        // try {
        // cmd = parser.parse(commandLineOptions, args);
        // } catch (ParseException e) {
        // System.out.println(e.getMessage());
        // formatter.printHelp(DeIdentification.class.getName(), commandLineOptions);
        // System.exit(1);
        // return;
        // }

        // default to auto-detected project id when not explicitly provided
        // String projectId = cmd.getOptionValue(projectIdOption.getOpt(),
        // ServiceOptions.getDefaultProjectId());

        int numberToMask = 0;
        char maskingCharacter = '#';
        String val = "Hello, my email address is somebody@somewhere.com and my number is 085-7198398."; // todo: mask
                                                                                                        // other fields
                                                                                                        // and identify
                                                                                                        // in Order
                                                                                                        // JSON. Create
                                                                                                        // custom
                                                                                                        // template
        List<InfoType> infoTypesList = Collections.emptyList();
        String masked = DeIdentification.deIdentifyWithMask(val, infoTypesList, maskingCharacter, numberToMask,
                "eshop-bigdata");
    }
}
