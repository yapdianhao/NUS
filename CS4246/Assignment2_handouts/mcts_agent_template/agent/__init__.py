##### Member 1 #####
# Name: Ng Zhi Da
# Matric no: A0034082R
# Tutorial group: 2
##### Member 2 #####
# Name: Yap Dian Hao
# Matric no: A0184679H
# Tutorial group: 1
##### Collaborators: None
##### Sources: Lecture notes

from copy import deepcopy
import gym
from gym.utils import seeding
import gym_grid_driving
from gym_grid_driving.envs.grid_driving import LaneSpec, MaskSpec, Point, AgentState
import math

def randomPolicy(state, env, random):
    '''
    Policy followed in MCTS simulation for playout
    '''
    reward = 0.
    while not state.isDone():
        action = random.choice(env.actions)
        state = state.simulateStep(env=env,action=action)
        reward += state.getReward()
    return reward

class GridWorldState():
    def __init__(self, state, reward = 0):
        '''
        Data structure to represent state of the environment
        self.env : Environment of gym_grid_environment simulator
        self.state : State of the gym_grid_environment
        self.is_done : Denotes whether the GridWorldState is terminal
        self.num_lanes : Number of lanes in gym_grid_environment
        self.width : Width of lanes in gym_grid_environment
        self.reward : Reward of the state
        '''
        self.state = deepcopy(state)
        self.is_done = state.agent_state != AgentState.alive  #is_done #if is_done else False
        if self.state.agent.position.x < 0:
            self.is_done = True
            self.state.agent.position.x = 0
        self.reward = reward
        
    def simulateStep(self, env, action):
        '''
        Simulates action at self.state and returns the next state
        '''
        state_desc = env.step(state=deepcopy(self.state), action= action)
        newState  = GridWorldState(state=state_desc[0], reward=state_desc[1])
        return newState

    def isDone(self):
        '''
        Returns whether the state is terminal
        '''
        return self.is_done
        
    def getReward(self):
        '''
        Returns reward of the state
        '''
        return self.reward

class Node:
    def __init__(self, state, parent=None):
        '''
        Data structure for a node of the MCTS tree
        self.state : GridWorld state represented by the node
        self.parent : Parent of the node in the MCTS tree
        self.numVisits : Number of times the node has been visited 
        self.totalReward : Sum of all rewards backpropagated to the node
        self.isDone : Denotes whether the node represents a terminal state
        self.allChildrenAdded : Denotes whether all actions from the node have been explored 
        self.children : Set of children of the node in the MCTS tree
        '''
        self.state = state
        self.parent = parent
        self.numVisits = 0
        self.totalReward = state.reward #0
        self.isDone = state.isDone()
        self.allChildrenAdded = state.isDone()
        self.children = {}

class MonteCarloTreeSearch:
    def __init__(self, env, numiters, explorationParam, playoutPolicy=randomPolicy, random_seed=None):
        '''
        self.numiters : Number of MCTS iterations
        self.explorationParam : exploration constant used in computing value of node
        self.playoutPolicy : Policy followed by agent to simulate rollout from leaf node
        self.root : root node of MCTS tree
        '''
        self.env = env
        self.numiters = numiters 
        self.explorationParam = explorationParam
        self.playoutPolicy = playoutPolicy
        self.root = None
        self.random, self.seed = seeding.np_random(random_seed)

    def buildTreeAndReturnBestAction(self, initialState):
        '''
        Function to build MCTS tree and return best action at initialState
        '''
        self.root = Node(state=initialState, parent=None)
        for i in range(self.numiters):
            self.addNodeAndBackpropagate()
        bestChild = self.chooseBestActionNode(self.root, 0)
        for action, cur_node in self.root.children.items():
            if cur_node is bestChild:
               return action

    def addNodeAndBackpropagate(self):
        '''
        Function to run a single MCTS iteration
        '''
        node = self.addNode()
        reward = self.playoutPolicy(node.state, self.env, self.random)
        self.backpropagate(node, reward)

    def addNode(self):
        '''
        Function to add a node to the MCTS tree
        '''
        cur_node = self.root
        while not cur_node.isDone:
            if cur_node.allChildrenAdded:
                cur_node = self.chooseBestActionNode(cur_node, self.explorationParam)
            else:
                actions = self.env.actions
                for action in actions:
                    if action not in cur_node.children:
                        childnode = cur_node.state.simulateStep(env=self.env, action=action)
                        newNode = Node(state=childnode, parent=cur_node) 
                        cur_node.children[action] = newNode
                        if len(actions) == len(cur_node.children):
                            cur_node.allChildrenAdded = True
                        return newNode
        return cur_node

    def backpropagate(self, node: Node, reward):
        '''
        FILL ME : This function should implement the backpropation step of MCTS.
                  Update the values of relevant variables in Node Class to complete this function
        '''
        if node:
            node.totalReward += reward
            node.numVisits += 1
            self.backpropagate(node.parent, reward)

    def chooseBestActionNode(self, node, explorationValue):
        random = self.random
        bestValue = float("-inf")
        nodeValueDict = dict()
        for child in node.children.values():
            '''
            FILL ME : Populate the list bestNodes with all children having maximum value
                       
                       Value of all nodes should be computed as mentioned in question 3(b).
                       All the nodes that have the largest value should be included in the list bestNodes.
                       We will then choose one of the nodes in this list at random as the best action node. 
            '''
            childNodeValue = child.totalReward / child.numVisits + explorationValue * math.sqrt(math.log(node.numVisits) / child.numVisits)
            bestValue = max(bestValue, childNodeValue)
            nodeValueDict[child] = childNodeValue
        bestNodes = [node for node in nodeValueDict if nodeValueDict[node] == bestValue]
        return random.choice(bestNodes)

