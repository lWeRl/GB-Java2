package lesson5;

public class HomeWork5 {
    static final int size = 10000000;
    static final int h = size / 2;
    static final int h2 = size / 4;
    static float[] arr1 = new float[size];
    static float[] arr2 = new float[size];
    static float[] arr3 = new float[size];
    static float[] arr4 = new float[size];
    static float[] arr5 = new float[size];

    static long method1() {
        for (int i = 0; i < size; i++) {
            arr1[i] = 1.0f;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return System.currentTimeMillis() - a;
    }

    static long method2() {
        for (int i = 0; i < size; i++) {
            arr2[i] = 1.0f;
        }
        long a = System.currentTimeMillis();
        float[] a1 = new float[h];
        System.arraycopy(arr2, 0, a1, 0, h);
        float[] a2 = new float[h];
        System.arraycopy(arr2, h, a2, 0, h);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = h, j = 0; i < size; i++, j++) {
                    a2[j] = (float) (a2[j] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(a1, 0, arr2, 0, h);
        System.arraycopy(a2, 0, arr2, h, h);
        return System.currentTimeMillis() - a;
    }

    static long method3() {
        for (int i = 0; i < size; i++) {
            arr3[i] = 1.0f;
        }
        long a = System.currentTimeMillis();
        float[] a1 = new float[h];
        System.arraycopy(arr3, 0, a1, 0, h);
        float[] a2 = new float[h];
        System.arraycopy(arr3, h, a2, 0, h);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h2; i++) {
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                for (int i = h + h2, j = h2; i < size; i++, j++) {
                    a1[j] = (float) (a1[j] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = h2, j = 0; i < size - h2; i++, j++) {
                    a2[j] = (float) (a2[j] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(a1, 0, arr3, 0, h2);
        System.arraycopy(a1, h2, arr3, h + h2, h2);
        System.arraycopy(a2, 0, arr3, h2, h);
        return System.currentTimeMillis() - a;
    }

    static long method4() {
        for (int i = 0; i < size; i++) {
            arr4[i] = 1.0f;
        }
        long a = System.currentTimeMillis();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < size; i += 2) {
                    arr4[i] = (float) (arr4[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < size; i += 2) {
                    arr4[i] = (float) (arr4[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis() - a;
    }

    static long method5() {
        for (int i = 0; i < size; i++) {
            arr5[i] = 1.0f;
        }
        long a = System.currentTimeMillis();
        final int[] i1 = {0};
        final int[] i2 = {size - 1};
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (i1[0] < i2[0]) {
                    arr5[i1[0]] = (float) (arr5[i1[0]] * Math.sin(0.2f + i1[0] / 5) * Math.cos(0.2f + i1[0] / 5) * Math.cos(0.4f + i1[0] / 2));
                    i1[0]++;
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (i2[0] > i1[0]) {
                    arr5[i2[0]] = (float) (arr5[i2[0]] * Math.sin(0.2f + i2[0] / 5) * Math.cos(0.2f + i2[0] / 5) * Math.cos(0.4f + i2[0] / 2));
                    i2[0]--;
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis() - a;
    }


    static boolean compare(float[] arr1, float[] arr2) {
        int l = arr1.length;
        if (l != arr2.length) return false;
        for (int i = 0; i < l; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        long t1 = method1();
        long t2 = method2();
        long t3 = method3();
        long t4 = method4();
        long t5 = method5();
        if (compare(arr1, arr2) && compare(arr1, arr3) && compare(arr1, arr4) && compare(arr1, arr5))
            System.out.printf("Two threads full parallel(counter convergence): %dms\nTwo threads full parallel(even\\odd): %dms\nTwo threads by quarters(1+4\\2+3): %dms\nTwo threads by half(1+1): %dms\nOne thread: %dms", t5, t4, t3, t2, t1);
    }
}