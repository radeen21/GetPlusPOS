package id.mygetplus.getpluspos.service.response;

import id.mygetplus.getpluspos.ResponsePojo;

public class WrapperLogin extends ResponsePojo {

    private UserData userData;
    private DeviceData deviceData;

    public class UserData {
        private String Username;
        private String Password;

        public String getUsername() {
            return Username;
        }

        public void setUsername(String username) {
            Username = username;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }
    }

    public class DeviceData {
        private String Brand;
        private String Imei;
        private String Serial;
        private String DeviceID;
        private String OS;

        public String getBrand() {
            return Brand;
        }

        public void setBrand(String brand) {
            Brand = brand;
        }

        public String getImei() {
            return Imei;
        }

        public void setImei(String imei) {
            Imei = imei;
        }

        public String getSerial() {
            return Serial;
        }

        public void setSerial(String serial) {
            Serial = serial;
        }

        public String getDeviceID() {
            return DeviceID;
        }

        public void setDeviceID(String deviceID) {
            DeviceID = deviceID;
        }

        public String getOS() {
            return OS;
        }

        public void setOS(String OS) {
            this.OS = OS;
        }
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public DeviceData getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(DeviceData deviceData) {
        this.deviceData = deviceData;
    }
}
