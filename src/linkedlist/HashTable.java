package linkedlist;

/**
 * BEST CASE LOOKUP O(1)
 * 
 * WORST CASE LOOKUP O(n)
 */

public class HashTable {

    // Size of the hash table
    private final int INITIAL_SIZE = 16;
    private HashEntry[] data;

    private class HashEntry {
        private String key;
        private String value;
        private HashEntry next;

        public HashEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTable() {
        data = new HashEntry[INITIAL_SIZE];
    }

    public void put(String key, String value) {
        HashEntry entry = new HashEntry(key, value);
        int index = getIndex(key);

        // If no entry, just put the entry against the index
        if (data[index] == null) {
            data[index] = entry;
            return;
        }
        // Collision detected. Get the linked list of entries for that index
        HashEntry entries = data[index];

        // Traverse to the end of linked list and put the entry there
        while (entries.next != null) {
            entries = entries.next;
        }
        entries.next = entry;
    }

    public String get(String key) {
        int index = getIndex(key);

        // Get the entries at index
        HashEntry entries = data[index];
        if (entries == null) {
            return null;
        }

        // If the node doesn't match KEY, traverse until matching node is found
        while (!entries.key.equalsIgnoreCase(key) && entries.next != null) {
            entries = entries.next;
        }

        // This is the matching node. Return the value
        return entries.value;
    }

    private int getIndex(String key) {
        int hash = key.hashCode();
        int index = (hash & 0x7fffffff) % INITIAL_SIZE;
        return index;
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                HashEntry entry = data[i];
                int index = getIndex(entry.key);
                table.append("\nINDEX: " + index + " KEY: " + entry.key + " VALUE: " + entry.value);
                while (entry.next != null) {
                    entry = entry.next;
                    table.append("\nINDEX: " + index + " KEY: " + entry.key + " VALUE: " + entry.value);
                }
            }
        }
        return table.toString();
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        hashTable.put("KEY1", "VALUE1");
        hashTable.put("KEY2", "VALUE2");
        System.out.println(hashTable.get("KEY1"));
        System.out.println(hashTable.get("KEY2"));
        System.out.println(hashTable.get("KEY3"));
        System.out.println(hashTable);
    }

}
