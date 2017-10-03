The code that I will be reviewing consists of the methods that make up our implementation of task 6’s
getViablePiecePlacements, which predominately comes from Khamis Buol <u6080028>.

BEST FEATURES OF THE CODE
One of the best features of the code is how its algorithm revolves around the use of permutations. Rather than using
backtracking to find the set of next possible moves, Khamis instead opted to get all the permutations of the remaining
piece placements in the objective (i.e. not in the first argument of the method) that will be a valid placement sequence
when joined with the first argument of the method to reach the objective. As for the output of getViablePiecePlacements,
he simply returned the set containing the first piece placements of every valid permutation. This idea of implementation
was superior to the alternative idea of using backtracking in regards to computation time by only dealing with those
permutations, when combined with the first argument, which are deemed valid using task 5’s isPlacementSequenceValid.
This implementation was also advantageous in terms of implementation due to it being easier to code when compared to the
backtracking alternative, with other group members easily grasping the overall concept as a bonus.

CODE DOCUMENTATION
The comments used in the code seem to be well placed. Each helper method used in the task is labelled with a comment
describing its purpose. However, a bit more comments could have been used in the ‘permute’ method to aid the
understanding of the mechanics of its recursive algorithm of getting permutations, and why lists of lists were being
used. The naming of the helper methods were highly appropriate as it helped convey its purpose along with its label in
the comments above. The naming of variables for the most part was appropriate, with the variables ‘pos’ and ‘posMoves’
being possible exceptions, as they could have easily been misinterpreted as position and position moves rather than
possible moves. There also seems to be consistency throughout the code.
