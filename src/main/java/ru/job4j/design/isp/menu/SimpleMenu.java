package ru.job4j.design.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        if (childName.isBlank() || parentName.isBlank()) {
            throw new IllegalArgumentException("Task names cannot be empty!");
        }
        if (findItem(childName).isPresent()) {
            throw new IllegalArgumentException("Task with that name is already exists!");
        }
        boolean result = Objects.equals(parentName, Menu.ROOT);
        if (result) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        } else {
            Optional<ItemInfo> element = findItem(parentName);
            result = element.isPresent();
            if (result) {
                element.get().menuItem.getChildren().add(
                        new SimpleMenuItem(childName, actionDelegate)
                );
            }
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Iterator<MenuItemInfo> it = iterator();
        Optional<MenuItemInfo> result = Optional.empty();
        while (it.hasNext()) {
            MenuItemInfo menuItemInfo = it.next();
            if (itemName.equals(menuItemInfo.getName())) {
                result = Optional.of(menuItemInfo);
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new MenuInfoIterator();
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator it = new DFSIterator();
        Optional<ItemInfo> result = Optional.empty();
        while (it.hasNext()) {
            ItemInfo itemInfo = it.next();
            if (name.equals(itemInfo.menuItem.getName())) {
                result = Optional.of(itemInfo);
                break;
            }
        }
        return result;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class MenuInfoIterator implements Iterator<MenuItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        MenuInfoIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public MenuItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            ItemInfo info = new ItemInfo(current, lastNumber);
            return new MenuItemInfo(info.menuItem, info.number);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}