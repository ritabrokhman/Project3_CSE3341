import java.util.*;
import java.io.*;

// Class to manage memory storage for variables and objects
class Memory {
    private Map<String, Integer> globalMemory; // Stores global variables
    private Deque<Map<String, Integer>> localMemoryStack; // Stack to track local variable scopes
    private Map<String, Map<String, Integer>> objectMemory; // Stores object variables as maps

    public Memory() {
        globalMemory = new HashMap<>();
        localMemoryStack = new ArrayDeque<>();
        objectMemory = new HashMap<>();
        localMemoryStack.push(new HashMap<>()); // Initialize global scope
    }

    // Declare a new global integer variable initialized to 0
    public void declareGlobal(String id) {
        globalMemory.put(id, 0);
    }

    // Declare a new local integer variable initialized to 0
    public void declareLocal(String id) {
        localMemoryStack.peek().put(id, 0);
    }

    // Declare a new object in the global memory (empty object map)
    public void declareObject(String id) {
        objectMemory.put(id, new HashMap<>());
    }

    // Assign a value to a variable, checking local and then global scope
    public void assign(String id, int value) {
        if (localMemoryStack.peek().containsKey(id)) {
            localMemoryStack.peek().put(id, value);
        } else {
            globalMemory.put(id, value);
        }
    }

    // Assign a key-value pair to an object in the global memory
    public void assignObject(String id, String key, int value) {
        if (!objectMemory.containsKey(id)) {
            throw new RuntimeException("ERROR: Undefined object " + id);
        }
        objectMemory.get(id).put(key, value);
    }

    // Retrieve the value of a variable, throwing an error if undefined
    public int getValue(String id) {
        if (localMemoryStack.peek().containsKey(id)) {
            return localMemoryStack.peek().get(id);
        } else if (globalMemory.containsKey(id)) {
            return globalMemory.get(id);
        } else {
            throw new RuntimeException("ERROR: Undefined variable " + id);
        }
    }

    // Retrieve a value from an object by key, throwing an error if undefined
    public int getObjectValue(String id, String key) {
        if (!objectMemory.containsKey(id) || !objectMemory.get(id).containsKey(key)) {
            throw new RuntimeException("ERROR: Undefined key " + key + " in object " + id);
        }
        return objectMemory.get(id).get(key);
    }

    public void setObjectReference(String id, String key) {
    // When assigning id : key, both should point to the same object
    if (!objectMemory.containsKey(key)) {
        throw new RuntimeException("ERROR: Cannot reference undefined object " + key);
    }
    objectMemory.put(id, objectMemory.get(key));
    }

    // Enter a new scope by pushing a new local variable map onto the stack
    public void enterScope() {
        localMemoryStack.push(new HashMap<>());
    }

    // Exit the current scope by popping the local variable map from the stack
    public void exitScope() {
        if (localMemoryStack.size() > 1) {
            localMemoryStack.pop();
        } else {
            throw new RuntimeException("ERROR: Cannot exit global scope");
        }
    }

    // Method to add a new object to memory
    public void addObject(String id, Id idObject) {
        // You can implement object creation logic here if needed
        objectMemory.put(id, new HashMap<>());
    }

    // Method to add a new integer variable to memory
    public void addInteger(String id, int value) {
        globalMemory.put(id, value);  // Add to global memory for simplicity
    }
}
