class IllegalInstructionException extends IllegalArgumentException {
    String message;

    IllegalInstructionException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "cs2030.catsanddogs.IllegalInstructionException: " + message;
    }
}
