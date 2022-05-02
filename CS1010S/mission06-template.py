#
# CS1010S --- Programming Methodology
#
# Mission 6
#
# Note that written answers are commented out to allow us to run your
# code easily while grading your problem set.

from diagnostic import *
from hi_graph_connect_ends import *

# Mission 6 requires certain functions from Mission 5 to work.
# Do copy any relevant functions that you require in the space below:

def your_gosper_curve_with_angle(level, angle_at_level):
    if level == 0:
        return unit_line
    else:
        return your_gosperize_with_angle(angle_at_level(level))(your_gosper_curve_with_angle(level-1, angle_at_level))
    
def your_gosperize_with_angle(theta):
    def inner_gosperize(curve_fn):
        return put_in_standard_position(connect_ends(rotate(theta)(curve_fn),(rotate(-theta)(curve_fn))))
    return inner_gosperize



# Do not copy any other functions beyond this line #
##########
# Task 1 #
##########

# Example from the mission description on the usage of time function:
# profile_fn(lambda: gosper_curve(10)(0.1), 50)

# Choose a significant level for testing for all three sets of functions.

# -------------
# gosper_curve:
# -------------
# write down and invoke the function that you are using for this testing
# in the space below

print(profile_fn(lambda: gosper_curve(5)(0.5),400))

# Time measurements
#  12.795000000000002, 12.858999999999982, 12.921000000000015, 12.824000000000002, 12.744000000000005
#  average = 12.8286


# ------------------------
# gosper_curve_with_angle:
# ------------------------
# write down and invoke the function that you are using for this testing
# in the space below

print(profile_fn(lambda: gosper_curve_with_angle(5, lambda lvl: pi / 4)(0.5), 400))

# 14.88299999999998, 14.436000000000003, 14.676999999999996, 14.534999999999993, 14.447000000000015
# average = 14.5956

#
# -----------------------------
# your_gosper_curve_with_angle:
# -----------------------------
# write down and invoke the function that you are using for this testing
# in the space below

print(profile_fn(lambda: your_gosper_curve_with_angle(5, lambda lvl: pi/(2+lvl)),400))

# 137.939, 137.20300000000003, 138.545, 139.31400000000002, 137.26
# average = 138.0522


# Conclusion: Customizable functions have speed advantage over customized functions
#  

##########
# Task 2 #
##########

#  1) Yes


#  2) pt is a function that returns a point while curve(t) is a function that returns a curve. A further step is needed to return a point when using curve(t).

##########
# Task 3 #
##########

#
# Fill in this table:
#
#                    level      rotate       joe_rotate
#                      1           3            4
#                      2           5            10
#                      3           7            22
#                      4           9            46
#                      5           11           94
#
#  Evidence of exponential growth in joe_rotate.

