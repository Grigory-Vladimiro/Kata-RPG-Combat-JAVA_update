# 🛡 Kata RPG Combat — TDD Edition

This project is a test-driven implementation of a minimal RPG combat engine in Java, created as part of a kata exercise. Each feature was added through a strict TDD process:  
➤ **Test first** → **Failing test** → **Minimal implementation** → ✅ **Green**

---

## ✅ Features Implemented

### 🎮 Characters
- Start with **1000 HP**, **Level 1**, and are **Alive**
- Can **receive damage**
- Die when HP reaches 0
- Can **heal** themselves (and later: allies)
- Cannot be healed if dead
- Healing **cannot exceed 1000 HP**

### ⚔ Damage Mechanics
- Characters **cannot damage themselves**
- If the target is **5+ levels higher**, damage is **reduced by 50%**
- If the target is **5+ levels lower**, damage is **increased by 50%**

### 📏 Attack Range
- **Melee** fighters have range of **2 meters**
- **Ranged** fighters have range of **20 meters**
- Characters can only deal damage if target is **within range**

### 🛡 Factions
- Characters can belong to one of 4 factions:  
  ➤ `HUMANS`, `ELVES` (Allies)  
  ➤ `ORCS`, `TROLLS` (Enemies)

- **Allies**:
  - Cannot damage each other
  - Can heal each other
- **Enemies**:
  - Can damage each other
  - Cannot be healed by one another

### 🪵 Props (Objects)
- Neutral, destructible game objects with health
- Can **receive damage**
- Are considered **destroyed** when HP = 0
- Cannot be healed

---

## 🧪 Testing & TDD

All logic is covered with **unit tests** using **JUnit 5**.  
Each commit follows a TDD step:
1. Write a failing test
2. Implement the minimal code to pass it
3. Refactor if needed

Tests are located in:
- `CharacterTest.java`
- `PropTest.java`

---

## 📁 Project Structure  
```
src/
├── main/
│   └── java/
│       └── com/
│           └── factoriaf5/
│               └── kata/
│                   ├── Character.java
│                   ├── Faction.java
│                   └── Prop.java
└── test/
    └── java/
        └── com/
            └── factoriaf5/
                └── kata/
                    ├── CharacterTest.java
                    └── PropTest.java
```
