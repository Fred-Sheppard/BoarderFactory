package ui;

public interface GuiFactory {
    PersonGui createPersonGui();
    AisleGui createAisleGui();
    SeatGui createSeatGui(String type);
} 