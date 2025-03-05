package ui;

public class LegacyGuiFactory implements AbstractGuiFactory {
    @Override
    public PersonGui createPersonGui() {
        return new LegacyPersonGui();
    }
    
    @Override
    public AisleGui createAisleGui() {
        return new LegacyAisleGui();
    }
    
    @Override
    public SeatGui createSeatGui() {
        return new LegacySeatGui();
    }
}