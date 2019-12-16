##### 5.1 BFS


##### 5.2 State Representation

- Sometimes vertex and edge are not obvious for us, but we can define a state as a node, and from current
state to the next state can be an edge.
- We don't need to know the whole graph at the first, we can keep going from a state to another state and finally
get to the goal


##### 5.3 Bucket
We have a bucket with capacity of 5 gallons water, another one with capacity of 3 gallons water, how do we get 4 gallons water by these buckets?

- From one state we can get next 6 different states: x, y -> (5, y) or (x, 3) or (0, y) or (x, 0) or (x -> y) or (x <- y)