package events;

import java.util.ArrayList;
import java.util.List;

import domain.Refree;

public class Olympics {

    List<Refree> refrees = new ArrayList<Refree>();

    private void prepareRefree() {

        Refree hundredMetersSprintRefree = new Refree();
        hundredMetersSprintRefree.addEvent(new TrackAndFieldEvent("HundredMetersSprint"));

        Refree fourHundredMetersSprintRefree = new Refree();
        fourHundredMetersSprintRefree.addEvent(new TrackAndFieldEvent("FourHundredMetersSprint"));

        Refree thousandMetersSprintRefree = new Refree();
        thousandMetersSprintRefree.addEvent(new TrackAndFieldEvent("ThousandMetersSprint"));

        Refree twoThousandMetersRaceRefree = new Refree();
        twoThousandMetersRaceRefree.addEvent(new TrackAndFieldEvent("TwoThousandMetersRace"));

        Refree fiveThousandMetersSprintRefree = new Refree();
        fiveThousandMetersSprintRefree.addEvent(new TrackAndFieldEvent("fiveThousandMetersSprint"));

        refrees.add(hundredMetersSprintRefree);
        refrees.add(fourHundredMetersSprintRefree);
        refrees.add(thousandMetersSprintRefree);
        refrees.add(twoThousandMetersRaceRefree);
        refrees.add(fiveThousandMetersSprintRefree);

    }

    public void begin() {
        prepareRefree();
        beginEvents();
    }

    private void beginEvents() {
        for (Refree refree : refrees) {
            refree.start();
        }
    }

}
