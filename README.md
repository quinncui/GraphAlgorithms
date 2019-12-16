### Graph Algorithms

#### 1. Graph Basics
###### 1.1 Adjacency Matrix
###### 1.2 Adjacency List
###### 1.3 Adjacency Set

###### Comparison
|       | Space Complexity of <br>Graph Construction | Time Complexity of <br> Graph Construction | verify if two <br> nodes connected | find neighbors <br> of a node |
| :---: | :---: | :---: | :---: | :---: |
|Adjacency Matrix| O (V ^ 2) | O(E) | O(1) |  O(V) |
|Adjacency List   | O (V + E)     | O(E) <br> O(E*V) if check duplicates   | O(degree(v)) <br> O(V) | O(degree(v)) <br> O(V) |
|Adjacency Set| O (V + E) | O(ElogV) | O(logV) | O(degree(v)) <br> O(V) |

#### 2. Graph DFS
###### 2.1 Graph (Adjacency Set)
###### 2.2 Graph DFS (Recursion)
###### 2.3 Graph DFS Iteration
###### 2.4 Components
###### 2.5 Single Source Path
###### 2.6 All Pairs Path
###### 2.7 Path
###### 2.8 Cycle Detection
###### 2.9 Bipartite Detection

###### DFS Recursion Core
```java
    private void dfs(int vertex) {
        visited[vertex] = true;
        preorder.add(vertex);
        for (int w : graph.neighbors(vertex)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }
```
###### DFS Iteration Core
```java
    private void dfs(int vertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(vertex);
        visited[vertex] = true;
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            preorder.add(cur);
            for (int v : graph.neighbors(cur)) {
                if (!visited[v]) {
                    stack.push(v);
                    visited[v] = true;
                }
            }
        }
    }
```

#### 3. Graph BFS
###### 3.1 Graph (Adjacency Set)
###### 3.2 Graph BFS (Iteration)
###### 3.3 Single Source Path
###### 3.4 All Pairs Path
###### 3.5 Path
###### 3.6 Components
###### 3.7 Cycle Detection
###### 3.8 Bipartite Detection
###### 3.9 Unweighted Single Source Shortest Path 
###### BFS Iteration Core

```java
    public void bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        visited[vertex] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            order.add(v);
            for (int w : graph.neighbors(v)) {
                if (!visited[w]) {
                    queue .add(w);
                    visited[w] = true; 
                }
            }
        }
    }
```

#### 4. Graph Modeling & Floodfill
###### 4.1 IsBipartite (leetcode 785)
###### 4.2 Max Area of Island (leetcode 695)
###### 4.3 Max Area of Island by Union Find


#### 5. Graph Search Algorithms & AI
###### 5.1 BFS - Shortest Path in Binary Matrix (leetcode 1091)
###### 5.2 State Representation - Open the Lock (leetcode 752)
###### 5.2 State Representation - Water Bucket
###### 5.3 Sliding Puzzle (leetcode 773)




