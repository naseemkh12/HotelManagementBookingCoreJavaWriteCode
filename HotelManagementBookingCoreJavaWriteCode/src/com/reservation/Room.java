package com.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
	private int number;
	private String type;
	private boolean isOccupied;

	public Room(int number, String type) {
		this.number = number;
		this.type = type;
		this.isOccupied = false;
	}

	public int getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean occupied) {
		isOccupied = occupied;
	}
}

class Guest {
	private String name;
	private String contactNumber;
	private Room room;

	public Guest(String name, String contactNumber, Room room) {
		this.name = name;
		this.contactNumber = contactNumber;
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public Room getRoom() {
		return room;
	}
}

class Hotel {
	private List<Room> rooms;
	private List<Guest> guests;

	public Hotel() {
		rooms = new ArrayList<>();
		guests = new ArrayList<>();
		initializeRooms();
	}

	private void initializeRooms() {
		rooms.add(new Room(101, "Supreme"));
		rooms.add(new Room(102, "lucky"));
		rooms.add(new Room(201, "taj"));
		rooms.add(new Room(202, "Deluxe"));
	}

	public void checkIn(String name, String contactNumber, int roomNumber) {
		Room room = getRoomByNumber(roomNumber);
		if (room != null && !room.isOccupied()) {
			room.setOccupied(true);
			Guest guest = new Guest(name, contactNumber, room);
			guests.add(guest);
			System.out.println("Guest " + guest.getName() + " checked in to Room " + room.getNumber());
		} else {
			System.out.println("Room " + roomNumber + " is not available.");
		}
	}

	public void checkOut(int roomNumber) {
		Room room = getRoomByNumber(roomNumber);
		if (room != null && room.isOccupied()) {
			room.setOccupied(false);
			Guest guest = getGuestByRoomNumber(roomNumber);
			guests.remove(guest);
			System.out.println("Guest " + guest.getName() + " checked out from Room " + room.getNumber());
		} else {
			System.out.println("Room " + roomNumber + " is not occupied.");
		}
	}

	public void displayGuests() {
		for (Guest guest : guests) {
			System.out.println("Guest Name: " + guest.getName() + ", Room Number: " + guest.getRoom().getNumber());
		}
	}

	private Room getRoomByNumber(int roomNumber) {
		for (Room room : rooms) {
			if (room.getNumber() == roomNumber) {
				return room;
			}
		}
		return null;
	}

	private Guest getGuestByRoomNumber(int roomNumber) {
		for (Guest guest : guests) {
			if (guest.getRoom().getNumber() == roomNumber) {
				return guest;
			}
		}
		return null;
	}
}

class HotelManagementSystem {
	public static void main(String[] args) {
		Hotel hotel = new Hotel();
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("1. Check-in");
			System.out.println("2. Check-out");
			System.out.println("3. Display Guests");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.print("Enter guest name: ");
				String name = scanner.next();
				System.out.print("Enter contact number: ");
				String contactNumber = scanner.next();
				System.out.print("Enter room number: ");
				int roomNumber = scanner.nextInt();
				hotel.checkIn(name, contactNumber, roomNumber);
				break;
			case 2:
				System.out.print("Enter room number: ");
				roomNumber = scanner.nextInt();
				hotel.checkOut(roomNumber);
				break;
			case 3:
				hotel.displayGuests();
				break;
			case 4:
				System.out.println("Exiting the system...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 4);
		scanner.close();
	}
}
