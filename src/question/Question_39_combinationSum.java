package question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liupeidong
 * Created on 2019/8/2 9:35
 */
public class Question_39_combinationSum {

    /*给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

        candidates 中的数字可以无限制重复被选取。

        说明：

        所有数字（包括 target）都是正整数。
        解集不能包含重复的组合。 
        示例 1:

        输入: candidates = [2,3,6,7], target = 7,
        所求解集为:
        [
          [7],
          [2,2,3]
        ]
        示例 2:

        输入: candidates = [2,3,5], target = 8,
        所求解集为:
        [
          [2,2,2,2],
          [2,3,3],
          [3,5]
        ] */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> lists = new ArrayList<>();
        backtrack(target, 0, candidates, new ArrayList<>(), lists);
        return lists;
    }

    public void backtrack(int target, int start, int[] candidates, List<Integer> list, List<List<Integer>> lists) {
        if (target == 0) {
            List<Integer> list1 = new ArrayList<>(list);
            lists.add(list1);
            return;
        } else if (target < 0) {
            return;
        }
        for (int i = start; i < candidates.length && target - candidates[i] >= 0; i++) {
            list.add(candidates[i]);
            backtrack(target - candidates[i], i, candidates, list, lists);
            list.remove(list.size() - 1);
        }
    }

}
