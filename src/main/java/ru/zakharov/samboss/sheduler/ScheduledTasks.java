package ru.zakharov.samboss.sheduler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.zakharov.samboss.nmapparser.Nmap;
import ru.zakharov.samboss.tools.FileTool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private List<String> filesList;
    private Nmap nmap;

    public void setNmap(Nmap nmap) {
        this.nmap = nmap;
    }

    private FileTool fileTool;

    public void setFileTool(FileTool fileTool) {
        this.fileTool = fileTool;
    }

    @Scheduled(fixedRate = 60000)
    public void doScan() {
        log.info("It is time {} , and SAMBOSS do Scan.", dateFormat.format(new Date()));
        nmap.doScan();
    }

    @Scheduled(fixedRate = 30000)
    public void parseScanResults() {
        log.info("It is time  {} , and SAMBOSS are parsing previous scan results.", dateFormat.format(new Date()));
        filesList = fileTool.findFilesInDirectory("D:\\samboss\\scans\\", "*.xml");
        if (filesList.size() > 0) {
            for (String file : filesList) {
                nmap.parse(file);
                fileTool.copyFile("D:\\samboss\\scans\\",file,"D:\\samboss\\scans\\parsed\\");
                fileTool.deleteFile("D:\\samboss\\scans\\", file);
            }
        }
    }
}