try:
    from runner.abstracts import Agent
except:
    class Agent(object): pass
    
class MCTSAgent(Agent):
    def initialize(self, env, numiters, random_seed):
        self.env = env
        self.numiters = numiters
        self.random_seed = random_seed
        self.explorationParam = 1.
        self.mcts = MonteCarloTreeSearch(env=self.env, numiters=self.numiters,
                explorationParam=self.explorationParam, random_seed=self.random_seed)
    
    def step(self, state, *args, **kwargs) :
        _state = GridWorldState(state)
        action = self.mcts.buildTreeAndReturnBestAction(initialState=_state)
        return action
    
def create_agent(test_case_env, *args, **kwargs):
    return MCTSAgent()


if __name__ == '__main__':
    import argparse

    parser = argparse.ArgumentParser()
    parser.add_argument('testcase', type=int, help='test case number')
    args = parser.parse_args()

    ### Sample test cases. 
    test_config = [{'lanes' : [LaneSpec(1, [-1, -1])] *3,'width' :5, 'seed' : 10, 'iters': 300},
                   {'lanes' : [LaneSpec(2, [-2, -1])] *3,'width' :7, 'seed' : 15, 'iters': 100},
                   {'lanes' : [LaneSpec(2, [-2, -1])] *4,'width' :8, 'seed' : 125, 'iters': 500},
                   {'lanes' : [LaneSpec(2, [-3, -2])] *4,'width' :10, 'seed' : 44, 'iters': 300},
                   {'lanes' : [LaneSpec(2, [-3, -1])] *4,'width' :10, 'seed' : 125, 'iters': 400},
                   {'lanes' : [LaneSpec(2, [-3, -1])] *4,'width' :10, 'seed' : 25, 'iters': 300}]

    test_case_number = args.testcase
    LANES = test_config[test_case_number]['lanes']
    WIDTH = test_config[test_case_number]['width']
    RANDOM_SEED = test_config[test_case_number]['seed']
    numiters = test_config[test_case_number]['iters']
    stochasticity = 1.
    env = gym.make('GridDriving-v0', lanes=LANES, width=WIDTH, 
                   agent_speed_range=(-3,-1), finish_position=Point(0,0), #agent_ pos_init=Point(4,2),
                   stochasticity=stochasticity, tensor_state=False, flicker_rate=0., mask=None, random_seed=RANDOM_SEED)

    actions = env.actions
    env.render()
    done = False
    mcts = MonteCarloTreeSearch(env=deepcopy(env), numiters=numiters, explorationParam=1.,random_seed=RANDOM_SEED)
    state = env.reset()
    while not done:
        gw_state = GridWorldState(state)
        action = mcts.buildTreeAndReturnBestAction(initialState=gw_state)
        print (action)
        state, reward, done, info = env.step(action)
        env.render()
        if done == True:
            break
    print ("simulation done")