import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Решение leetcode-weekly-contest-20
 * <p>
 * Задачи:
 *      520. Detect Capital
 *      526. Beautiful Arrangement
 *      525. Contiguous Array
 *      517. Super Washing Machines
 *
 * @author Danil Popov
 *         BadPit@211.ru
 *         on 25.11.17.
 */
public class LeetcodeContest20 {

    /**
     * Given a word, you need to judge whether the usage of capitals in it is right or not.
     * <p>
     * We define the usage of capitals in a word to be right when one of the following cases holds:
     * <p>
     * All letters in this word are capitals, like "USA".
     * All letters in this word are not capitals, like "leetcode".
     * Only the first letter in this word is capital if it has more than one letter, like "Google".
     * Otherwise, we define that this word doesn't use capitals in a right way.
     * <p>
     * Example 1:
     * Input: "USA"
     * Output: True
     * <p>
     * Example 2:
     * Input: "FlaG"
     * Output: False
     * <p>
     * Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
     */
    @Test
    public void detectCapital() {
        assertTrue(capitaliseChecker("USA"));
        assertTrue(capitaliseChecker("Google"));
        assertTrue(capitaliseChecker("leetcode"));
        assertFalse(capitaliseChecker("leetcOde"));
    }

    private boolean capitaliseChecker(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        String allUpper = word.toUpperCase();
        if (word.equals(allUpper)) {
            return true;
        }

        String allLower = word.toLowerCase();
        if (word.equals(allLower)) {
            return true;
        }

        if (allUpper.substring(0, 1).equals(word.substring(0, 1)) &&
                word.substring(1).toLowerCase().equals(word.substring(1))) {
            return true;
        }

        return false;
    }

    /**
     * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:
     * <p>
     * The number at the ith position is divisible by i.
     * i is divisible by the number at the ith position.
     * Now given N, how many beautiful arrangements can you construct?
     * <p>
     * Example 1:
     * Input: 2
     * Output: 2
     * Explanation:
     * <p>
     * The first beautiful arrangement is [1, 2]:
     * <p>
     * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
     * <p>
     * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
     * <p>
     * The second beautiful arrangement is [2, 1]:
     * <p>
     * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
     * <p>
     * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
     * Note:
     * N is a positive integer and will not exceed 15.
     */
    @Test
    public void beautifulArrangements() {
        int n = 2;
        int[] input = new int[n];
        for (int i = 1; i <= n; i++) {
            input[i - 1] = i;
        }

        int[][] allArrangements = generateAllArrangements(input);
        int count = 0;
        for (int[] arrange : allArrangements) {
            if (isBeautiful(arrange)) {
                System.out.println(Arrays.toString(arrange));
                count++;
            }
        }
        System.out.println("Count: " + count);
    }

    /**
     * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
     * <p>
     * Example 1:
     * Input: [0,1]
     * Output: 2
     * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
     * <p>
     * Example 2:
     * Input: [0,1,0]
     * Output: 2
     * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
     * <p>
     * Note: The length of the given binary array will not exceed 50,000.
     */
    @Test
    public void contiguousArray() {
        int[] binaryArray = getRandomBinaryArray(20);
        int maxLength = getMaxContiguousArray(binaryArray);

        binaryArray = new int[]{1,0,1,0,1,0,0,0,1,0};
        maxLength = getMaxContiguousArray(binaryArray);
        assertEquals(6, maxLength);

        binaryArray = new int[]{1,0,1,0,1,0,1,0,1,0};
        maxLength = getMaxContiguousArray(binaryArray);
        assertEquals(10, maxLength);

        binaryArray = new int[]{1,0};
        maxLength = getMaxContiguousArray(binaryArray);
        assertEquals(2, maxLength);

        binaryArray = new int[]{0,1};
        maxLength = getMaxContiguousArray(binaryArray);
        assertEquals(2, maxLength);

        binaryArray = new int[]{0,1,0};
        maxLength = getMaxContiguousArray(binaryArray);
        assertEquals(2, maxLength);

        binaryArray = new int[]{1,1,1,0,0,0};
        maxLength = getMaxContiguousArray(binaryArray);
        assertEquals(6, maxLength);

        binaryArray = new int[]{1,1,1,0,0,0,0,0,1,0,1,0,0,1,1,0,1,0};
        maxLength = getMaxContiguousArray(binaryArray);
        assertEquals(10, maxLength);

        binaryArray = new int[]{1,1,1,1,0};
        maxLength = getMaxContiguousArray(binaryArray);
        assertEquals(2, maxLength);
    }

