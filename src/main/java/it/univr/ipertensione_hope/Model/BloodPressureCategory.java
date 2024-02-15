package it.univr.ipertensione_hope.Model;

public enum BloodPressureCategory {
    HYPERTENSIVE_CRISIS("Hypertensive Crisis"),
    HYPERTENSION_STAGE_2("Hypertension Stage 2"),
    HYPERTENSION_STAGE_1("Hypertension Stage 1"),
    HIGH_BLOOD_PRESSURE_STAGE_1("High Blood Pressure (Hypertension) Stage 1"),
    ELEVATED("Elevated"),
    NORMAL("Normal");

    private final String label;

    BloodPressureCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
