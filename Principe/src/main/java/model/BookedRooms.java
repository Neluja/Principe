package model;

public class BookedRooms {

    public int getBookedRoomsId() {
		return bookedRoomsId;
	}
	public void setBookedRoomsId(int bookedRoomsId) {
		this.bookedRoomsId = bookedRoomsId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	private int bookedRoomsId;
    private int bookingId;
    private String roomId;

    // Getters and Setters
}