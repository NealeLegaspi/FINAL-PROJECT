package departmentstore;

class Cashier {
    private int number;
    private boolean occupied;

    Cashier(int number) {
        this.number = number;
        this.occupied = false;
    }

    int getNumber() {
        return number;
    }

    boolean isOccupied() {
        return occupied;
    }

    void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
