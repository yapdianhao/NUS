from diagnostic import *
from hi_graph_connect_ends import *


def twice(f):
    return lambda x: f(f(x))

trace(f)
twice(twice)(twice)(lambda x:x+1)(2)


