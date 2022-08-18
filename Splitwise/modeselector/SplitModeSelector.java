package Designs.Splitwise.modeselector;

import Designs.Splitwise.splitmodes.SplitMode;

import java.util.Map;

public class SplitModeSelector {
    private Map<SplitTypes, SplitMode> map;

    public SplitModeSelector(Map<SplitTypes, SplitMode> map) {
        this.map = map;
    }

    public SplitMode select(SplitTypes type) {
        return map.get(type);
    }
}
