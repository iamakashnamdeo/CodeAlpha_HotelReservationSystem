Task 4: Hotel Reservation System built using Java during my CodeAlpha Internship.
# 🏨 CodeAlpha Hotel Reservation System

Hi, I am Akash Namdeo, a 2nd-year B.Tech CSE student. This is **Task 4: Hotel Reservation System** for my 1-month Java Programming Virtual Internship at CodeAlpha (Sept 1 - Sept 30).

I have built this project completely from scratch using core Java and Object-Oriented Programming (OOP) concepts.

## 🛠️ How I Built This (Project Logic)
* **`Room` Class**: Created a blueprint to store room details like Room Number, Category (Standard, Deluxe, Luxury), Price, and an availability flag (`isAvailable`).
* **`Reservation` Class**: Handles the booking details. It links the Guest Name to the selected Room and automatically calculates the total bill based on the number of nights. It also generates a unique Booking ID (starting from 1001).
* **`HotelSystem` Class**: This is the main engine. It stores the rooms in an `ArrayList<Room>` and bookings in an `ArrayList<Reservation>`. It runs a `while(true)` loop with a `switch-case` to show a clean menu dashboard to the user.

## ✨ Main Features
1. **Browse Vacant Rooms**: Shows only the rooms that are currently free with their clean pricing table.
2. **Book a New Stay**: Takes guest details, checks if the room is available, changes the room status to booked, and prints a neat receipt.
3. **View My Reservations**: Displays a live ledger list of all the current active bookings in the hotel.
4. **Exit**: Safely closes the application.

## 🚀 How to Run
Simply open your terminal inside the folder and run these commands:
```bash
javac HotelSystem.java
java HotelSystem
```
