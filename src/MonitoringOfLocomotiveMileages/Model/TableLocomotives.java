package MonitoringOfLocomotiveMileages.Model;

public class TableLocomotives {

    String series;
    String number;
    String status;
    int oldMileage;
    int newMileage;
    int averageMileage;
    int remainingMileageDays;
    int remainingMileage;
    String endDateOfTheMileage;


    public String getSeries() {
        return series;
    }

    public String getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public int getOldMileage() {
        return oldMileage;
    }

    public int getNewMileage() {
        return newMileage;
    }


    public int getRemainingMileage() {
        return remainingMileage;
    }

    public int getAverageMileage() {
        return averageMileage;
    }

    public int getRemainingMileageDays() {
        return remainingMileageDays;
    }

    public String getEndDateOfTheMileage() {
        return endDateOfTheMileage;
    }


    public TableLocomotives(Builder builder) {
        this.series = builder.series;
        this.number = builder.number;
        this.status = builder.status;
        this.oldMileage = builder.oldMileage;
        this.newMileage = builder.newMileage;
        this.remainingMileage = builder.remainingMileage;
        this.averageMileage = builder.averageMileage;
        this.remainingMileageDays = builder.remainingMileageDays;
        this.endDateOfTheMileage = builder.endDateOfTheMileage;
    }


    public static class Builder {

        String series;
        String number;
        String status;
        int oldMileage;
        int newMileage;
        int remainingMileage;
        int averageMileage;
        int remainingMileageDays;
        String endDateOfTheMileage;


        public Builder setSeries(String series) {
            this.series = series;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setOldMileage(int mileage) {
            this.oldMileage = mileage;
            return this;
        }

        public Builder setNewMileage(int mileage) {
            this.newMileage = mileage;
            return this;
        }

        public Builder setRemainingMileage(int remainingMileage) {
            this.remainingMileage = remainingMileage;
            return this;
        }


        public Builder setAverageMileage(int averageMileage) {
            this.averageMileage = averageMileage;
            return this;
        }

        public Builder setRemainingMileageDays(int remainingMileageDays) {
            this.remainingMileageDays = remainingMileageDays;
            return this;
        }

        public Builder setEndDateOfTheMileage(String endDateOfTheMileage) {
            this.endDateOfTheMileage = endDateOfTheMileage;
            return this;
        }

        public TableLocomotives build() {
            return new TableLocomotives(this);
        }






    }
}
