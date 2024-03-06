package codechef.start124d;

// How many arrays
// You are given two integers 
// �
// N and 
// �
// X.

// Count the number of distinct arrays of length at most 
// �
// N, whose LCM is equal to 
// �
// X.
// That is, find the number of arrays 
// �
// A such that:

// 1
// ≤
// ∣
// �
// ∣
// ≤
// �
// 1≤∣A∣≤N, where 
// ∣
// �
// ∣
// ∣A∣ denotes the length of 
// �
// A; and
// lcm
// (
// �
// 1
// ,
// �
// 2
// ,
// �
// 3
// ,
// …
// ,
// �
// ∣
// �
// ∣
// )
// =
// �
// lcm(A 
// 1
// ​
//  ,A 
// 2
// ​
//  ,A 
// 3
// ​
//  ,…,A 
// ∣A∣
// ​
//  )=X
// Two arrays 
// �
// A and 
// �
// B are considered different if:

// �
// A and 
// �
// B have different lengths; or
// �
// A and 
// �
// B have the same length, but 
// �
// �
// ≠
// �
// �
// A 
// i
// ​
//  =B 
// i
// ​
//   for some index 
// 1
// ≤
// �
// ≤
// ∣
// �
// ∣
// 1≤i≤∣A∣.
// The answer may be large, so compute it modulo 
// 998244353
// 998244353.

// Input Format
// The first line of input will contain a single integer 
// �
// T, denoting the number of test cases.
// Each test case consists of a single line containing two space-separated integers 
// �
// N and 
// �
// X, the parameters specified in the statement.
// Output Format
// For each test case, output on a new line the number of possible arrays with length at most 
// �
// N and LCM equal to 
// �
// X, modulo 
// 998244353
// 998244353.
// Constraints
// 1
// ≤
// �
// ≤
// 1
// 0
// 3
// 1≤T≤10 
// 3
 
// 1
// ≤
// �
// ≤
// 2
// ⋅
// 1
// 0
// 5
// 1≤N≤2⋅10 
// 5
 
// 1
// ≤
// �
// ≤
// 1
// 0
// 9
// 1≤X≤10 
// 9
 
// The sum of 
// �
// N over all test cases won't exceed 
// 2
// ⋅
// 1
// 0
// 5
// 2⋅10 
// 5
//  .
// Sample 1:
// Input
// Output
// 3
// 2 3
// 10000 6
// 156324 959345256
// 4
// 682692428
// 568466415
// Explanation:
// Test case 
// 1
// 1: We're looking for arrays of length at most 
// 2
// 2 and whose LCM is 
// 3
// 3.
// The only such arrays are 
// [
// 3
// ]
// ,
// [
// 1
// ,
// 3
// ]
// ,
// [
// 3
// ,
// 1
// ]
// ,
// [
// 3
// ,
// 3
// ]
// [3],[1,3],[3,1],[3,3].

public class HowManyArrays {
    
}
