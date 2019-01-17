package cn.repair.dto;

public class Service{
    private String recordId;
    private String serviceDuration;
    private String servicePrice;

    public Service() {
    }

    @Override
    public String toString() {
        return "Service{" +
                "recordId='" + recordId + '\'' +
                ", serviceDuration='" + serviceDuration + '\'' +
                ", servicePrice='" + servicePrice + '\'' +
                '}';
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getServiceDuration() {
        return serviceDuration;
    }

    public void setServiceDuration(String serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }
}
