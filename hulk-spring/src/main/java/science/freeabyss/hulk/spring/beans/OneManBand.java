package science.freeabyss.hulk.spring.beans;

import java.util.List;

/**
 * Created by abyss on 08/13/16.
 */
public class OneManBand implements Performer {
    private List<Instrument> instruments;

    @Override
    public void perform() {
        instruments.forEach(x -> x.play());
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }
}
