package it.univr.ipertensione_hope.Model;

public enum BloodPressureCategory {
    NORMAL("Normal", 0),
    ELEVATED("Elevated", 1),
    HIGH_BLOOD_PRESSURE_STAGE_1("High Blood Pressure Stage 1", 2),
    HYPERTENSION_STAGE_1("Hypertension Stage 1", 3),
    HYPERTENSION_STAGE_2("Hypertension Stage 2", 4),
    HYPERTENSIVE_CRISIS("Hypertensive Crisis", 5);

    private final String description;
    private final int value;

    BloodPressureCategory(String description, int value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }
}
