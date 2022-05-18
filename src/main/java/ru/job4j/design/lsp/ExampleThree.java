package ru.job4j.design.lsp;

import java.util.Random;

public class ExampleThree {

    public  class NumberGenerator {

        private int count;

        public NumberGenerator(int count) {
            validate(count);
            this.count = count;
        }
        
        public NumberGenerator() {
            
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            validate(count);
            this.count = count;
        }

        protected void validate(int count) {
            if (count < 1) {
                throw new IllegalArgumentException("Number count can not be less then 1");
            }
        }
        
        public void generate() {
            Random random = new Random();
            for (int i = 0; i < count; i++) {
                System.out.println(random.nextInt(100));
            }
        }
    }
    
    public class EvenNumberGenerator extends NumberGenerator {
        
        private int count;
        
        public EvenNumberGenerator(int count) {
            validate(count);
            this.count = count;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public void setCount(int count) {
            this.count = count;
        }
        
        @Override
        public void generate() {
            Random random = new Random();
            for (int i = 0; i < count; i++) {
                System.out.println(random.nextInt(100) * 2);
            }
        }
    }
}
