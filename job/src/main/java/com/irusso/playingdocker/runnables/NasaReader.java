package com.irusso.playingdocker.runnables;

import com.irusso.playingdocker.util.Printer;
import com.irusso.playingdocker.http.HttpClient;
import com.irusso.playingdocker.model.GroundStation;
import com.irusso.playingdocker.model.Observatory;
import com.irusso.playingdocker.service.SscService;
import org.apache.http.HttpException;

import java.util.List;
import java.util.Properties;

public class NasaReader {

    private final Properties config;
    private final SscService sscService;

    public NasaReader(Properties config) {
        this.config = config;
        this.sscService = new SscService(config, new HttpClient());
    }

    public void read() {
        try {
            List<GroundStation> groundStations = sscService.getGroundStations();
            System.out.println("SSC Responded with " + groundStations.size() + " Ground Stations");
            List<Observatory> observatories = sscService.getObservatories();
            System.out.println("SSC Responded with " + observatories.size() + " Observatories");
            List<Observatory> spaseObservatories = sscService.getSpaseObservatories();
            System.out.println("SSC Responded with " + spaseObservatories.size() + " SPASE Observatories");

            Printer.printGroundStations(groundStations);
            Printer.printObservatories(observatories);
            Printer.printObservatories(spaseObservatories);
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }
}
