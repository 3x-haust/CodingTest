class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        
        IntStream.range(0, nums.length).forEach(i -> {
            int complement = target - nums[i]; 
            if (map.containsKey(complement)) {
               result[0] = map.get(complement);
               result[1] = i;
            }
            map.put(nums[i], i);
        });

        return result;
    }
}