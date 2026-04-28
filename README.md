# Smart Food Delivery System

A robust, Java-based Object-Oriented Programming (OOP) system designed to simulate a real-world food delivery workflow with comprehensive **Exception Handling**.

##  Key Features
- **OOP Architecture**: Demonstrates Encapsulation, Inheritance, Abstraction, and Polymorphism.
- **Robust Exception Handling**: Custom hierarchy of unchecked exceptions to ensure graceful failure recovery.
- **Interactive Console UI**: User-friendly prompts with built-in validation and retry mechanisms.

---

##  System Architecture

### Core Classes
- **User (Abstract)**: Base class for all system participants.
- **Customer**: Extends User; manages order placement.
- **Restaurant**: Handles order preparation and validation.
- **Order**: Encapsulates order details (ID, Item, Price).
- **Delivery**: Manages tracking and success/failure states.

---

##  Exception Handling Mechanism

The system is designed to handle business rule violations without crashing. It uses a custom hierarchy of **Unchecked Exceptions**:

### Custom Exception Hierarchy
```text
RuntimeException (Java Built-in)
└── DeliverySystemException (Base Custom)
    ├── InvalidUserException     (Bad Name/Email)
    ├── InvalidOrderException    (Invalid Price/ID)
    └── InvalidDeliveryException (Empty Address/Double Delivery)
```

### Handled Scenarios
| Failure Scenario | Exception Thrown | Recovery Action |
| :--- | :--- | :--- |
| Blank Name/Email | `InvalidUserException` | System re-prompts for valid input |
| Invalid Email Format | `InvalidUserException` | System re-prompts for valid input |
| Non-positive Order ID | `InvalidOrderException` | System re-prompts for valid input |
| Price ≤ 0 | `InvalidOrderException` | System re-prompts for valid input |
| Blank Delivery Address | `InvalidDeliveryException` | System re-prompts for valid input |
| Attempting Double Delivery | `InvalidDeliveryException` | Error message displayed; state protected |

---

## 🛠️ How to Run

1. **Compile**:
   ```bash
   javac src/*.java -d out
   ```
2. **Execute**:
   ```bash
   java -cp out Main
   ```

---

## Contributors
- **Mutesire250**