    /**
     * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
     *
     * For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time .
     *
     * Given an integer array representing the number of dresses in each washing machine from left to right on the line, you should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.
     *
     * Example1
     *
     * Input: [1,0,5]
     *
     * Output: 3
     *
     * Explanation:
     * 1st move:    1     0 <-- 5    =>    1     1     4
     * 2nd move:    1 <-- 1 <-- 4    =>    2     1     3
     * 3rd move:    2     1 <-- 3    =>    2     2     2
     *
     * Example2
     *
     * Input: [0,3,0]
     *
     * Output: 2
     *
     * Explanation:
     * 1st move:    0 <-- 3     0    =>    1     2     0
     * 2nd move:    1     2 --> 0    =>    1     1     1
     *
     * Example3
     *
     * Input: [0,2,0]
     *
     * Output: -1
     *
     * Explanation:
     * It's impossible to make all the three washing machines have the same number of dresses.
     * Note:
     * The range of n is [1, 10000].
     * The range of dresses number in a super washing machine is [0, 1e5].
     */
    @Test
    public void superWashingMachines() {
        int[] machines = getWashingMachines(6, 10, true);
        System.out.println(Arrays.toString(machines));
        System.out.println("Output: " + swapDresses(machines));
        System.out.println(Arrays.toString(machines));
    }

    private int swapDresses(int[] machines) {
        int sum = getDressesSum(machines);
        if (sum % machines.length != 0) {
            return -1;
        }

        int max = getMaxMachineIndex(machines);
        int min = getMinMachineIndex(machines);

        while (max != min) {
            machines[max] = machines[max] - 1;
            machines[min] = machines[min] + 1;
            max = getMaxMachineIndex(machines);
            min = getMinMachineIndex(machines);
        }

        return machines[max];
    }

    private int getDressesSum(int[] machines) {
        int sum = 0;
        for (int i = 0; i < machines.length; i++) {
            sum += machines[i];
        }
        return sum;
    }

