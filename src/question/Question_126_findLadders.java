package question;

import java.util.*;

/**
 * @author liupeidong
 * Created on 2019/9/25 22:49
 */
public class Question_126_findLadders {

    /*给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：

        每次转换只能改变一个字母。
        转换过程中的中间单词必须是字典中的单词。
        说明:

        如果不存在这样的转换序列，返回一个空列表。
        所有单词具有相同的长度。
        所有单词只由小写字母组成。
        字典中不存在重复的单词。
        你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
        示例 1:

        输入:
        beginWord = "hit",
        endWord = "cog",
        wordList = ["hot","dot","dog","lot","log","cog"]

        输出:
        [
          ["hit","hot","dot","dog","cog"],
          ["hit","hot","lot","log","cog"]
        ]
        示例 2:

        输入:
        beginWord = "hit"
        endWord = "cog"
        wordList = ["hot","dot","dog","lot","log"]

        输出: []

        解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 */

    /*
    * BFS + DFS超时了。。。
    * */

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            List<String> list = new ArrayList<>();
            list.add(beginWord);
            List<List<String>> listList = new ArrayList<>();
            listList.add(list);
            return listList;
        }
        wordList.add(beginWord);
        HashMap<String, List<String>> hashMap = new HashMap<>(wordList.size());
        HashMap<String, Boolean> isUsedMap = new HashMap<>(wordList.size());
        getChild(hashMap, isUsedMap, wordList);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int sum = 1;
        boolean isFind = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                List<String> list = hashMap.get(s);
                for (String s1 : list) {
                    if (s1.equals(endWord)) {
                        isFind = true;
                        break;
                    }
                    if (!isUsedMap.get(s1)) {
                        queue.add(s1);
                        isUsedMap.put(s1, true);
                    }
                }
            }
            sum++;
            if (isFind) {
                break;
            }
        }
        if (isFind) {
            HashSet<List<String>> set = new HashSet<>(100);
            List<String> listTem = new ArrayList<>(sum + 10);
            isUsedMap = new HashMap<>(wordList.size() + 10);
            listTem.add(beginWord);
            backtrack(0, sum, isUsedMap, hashMap, set, listTem, beginWord, endWord);
            return new ArrayList<>(set);
        } else {
            return new ArrayList<>();
        }
    }

    private void backtrack(int i, int sum, HashMap<String, Boolean> isUsedMap, HashMap<String, List<String>> hashMap, HashSet<List<String>> set, List<String> listTem, String beginWord, String endWord) {
        if (i + 1 == sum) {
            if (beginWord.equals(endWord)) {
                set.add(new ArrayList<>(listTem));
            }
            return;
        }
        for (String ss : hashMap.get(beginWord)) {
            Boolean b = isUsedMap.get(beginWord);
            if (b == null || !b) {
                isUsedMap.put(beginWord, true);
                listTem.add(ss);
                backtrack(i + 1, sum, isUsedMap, hashMap, set, listTem, ss, endWord);
                isUsedMap.put(beginWord, false);
                listTem.remove(listTem.size() - 1);
            }
        }
    }

    private void getChild(HashMap<String, List<String>> hashMap, HashMap<String, Boolean> hashMap2, List<String> wordList) {
        for (int i = 0; i < wordList.size(); i++) {
            List<String> list = hashMap.get(wordList.get(i));
            hashMap2.put(wordList.get(i), false);
            if (list == null) {
                list = new ArrayList<>(wordList.size());
                hashMap.put(wordList.get(i), list);
            }
            for (int j = 0; j < wordList.size(); j++) {
                if (j != i) {
                    int count = 0;
                    for (int k = 0; k < wordList.get(0).length(); k++) {
                        if (wordList.get(i).charAt(k) != wordList.get(j).charAt(k)) {
                            count++;
                            if (count == 2) {
                                break;
                            }
                        }
                    }
                    if (count == 1) {
                        list.add(wordList.get(j));
                    }
                }
            }
        }
    }
}
