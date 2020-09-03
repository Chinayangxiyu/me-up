package com.up.arithmetic.leetcode;

public class leetcode486 {



    public static void main(String[] args) {

        int[] nums = new int[]{1, 5, 233, 7};
        leetcode486 l = new leetcode486();
        System.out.println(l.PredictTheWinner(nums));

    }


    /**
     * choice(int[] nums, int left, int right);
     * 设置当前玩家1选择的次数是x次，目前的总分数sum1 = f1(x)；此时左右边界为[left, right]
     * 下一次由玩家2选择，sum2 = f2(x);此时边界可能是[left +1, right]或[left , right-1],具体取哪一个
     * 取决于哪一个后玩家2的总分数更大。
     * int lsum = choice(nums, left +1, right);
     * int rsum = choice(nums, left, right-1);
     * max(lsum, rsum)
     * @param nums
     * @return
     */
    public boolean PredictTheWinner(int[] nums) {
        return choice(nums, 0, nums.length-1) >= 0;
    }


    /**
     * 1、玩家1的分数为正数，玩家2的分数 * -1使用负数表示；
     * 2、每次递归flag都会被重置，并计算玩家1、2的分数总和，若大于等于零则此时玩家1获胜；
     * 3、
     * @param nums 数组
     * @param left 做边界索引
     * @param right 右边界索引
     * @return
     */
    public int choice(int[] nums, int left, int right){

        // 索引边界处理
        if(left > right){
            return 0;
        }

        // 玩家选择左边界值
        // 将左边界值和之后玩家2的"剩余分数"相加；（剩余分数是玩家2总分数减去玩家1总分数的结果）；取flag倒数继续递归
        int lsum = nums[left]  -  choice(nums, left +1, right);
        int rsum = nums[right] - choice(nums, left, right-1);

        return Math.max(lsum , rsum );

    }


    /**
     * 【重要】该动态规划是对于递归的基础上进行分析，优化；如果单独的分析是否使用动态规划，太抽象了。
     * 1、递归方法中，每次执行的都是一个子任务；而这个子任务的结果值和left，right直接相关；
     * 2、根据left，right创建一个二维数组 dp[][]，left、right的取值就是nums.length，left表示一维索引（纵坐标），right表示横坐标;
     * 3、因为有田间 (left <= right)才会继续递归；所以我们的dp二维数组，只有斜线之上部分才会生效；
     * 4、而斜线上的取值就是数组nums[]的元素（nums[left] == nums[right]）,可以执行初始化；
     * 5、推导共识dp[left,right]的值 = max((nums[left] - dp[left+1, right]), (nums[right] - dp[left, right-1]))
     * 6、根据推导出来的公式和 left==right的值，计算每一个单元格的值，
     * 7、最后判断dp[0][nums.length-1]的值是否大于零
     *
     * @param nums
     * @return
     */
    public boolean PredictTheWinner1(int[] nums) {


        int[][] dp = new int[nums.length][nums.length];


        return choice(nums, 0, nums.length-1) >= 0;
    }



}