    private int getMinMachineIndex(int[] machines) {
        int minIndex = 0;
        for (int i = 0; i < machines.length; i++) {
            if (machines[i] < machines[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private int getMaxMachineIndex(int[] machines) {
        int maxIndex = 0;
        for (int i = 0; i < machines.length; i++) {
            if (machines[i] > machines[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private int[] getWashingMachines(int machinesCount, int maxDressesCount, boolean isValid) {
        int[] result = new int[machinesCount];
        for (int i = 0; i < result.length; i++) {
            result[i] = (int) Math.round(Math.random() * maxDressesCount);
        }

        if (isValid) {
            int sum = getDressesSum(result);
            if (sum % result.length != 0) {
                result[0] += result.length - (sum % result.length);
            }
        }

        return result;
    }

    private int getMaxContiguousArray(int[] binaryArray) {
        System.out.println("Input: " + Arrays.toString(binaryArray));

        int maxLength = 0;
        int beginIndex = 0;

        for (int i = 0; i < binaryArray.length; i++) {
            int lastEqIndex = i;
            int zeroCount = 0;
            int oneCount = 0;
            if (binaryArray[i] == 0) {
                zeroCount++;
            }
            if (binaryArray[i] == 1) {
                oneCount++;
            }
            for (int j = i + 1; j < binaryArray.length; j++) {
                if (binaryArray[j] == 0) {
                    zeroCount++;
                }
                if (binaryArray[j] == 1) {
                    oneCount++;
                }
                if (zeroCount == oneCount) {
                    lastEqIndex = j;
                }
            }
            if (lastEqIndex - i + 1 > maxLength) {
                maxLength = lastEqIndex - i + 1;
                beginIndex = i;
            }
        }
        int[] subarray = new int[maxLength];
        System.arraycopy(binaryArray, beginIndex, subarray, 0, maxLength);
        System.out.println("Max subarray is: " + Arrays.toString(subarray) + "\nLength: " + maxLength);
        return maxLength;
    }

    private int[] getRandomBinaryArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = (int) Math.round(Math.random());
        }
        return result;
    }

    @Test
    public void invertTest() {
        int count = 8;
        int[] input = new int[count];
        for (int i = 1; i <= count; i++) {
            input[i - 1] = i;
        }
        invertTail(input, 0);
        System.out.println(Arrays.toString(input));
        assertEquals("[8, 7, 6, 5, 4, 3, 2, 1]", Arrays.toString(input));
    }

    @Test
    public void invertTest2() {
        int count = 2;
        int[] input = new int[count];
        for (int i = 1; i <= count; i++) {
            input[i - 1] = i;
        }
        invertTail(input, 0);
        System.out.println(Arrays.toString(input));
        assertEquals("[2, 1]", Arrays.toString(input));
    }

    @Test
    public void invertTest3() {
        int count = 3;
        int[] input = new int[count];
        for (int i = 1; i <= count; i++) {
            input[i - 1] = i;
        }
        invertTail(input, 0);
        System.out.println(Arrays.toString(input));
        assertEquals("[3, 2, 1]", Arrays.toString(input));
    }

    /**
     * Генерируем все возможные последовательности
     * в лексикографическом убывающем порядке
     *
     * @param input
     */
    private int[][] generateAllArrangements(int[] input) {
        int[][] result = new int[factorial(input.length)][input.length];
        int[] arrange = new int[input.length];
        System.arraycopy(input, 0, arrange, 0, input.length);
        result[0] = arrange;
        for (int i = 1; i <= factorial(input.length); i++) {
            int tailsIndex = findTailsIndex(input);
            if (tailsIndex == 0) {
                return result;
            }
            swapTailsElementWithPreTailElement(input, tailsIndex);
            invertTail(input, tailsIndex);
            arrange = new int[input.length];
            System.arraycopy(input, 0, arrange, 0, input.length);
            result[i] = arrange;
            // System.out.println(Arrays.toString(input));
        }
        return result;
    }

    private int factorial(int i) {
        if (i <= 1) {
            return 1;
        }
        return i * factorial(i - 1);
    }

    /**
     * Инвертируем порядок элементов в хвосте
     *
     * @param input
     * @param tailsIndex
     */
    private void invertTail(int[] input, int tailsIndex) {
        for (int k = 1; k < input.length; k++) {
            for (int i = tailsIndex; i < input.length - k; i++) {
                if (i + 1 == input.length) {
                    return;
                }
                swap(input, i, i + 1);
            }
        }
    }

    /**
     * Меняем местами элемент стоящий перед хвостом
     * с первым элементом начная с конца хвоста, который
     * будет больше его
     *
     * @param input
     * @param tailsIndex
     */
    private void swapTailsElementWithPreTailElement(int[] input, int tailsIndex) {
        int elementIndex = tailsIndex;
        for (int i = input.length - 1; i >= tailsIndex; i--) {
            if (input[i] > input[tailsIndex - 1]) {
                elementIndex = i;
                break;
            }
        }
        swap(input, tailsIndex - 1, elementIndex);
    }

    /**
     * Ищем индекс начала масимальной
     * убывающей последовательности
     *
     * @param input входной массив
     * @return индекс
     */
    private int findTailsIndex(int[] input) {
        int tailsIndex = -1;
        for (int i = 0; i < input.length; i++) {
            if (tailsIndex == -1 && i + 1 == input.length) {
                return 0;
            } else if (i + 1 == input.length) {
                return tailsIndex;
            }
            if (input[i] < input[i + 1]) {
                tailsIndex = i + 1;
            }
        }
        return tailsIndex;
    }

    private boolean isBeautiful(int[] input) {
        for (int i = 0; i < input.length; i++) {
            if (i % input[i] != 0 &&
                    input[i] % i != 0) {
                return false;
            }
        }
        return true;
    }

    private void swap(int[] input, int i, int i1) {
        input[i] ^= input[i1];
        input[i1] ^= input[i];
        input[i] ^= input[i1];
    }
}
