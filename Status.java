public enum Status {
    PENDING("В ожидании", 1),
    PROCESSING("В обработке", 2), 
    COMPLETED("Завершено", 3),
    CANCELLED("Отменено", 0);
    
    private final String description;
    private final int code;
    
    Status(String description, int code) {
        this.description = description;
        this.code = code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getCode() {
        return code;
    }
    
    public static Status getByCode(int code) {
        for (Status status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }
}