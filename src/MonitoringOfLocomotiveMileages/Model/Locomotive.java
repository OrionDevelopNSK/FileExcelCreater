package MonitoringOfLocomotiveMileages.Model;

public class Locomotive {

    private String series;
    private String number;
    private String location;
    private String status;
    private int mileage;


    public String getSeries() {
        return series;
    }

    public String getNumber() {
        return number;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public int getMileage() {
        return mileage;
    }



    public Locomotive(Builder builder) {
        this.series = builder.series;
        this.number = builder.number;
        this.location = builder.location;
        this.status = builder.status;
        this.mileage = builder.mileage;
    }

    public static class Builder{

        private String series;
        private String number;
        private String location;
        private String status;
        private int mileage;


        public Builder withSeries(String series){
            this.series = series;
            return this;
        }
        public Builder withNumber(String number){

            if (number.length() > 0 ){

                //удаляем нули перед номером если есть
                number = number.replaceFirst("^(?<=^)0+", "");

            }
            this.number = number;
            return this;
        }
        public Builder withLocation(String location){
            this.location = location;
            return this;
        }
        public Builder withStatus(String status){
            this.status = status;
            return this;
        }
        public Builder withMileage(int mileage){
            this.mileage = mileage;
            return this;
        }

        public Locomotive build(){
            return  new Locomotive(this);
        }
    }



}


