class Type1Adjustment extends SalaryAdjust {
    public Type1Adjustment() {
    }

    @Override
    public double adjust(double raise) {
        if (raise > 10) {
            return 10;
        }
        else if (raise < 0) {
            return 0;
        }
        else {
            return raise;
        }
    }
}
