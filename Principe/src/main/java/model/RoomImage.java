package model;

public class RoomImage {

    public int getRoomImageId() {
		return roomImageId;
	}
	public void setRoomImageId(int roomImageId) {
		this.roomImageId = roomImageId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public byte[] getRoomImage() {
		return roomImage;
	}
	public void setRoomImage(byte[] roomImage) {
		this.roomImage = roomImage;
	}
	private int roomImageId;
    private String roomId;
    private byte[] roomImage;

    // Getters and Setters
}