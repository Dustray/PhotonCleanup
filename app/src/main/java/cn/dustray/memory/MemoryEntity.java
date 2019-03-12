package cn.dustray.memory;

public class MemoryEntity {
    private String totalMemory;
    private String availableMemory;
    private String canBeCleanMemory;
    private boolean isLowMemory;
    private float minimumMemoryScale;

    public String getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    public String getAvailableMemory() {
        return availableMemory;
    }

    public void setAvailableMemory(String availableMemory) {
        this.availableMemory = availableMemory;
    }

    public String getCanBeCleanMemory() {
        return canBeCleanMemory;
    }

    public void setCanBeCleanMemory(String canBeCleanMemory) {
        this.canBeCleanMemory = canBeCleanMemory;
    }

    public boolean isLowMemory() {
        return isLowMemory;
    }

    public void setLowMemory(boolean lowMemory) {
        isLowMemory = lowMemory;
    }

    public float getMinimumMemoryScale() {
        return minimumMemoryScale;
    }

    public void setMinimumMemoryScale(float minimumMemoryScale) {
        this.minimumMemoryScale = minimumMemoryScale;
    }
}
