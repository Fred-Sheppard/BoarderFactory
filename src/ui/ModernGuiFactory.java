package ui;

public class ModernGuiFactory implements GuiFactory {
    @Override
    public PersonGui createPersonGui() {
        return new ModernPersonGui();
    }
    
    @Override
    public AisleGui createAisleGui() {
        return new ModernAisleGui();
    }
    
    @Override
    public SeatGui createSeatGui(String type) {
        return new ModernSeatGui(type);
    }
}
