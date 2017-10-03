Code Review by:         Aditya Sharma (u6051965)

for function:           updateRemainingMoves

function written by:    Stefan Louie (u6051635)


The best features of this code include:
- Use of an Iterator object in order to avoid a Concurrent Modification Error which is thrown by java when attempting to remove/add elements to a list while iterating through it. Use of this object is out of the scope of the lecture teachings and therefore its implementation is to be commended.
- Use of a HashSet in order to eliminate the complication of duplicate value checking.
- Clever methods to get the first element of piece placements by iterating every 3 numbers. 

The code documentation:
- Contains only a single comment about what the code does and thus lacks sufficient information for proper debugging given the larger scope of the project.

The program decomposition is only somewhat appropriate as the function type could be changed to private to assure security.

The code:
- Has properly named variables.
- Has consistent styling throughout.
- Has insufficient commenting conventions and does not list meanings of parameters and return values in the comment.

The code has no errors, and sufficiently handles any possible cases of index out of range errors. In addition to this, the tests for this code are well written (however not using many different cases), and the code passes all tests.



