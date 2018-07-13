package com.zjw.jdk.thread;

public class ProducerConsumer {
    private int apple = 0;

    public synchronized void increace() {
        while (apple == 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        apple++;
        System.out.println("生成苹果成功！");
        notify();
    }

    public synchronized void decreace() {
        while (apple == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        apple--;
        System.out.println("消费苹果成功！");
        notify();
    }

    public static void main(String[] args) {
        ProducerConsumer box = new ProducerConsumer();
        Consumer consumer = box.new Consumer(box);
        Producer producer = box.new Producer(box);

        Thread t1 = new Thread(consumer);
        Thread t2 = new Thread(producer);

        t1.start();
        t2.start();


    }

    class Producer implements Runnable {
        private ProducerConsumer box;

        public Producer(ProducerConsumer box) {
            this.box = box;
        }

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("pro  i:" + i);
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }

                box.increace();
            }

        }
    }


    class Consumer implements Runnable {
        private ProducerConsumer box;

        public Consumer(ProducerConsumer box) {
            this.box = box;
        }

        @Override
        public void run() {

            for (int i = 0; i < 11; i++) {
                try {
                    System.out.println("Con: i " + i);
                    Thread.sleep(3000);                // 这里设置跟上面30不同是为了 盒子中的苹果能够增加，不会生产一个马上被消费
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }

                box.decreace();
            }
        }
    }

}