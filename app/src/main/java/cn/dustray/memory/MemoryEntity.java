package cn.dustray.memory;

public class MemoryEntity {
    private int totalMemory;
    private int availableMemory;
    private boolean isLowMemory;
    private float minimumMemoryScale;

    public int getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(int totalMemory) {
        this.totalMemory = totalMemory;
    }

    public int getAvailableMemory() {
        return availableMemory;
    }

    public void setAvailableMemory(int availableMemory) {
        this.availableMemory = availableMemory;
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
