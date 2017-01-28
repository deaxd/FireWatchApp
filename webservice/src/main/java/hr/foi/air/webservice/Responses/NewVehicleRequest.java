package hr.foi.air.webservice.Responses;

/**
 * Created by Matija on 28/01/2017.
 */

public class NewVehicleRequest {

    private String vName;
    private int vSeatNum;
    private int vWaterVolume;
    private String vKindOf;

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public int getvSeatNum() {
        return vSeatNum;
    }

    public void setvSeatNum(int vSeatNum) {
        this.vSeatNum = vSeatNum;
    }

    public int getvWaterVolume() {
        return vWaterVolume;
    }

    public void setvWaterVolume(int vWaterVolume) {
        this.vWaterVolume = vWaterVolume;
    }

    public String getvKindOf() {
        return vKindOf;
    }

    public void setvKindOf(String vKindOf) {
        this.vKindOf = vKindOf;
    }
}
