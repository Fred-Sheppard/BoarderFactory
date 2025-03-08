package ui;

public class ModernGuiFactory implements AbstractGuiFactory {
    @Override
    public PersonGui createPersonGui() {
        return new ModernPersonGui();
    }
    
    @Override
    public SeatGui createSeatGui() {
        return new ModernSeatGui();
    }
    
    @Override
    public AisleGui createAisleGui() {
        return new ModernAisleGui();
    }
}
