package com.css.java8.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author Kishore Routhu on 15/8/17 11:51 AM.
 */
public class SemaphorePoolDemo {

    public static void main(String[] args) throws InterruptedException {
        Pool pool = new Pool();
        pool.fillData();

        Item item = pool.getItem();
        //do some job with item
        //put the item back to pool
        pool.putItem(item);

    }

    //Class that uses a semaphore to control access to the pool of items
    private static class Pool {

        private static final int MAX_VALUE = 100;
        private static final Semaphore available = new Semaphore(MAX_VALUE, true);

        private List<Item> itemList = new ArrayList<>(MAX_VALUE);

        public void fillData() {
            for (int i = 1; i <= MAX_VALUE; i++)
                itemList.add(new Item(i, false));
        }

        public Item getItem() throws InterruptedException {
            available.acquire();
            return getNextAvailableItem();
        }

        public void putItem(Item item) {
            if (markAsUnUsed(item))
            available.release();
        }

        private synchronized Item getNextAvailableItem() {
            for (int i = 0; i < MAX_VALUE; i++) {
                Item item = itemList.get(i);
                if (!item.isUsed()) {
                    item.setUsed(true);
                    return item;
                }
            }
            return null;
        }

        private synchronized boolean markAsUnUsed(Item item) {
            for (int i = 0; i < MAX_VALUE; i++) {
                Item listItem = itemList.get(i);
                if (listItem.equals(item) && listItem.isUsed()) {
                    listItem.setUsed(false);
                    return true;
                }
            }
            return false;
        }
    }

    private static class Item {
        private int value;
        private boolean used;

        public Item() {
        }

        public Item(int value, boolean used) {
            this.value = value;
            this.used = used;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Item item = (Item) o;

            return getValue() == item.getValue();
        }

        @Override
        public int hashCode() {
            return getValue();
        }
    }
}
