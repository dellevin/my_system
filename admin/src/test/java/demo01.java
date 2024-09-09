public class demo01 {
    public static void main(String[] args) {
        //  初始化数组
        int[] points = {18, 25, 7, 36, 13, 2, 89, 63};

        // 初始化最小值和下标
        int min = points[0];
        int index = 0;

        // 遍历
        for (int i = 1; i < points.length; i++) {
            if (points[i] < min) {
                min = points[i];
                index = i;
            }
        }
        // Step 5: Print the result
        System.out.println("最低分: " + min);
        System.out.println("最低分下标: " + index);
    }
}