# 🚆 Online Reservation System

## 📌 Overview

The Online Reservation System is a Java Swing desktop application developed as part of the **Oasis Infobyte Java Development Internship**. It enables users to search trains, book tickets, generate PNR numbers, and cancel reservations while storing all reservation details in a MySQL database.

---

## ✨ Features

- 🔐 User Login
- 🚆 Fetch Train Details
- 🎫 Ticket Booking
- 🆔 Automatic PNR Generation
- ❌ Reservation Cancellation
- 💾 MySQL Database Integration
- ✅ Input Validation
- 🔍 Train Search by Train Number

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| Java | Programming Language |
| Java Swing | GUI Development |
| JDBC | Database Connectivity |
| MySQL | Database |
| Eclipse IDE | Development Environment |
| Git & GitHub | Version Control |

---

## 📂 Project Structure

```
OnlineReservationSystem
│
├── src
│   └── com
│       └── oasis
│           ├── db
│           │    └── DBConnection.java
│           │
│           ├── ui
│           │    ├── LoginFrame.java
│           │    ├── ReservationFrame.java
│           │    └── CancellationFrame.java
│           │
│           └── Main.java
│
├── online_reservation_system.sql
├── README.md
└── screenshots
```

---

## 🗄️ Database Setup

1. Open MySQL Workbench.
2. Create a database named:

```sql
online_reservation_system
```

3. Import the provided SQL file:

```
online_reservation_system.sql
```

4. Update the database username and password in:

```
DBConnection.java
```

---

## ▶️ How to Run

1. Clone the repository.

```
git clone https://github.com/Dhanvithakn6/OIBSIP.git
```

2. Open the project in Eclipse.

3. Import the MySQL database.

4. Configure database credentials in `DBConnection.java`.

5. Run:

```
Main.java
```

---

## 📸 Application Screenshots

### Login Page

*(Add login screenshot here)*

---

### Reservation Window

*(Add reservation screenshot here)*

---

### Booking Confirmation

*(Add booking success screenshot here)*

---

### Cancellation Window

*(Add cancellation screenshot here)*

---

## 🚀 Future Enhancements

- OTP-based Login Authentication
- Password Encryption
- Seat Availability Checking
- Admin Dashboard
- Email Confirmation after Booking
- Date Picker Calendar
- Responsive User Interface
- Train Search using Source & Destination
- Booking History
- Passenger Profile Management

---

## 👩‍💻 Author

**Dhanvitha K N**

Java Developer | Computer Science & Business Systems Student

GitHub: https://github.com/Dhanvithakn6