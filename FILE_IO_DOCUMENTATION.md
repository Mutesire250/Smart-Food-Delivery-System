# Smart Food Delivery System - File I/O Implementation

## Overview
This document describes the **File I/O and Data Persistence** features implemented in the Smart Food Delivery System project. The system now supports reading from and writing to files, ensuring data integrity and persistence throughout the application lifecycle.

## Features Implemented

### 1. DataPersistence Class
**Location:** `src/DataPersistence.java`

A comprehensive utility class that handles all file I/O operations for the system. It manages data serialization and deserialization using CSV format.

**Key Methods:**
- `initializeDataDirectory()` - Creates the `data/` directory if it doesn't exist
- `saveCustomer(Customer)` - Writes customer information to `customers.csv`
- `loadCustomers()` - Reads all customers from `customers.csv`
- `saveOrder(Order, String)` - Writes order details to `orders.csv`
- `loadOrders()` - Reads all orders from `orders.csv`
- `saveDelivery(Delivery, String)` - Writes delivery information to `deliveries.csv`
- `loadDeliveries()` - Reads all deliveries from `deliveries.csv`
- `saveMenu(Restaurant)` - Writes menu items to `menu.csv`
- `loadMenu()` - Reads menu items from `menu.csv`
- `displayAllData()` - Displays all saved data in a formatted summary
- `clearAllData()` - Deletes all data files (for testing)

### 2. Data Files Structure

#### `data/customers.csv`
```csv
Name,Email,OrderCount
John Doe,john@example.com,3
Jane Smith,jane@example.com,1
```

#### `data/orders.csv`
```csv
OrderID,ItemName,Price,CustomerName,Timestamp
200,Pizza,12.99,John Doe,1714338965000
201,Burger,8.50,John Doe,1714338975000
```

#### `data/deliveries.csv`
```csv
Location,Status,CustomerName,Timestamp
123 Main St,Delivered,John Doe,1714339015000
456 Oak Ave,Out for Delivery,Jane Smith,1714339025000
```

#### `data/menu.csv`
```csv
ItemName,Price,Restaurant
Pizza,12.99,Miribe Kitchen
Burger,8.50,Miribe Kitchen
Pasta,10.00,Miribe Kitchen
Salad,7.25,Miribe Kitchen
```

### 3. Enhanced Main Class

The `Main.java` has been refactored to provide an interactive menu system that leverages the file I/O capabilities:

**Menu Options:**
1. **Register/Login as Customer** - Creates new customer and saves to file
2. **Place Orders** - Places orders and persists them to file
3. **View Order History** - Displays orders for the current customer
4. **Create Delivery** - Creates delivery and saves to file
5. **View All Saved Data** - Displays complete data summary
6. **Load Customers from File** - Retrieves customers from `customers.csv`
7. **Load Orders from File** - Retrieves orders from `orders.csv`
8. **Load Deliveries from File** - Retrieves deliveries from `deliveries.csv`
9. **Clear All Data Files** - Deletes all data (for reset/testing)
10. **Exit** - Closes the application

### 4. Modified Classes

#### Delivery.java
- Added `getLocation()` method to support file I/O operations
- Allows retrieval of delivery location for persistence

## Usage

### Compilation
```bash
cd "Smart Food Delivery System"
javac -d bin src/*.java
```

### Running the Application
```bash
cd bin
java Main
```

### Typical Workflow
1. Start the application
2. Register a customer (Option 1) - data automatically saved
3. Place orders (Option 2) - orders automatically saved
4. Create delivery (Option 4) - delivery data automatically saved
5. View saved data (Option 5) - displays all persisted information
6. Load data from files (Options 6-8) to verify persistence

## Data Persistence Benefits

1. **Data Integrity** - Information is retained across application sessions
2. **Audit Trail** - Timestamps track when operations were performed
3. **Easy Backup** - CSV format allows easy export and backup
4. **Scalability** - Framework ready for database migration
5. **CSV Format** - Human-readable and compatible with Excel/spreadsheet apps

## File Structure
```
Smart Food Delivery System/
├── src/
│   ├── Main.java
│   ├── DataPersistence.java (NEW)
│   ├── Customer.java
│   ├── Order.java
│   ├── Delivery.java (MODIFIED)
│   ├── Restaurant.java
│   ├── User.java
│   └── [Exception classes]
├── bin/
│   └── [compiled .class files]
├── data/ (CREATED AT RUNTIME)
│   ├── customers.csv
│   ├── orders.csv
│   ├── deliveries.csv
│   └── menu.csv
└── README.md
```

## Git Branch Information

**Branch Name:** `file-io`

This branch contains all file I/O implementation changes and can be found at:
```
https://github.com/Mutesire250/Smart-Food-Delivery-System/tree/file-io
```

**Commit Message:**
```
feat: Implement file I/O operations for data persistence

- Created DataPersistence class for reading/writing CSV files
- Implemented save/load for customers (with order count)
- Implemented save/load for orders with timestamps
- Implemented save/load for delivery data
- Implemented save/load for restaurant menu
- Enhanced Main.java with interactive menu for data management
- Added getLocation() method to Delivery class
- Data is stored in 'data/' directory with CSV format
- Each entity has dedicated file
```

## Error Handling

The implementation includes robust error handling:
- **FileNotFoundException** - Handles missing files gracefully
- **IOException** - Catches file read/write errors
- **NumberFormatException** - Validates numeric data during deserialization
- **InvalidUserException** - Validates customer data
- **InvalidOrderException** - Validates order data
- **InvalidDeliveryException** - Validates delivery data

All errors are logged to `System.err` and don't crash the application.

## Future Enhancements

1. **Database Integration** - Migrate from CSV to SQL database
2. **JSON Support** - Add JSON serialization option
3. **Compression** - Compress data files for storage efficiency
4. **Encryption** - Add data encryption for sensitive information
5. **Search/Filter** - Implement advanced search capabilities
6. **Reporting** - Generate sales and delivery reports

## Testing Recommendations

1. Create a new customer and verify it appears in `data/customers.csv`
2. Place multiple orders and verify in `data/orders.csv`
3. Create deliveries and verify in `data/deliveries.csv`
4. Use Option 5 to view all saved data
5. Close and restart the application
6. Use Option 6 to load customers - verify previous data is retrieved

## Conclusion

The file I/O implementation provides a solid foundation for data persistence in the Smart Food Delivery System. The system maintains data integrity, supports multiple data types, and provides a user-friendly interface for data management and retrieval.
