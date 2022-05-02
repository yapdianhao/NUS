#
# CS1010S --- Programming Methodology
#
# Mission 1
#
# Note that written answers are commented out to allow us to run your
# code easily while grading your problem set.

from runes import *


##########
# Task 1 #
##########
def mosaic(a,b,c,d):
    def right_stack(a,b):
        return stack(a,b)
    def left_stack(c,d):
        return stack(c,d)
    return quarter_turn_right(stack(quarter_turn_left(left_stack(a,b)),quarter_turn_left(right_stack(d,c))))
# Test
#show(mosaic(rcross_bb, sail_bb, corner_bb, nova_bb))
#clear_all()
#show(mosaic(heart_bb, pentagram_bb, circle_bb, ribbon_bb))

##########
# Task 2 #
##########

def simple_fractal(params):
    def right_stack(params):
        return stack(params,params)
    return quarter_turn_left(stack(quarter_turn_right(params),quarter_turn_right(right_stack(params))))


# Test
show(simple_fractal(make_cross(rcross_bb)))
clear_all()
show(simple_fractal(heart_bb))
#clear_all()
