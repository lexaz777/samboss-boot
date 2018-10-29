package ru.zakharov.samboss.nmapparser;

import org.nmap4j.core.flags.Flag;
import org.nmap4j.core.scans.BaseScan;


public class MyScan extends BaseScan {
    public MyScan(String s) {
        super(s);
    }

    public void addFlag(Flag flag, String value) {
        argProps.addFlag(flag,value);
    }
}
