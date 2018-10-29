package ru.zakharov.samboss.nmapparser;

import org.nmap4j.core.flags.Flag;
import org.nmap4j.core.nmap.ExecutionResults;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;
import org.nmap4j.core.scans.BaseScan;
import org.nmap4j.core.scans.IScan;
import org.nmap4j.core.scans.ParameterValidationFailureException;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Nmap4jScan {

    private String scanResults = "";
    private BaseScan baseScan;
    private MyScan myScan;
    private File file = new File("d:\\samboss\\scans\\" + new Date().hashCode() + ".xml");
    private FileWriter fileWriter;

    public String getScanResults() {
        return scanResults;
    }

    public void doSimpleScan() {


        try {

            myScan = new MyScan("C:\\Program Files (x86)\\Nmap");
            //baseScan = new BaseScan("C:\\Program Files (x86)\\Nmap");
            //myScan.includeHosts(new String[]{"scanme.nmap.org"});
            //baseScan.includeHosts(new String[]{"127.0.0.5", "127.0.0.6", "127.0.0.7"});
            myScan.includeHost("127.0.0.1-10");
            //baseScan.addPorts(new int[]{22, 80, 443, 3306, 3389});
            //baseScan.setTiming(IScan.TimingFlag.NORMAL);
            myScan.addFlag(Flag.AGGRESIVE_TIMING);
            myScan.addFlag(Flag.A_FLAG);
            //myScan.addFlag(Flag.PORT_SPEC,"1-65535");
            myScan.addFlag(Flag.TOP_PORTS,"10");

            //baseScan.addFlag(Flag.A_FLAG);
            //baseScan.addFlag(Flag.XML_OUTPUT);
            //baseScan.setTiming(IScan.TimingFlag.AGGRESSIVE);
            //baseScan.setOutputType(IScan.OutputType.XML, file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("file not found");
        }


        try {
            ExecutionResults results = myScan.executeScan();
            System.out.println(results.getExecutedCommand());
            System.out.println(results.getOutput());
            scanResults = results.getOutput();

            if (results.hasErrors()) {
                System.out.println("Errors: " + results.getErrors());
            } else {
                fileWriter = new FileWriter(file);
                fileWriter.write(scanResults);

                //System.out.println("Results: " + results.getOutput());
            }

        } catch (ParameterValidationFailureException |
                NMapExecutionException |
                NMapInitializationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
