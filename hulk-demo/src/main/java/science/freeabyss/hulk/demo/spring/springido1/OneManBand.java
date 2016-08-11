package science.freeabyss.hulk.demo.spring.springido1;

import java.util.List;

/**
 * Created by abyss on 3/25/16.
 */
public class OneManBand implements Performer {

    private List<Instrument> instruments;

    @Override
    public void perform() throws PerformanceException {
        for (Instrument instrument : instruments) {
            instrument.play();
        }
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }
}
