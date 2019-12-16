#### Bridge And Articulation Point


##### 6.1 Bridge
The number of components would change if an edge has been cut, which is a bridge

A graph could have several bridges
Every edge of a tree is a bridge

cycle is the attribute of a graph
bridge is a attribute of an edge

Finding bridges can be solved only by DFS, not BFS 
In the traversal of BFS, there is no edge point to the ancestor, only cross edges


##### 6.2 Articulation Point / Cut Point
The number of components would change if an vertex has been cut, which is an articulation point

If using list to store the result of points, there might be duplicates, due to some cut points have more than one edges,
so we have to use set