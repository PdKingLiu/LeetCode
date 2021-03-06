package question;

/**
 * @author liupeidong
 * Created on 2019/5/5 17:29
 */
public class Question_27_removeElement {

    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0 || nums.length == 1 && nums[0] == val) {
            return 0;
        }
        int start = 0, end = nums.length - 1;
        int count = 0;
        while (start < end) {
            while (nums[end] == val) {
                end--;
                if (end <= start) {
                    break;
                }
            }
            if (nums[start] == val) {
                nums[start] = nums[end];
                nums[end] = val;
                end--;
                start++;
            } else {
                start++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                count++;
            }
        }
        return nums.length - count;
    }

}
