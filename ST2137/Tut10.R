# Q2
power = function(N, n, mu) {
  t05 = qt(0.975, n - 1)
  test.statistics = c(rep(0, N))
  for (i in 1:N) {
    sample = rnorm(n, mu, 2)
    t = mean(sample) / (sd(sample) / sqrt(n))
    test.statistics[i] = t
  }
  size = sum(abs(test.statistics) > t05) / N
  return(size) # power, prob of reject null given null is wrong
}