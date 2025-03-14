package ui;

public interface AbstractGuiFactory {
    PersonGui createPersonGui(int cols);
    AisleGui createAisleGui(int rows, int columns);
    SeatGui createSeatGui(int seatsPerColumn);
} 