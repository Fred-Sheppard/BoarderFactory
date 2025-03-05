package ui;

public interface AbstractGuiFactory {
    PersonGui createPersonGui();
    AisleGui createAisleGui();
    SeatGui createSeatGui();
} 