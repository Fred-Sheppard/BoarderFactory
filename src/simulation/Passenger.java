package simulation;

public class Passenger {
    private String passengerName;
    private Seat seat;
    private int currentX;
    private int currentY;
    
    // Cache target position coordinates
    private int targetSimX;
    private int targetSimY;

    public Passenger(String passengerName, Seat seat) {
        this.passengerName = passengerName;
        this.seat = seat;
        
        // Calculate target position when passenger is created
        calculateTargetPosition();
    }
    
    
    //  Calculates where this passenger needs to go in simulation coordinates
     
    private void calculateTargetPosition() {
        int targetRow = seat.row();
        int targetCol = seat.col();
        
        // Calculate target X based on seat column
        if (targetCol < 3) {
            // Left side columns (0,1,2) map to x=(-15,-10,-5)
            targetSimX = -15 + (targetCol * 5);
        } else {
            // Right side columns (3,4,5) map to x=(5,10,15)
            targetSimX = 5 + ((targetCol-3) * 5);
        }
        
        // Calculate target Y with the +4 offset
        targetSimY = targetRow + 4;
    }

    public Seat getSeat() {
        return seat;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }
    
    public void moveTowardDestination() {
        // First move down until reaching row
        if (currentY < targetSimY) {
            currentY++;
        }
        // Then move to the proper X position
        else if (currentY == targetSimY) {
            if (currentX < targetSimX) {
                currentX++;
            } else if (currentX > targetSimX) {
                currentX--;
            }
        }
    }

    public void setCurrentPosition(int x, int y) {
        this.currentX = x;
        this.currentY = y;
    }
}
