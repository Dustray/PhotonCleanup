package cn.dustray.memory;

public interface OnMemoryCleanupListener {
    void onCleanupStart();
    void onCleanupStop();
}
