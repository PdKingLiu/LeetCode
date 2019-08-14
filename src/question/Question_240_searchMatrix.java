package question;

/**
 * @author liupeidong
 * Created on 2019/8/14 9:07
 */
public class Question_240_searchMatrix {

    /*编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

        每行的元素从左到右升序排列。
        每列的元素从上到下升序排列。
        示例:

        现有矩阵 matrix 如下：

        [
          [1,   4,  7, 11, 15],
          [2,   5,  8, 12, 19],
          [3,   6,  9, 16, 22],
          [10, 13, 14, 17, 24],
          [18, 21, 23, 26, 30]
        ]
        给定 target = 5，返回 true。

        给定 target = 20，返回 false。 */

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int sum = 0;
        int m = matrix.length, n = matrix[0].length;
        while (sum != matrix.length && m != 0 && n != 0) {
            int left = 0, right = m + n - 2;
            int middle;
            int temMiddle;
            while (left <= right) {
                middle = (left + right) >>> 1;
                if (n == 1) {
                    temMiddle = matrix[sum + middle][0];
                } else {
                    temMiddle = middle <= n - 1 ? matrix[sum][middle] : matrix[sum + (middle + 1) % n][n - 1];
                }
                if (temMiddle == target) {
                    return true;
                } else if (temMiddle < target) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
            m--;
            n--;
            sum++;
        }
        return false;
    }

}
