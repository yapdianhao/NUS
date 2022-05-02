rm(list = ls())
m = 10000
n = 20
mu = 170
sd = 10
meanx = numeric(m)
medx = numeric(m)
trmx = numeric(m)
stdx = numeric(m)
set.seed(1234)
for (i in 1:m) {
  x = rnorm(n, mu, sd)
  meanx[i] = mean(x)
  medx[i] = median(x)
  trmx[i] = mean(x, trim = 0.1)
  stdx[i] = sd(x)
}

simumean = apply(cbind(meanx, medx, trmx), 2, mean)
simustd = apply(cbindn(meanx, medx, trmx), 2, sd)
simubias = simumean - rep(mu, 3)
simumse = simubias ^ 2 + simustd ^ 2
estimators = c('Sample Mean', 'Sample Median', 'Sample Trimmed Mean 10%')
names = c('True value', m, simumean, simustd, simubias, )