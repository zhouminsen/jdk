package com.zjw.jdk.arithmetic;

/**
 * Created by zhoum on 2018/7/16.
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 4, 5, 6, 7, 8, 9, 10, 11};
        System.out.println(binarySearch(arr, 4));

        System.out.println(findFirstEqual(arr, 4));
        System.out.println(findLastEqual(arr, 4));
        System.out.println(findFirstEqualLarger(arr, 4));
        System.out.println(findLastEqualSmaller(arr, 4));

        System.out.println(binFind2(arr, 0, arr.length, 4));

    }

    private static int findFirstEqualLarger(int[] arr, int target) {
        //下标初始
        int left = 0;
        //下标末处
        int right = arr.length - 1;

        while (left <= right) {
            //取模得到中间下标
            int index = (left + right) / 2;
            int v = arr[index];
            if (v >= target) {
                right = index - 1;
            } else {
                left = index + 1;
            }
        }
        return left;
    }

    // 查找最后一个等于或者小于key的元素
    static int findLastEqualSmaller(int[] arr, int target) {
        //下标初始
        int left = 0;
        //下标末处
        int right = arr.length - 1;

        while (left <= right) {
            //取模得到中间下标
            int index = (left + right) / 2;
            int v = arr[index];
            if (v > target) {
                //如果得到的值大于target
                right = index - 1;
            } else {
                left = index + 1;
            }
        }
        return right;
    }

    private static int findLastEqual(int[] arr, int target) {
        //下标初始
        int left = 0;
        //下标末处
        int right = arr.length - 1;

        while (left <= right) {
            //取模得到中间下标
            int index = (left + right) / 2;
            int v = arr[index];
            if (v > target) {
                //如果得到的值大于target
                right = index - 1;
            } else {
                left = index + 1;
            }
        }
        if (right >= 0 && arr[right] == target) {
            return right;
        }
        return -1;
    }

    private static int findFirstEqual(int[] arr, int target) {
        //下标初始
        int left = 0;
        //下标末处
        int right = arr.length - 1;

        while (left <= right) {
            //取模得到中间下标
            int index = (left + right) / 2;
            int v = arr[index];
            if (v >= target) {
                right = index - 1;
            } else {
                left = index + 1;
            }
        }
        if (left < arr.length && arr[left] == target) {
            return left;
        }

        return -1;
    }

    private static int binarySearch(int[] arr, int target) {
        //下标初始
        int left = 0;
        //下标末处
        int right = arr.length - 1;

        while (left <= right) {
            //取模得到中间下标
            int index = (left + right) / 2;
            int v = arr[index];
            if (v == target) {
                return index;
            } else if (v > target) {
                //如果得到的值大于target
                right = index - 1;
            } else {
                left = index + 1;
            }
        }

        return -1;
    }

    /**
     * 递归实现
     *
     * @param arr
     * @param start
     * @param end
     * @param num
     * @return
     */
    public static int binFind2(int arr[], int start/*起点*/, int end/*终点*/, int num/*要查找的数字*/) {
        if (start <= end)//注意这里的条件是<=
        {
            int index = (start + end) / 2;
            int v = arr[index];
            if (num < v) {
                return binFind2(arr, start, index - 1, num);
            }
            if (num > v) {
                return binFind2(arr, index + 1, end, num);
            }
            if (v == num) {
                return index;
            }
        }
        return -1;//未找到
    }

}
