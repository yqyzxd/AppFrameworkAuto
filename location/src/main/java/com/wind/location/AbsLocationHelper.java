package com.wind.location;

import android.content.Context;

/**
 * Created by wind on 2018/1/2.
 */

public abstract class AbsLocationHelper {


    public enum Server{
        SERVER_BAIDU,SERVER_GAODE
    }
    public abstract void startLocation(LocationListener listener);

    public interface LocationListener{
       public void location(LocationInfo locationInfo);
    }


    public static AbsLocationHelper getInstance(Context context,Server server){
        AbsLocationHelper instance=null;
        switch (server){
            case SERVER_BAIDU:
                instance=BaiduLocationHelper.getInstance(context);
                break;
            case SERVER_GAODE:
                instance=GaoDeLocationHelper.getInstance(context);
                break;
        }
        return instance;
    }

    public static class LocationInfo{
        private double latitude;
        private double longitude;
        private String province;
        private String city;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
