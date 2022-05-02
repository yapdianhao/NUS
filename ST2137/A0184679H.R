# Student number = A0184679H
set.seed(666)

# Q3
simulate = function(lambda, N = 1000, n = 50) {
  sds = c(rep(0, N))
  avgs = c(rep(0, N))
  tcrit = qexp(0.95)
  for (i in 1:N) {
    sample = rexp(n, 1 / lambda)
    sds[i] = sd(sample)
    avgs[i] = mean(sample)
  }
  return(sum(abs(avgs - 400) > tcrit) / N) # power = tests that reject H0 over all samples
}

simulate(430) #0.961
simulate(500) #0.987
simulate(550) # 0.998
simulate(700) # 1

# Q4
us.pop = read.csv('./sample_US_pop.csv', header = T)
attach(us.pop)

N = 10 # number of replicates
n = length(u) # sample size
theta.bootstrap = numeric(N) # to be filled
for (i in 1:N) {
  sp = sample(1 : n, size = n, replace = T)
  sampled.u = u[sp]
  sampled.v = v[sp]
  theta.bootstrap[i] = mean(sampled.v) / mean(sampled.u)
}

se = sd(theta.bootstrap) 
se # standard error = 0.1067545
theta.hat = mean(v)  / mean(u)
bias = mean(theta.bootstrap) - theta.hat
low = theta.hat - bias - 1.96 * se
high = theta.hat - bias + 1.96 * se
cat("95% confidence interval is [", low, ",", high, "]\n") # 95% confidence interval is [ 1.05093 , 1.469408 ]
