## 1b
summaries = function(x) {
  return(list(mean = mean(x), three.quant = quantile(x), variance = var(x), stdev = sd(x), 
              range = range(x)))
}

solve(t(X)%*%X) %*% t(X) %*% y

## 3
a = 0
b = 1
ct = 3
c = 0
s = 1
while (ct <= 30) {
  c = 2 * b - a
  a = b
  b = c
  if (ct <= 20) {
    s = s + c
  }
  ct = ct + 1
}
cat(c, s)

## 4

m2 = function(x) {
  x_bar = mean(x)
  x = (x - x_bar) ^ 2
  return(sum(x) / length(x))
}

m3 = function(x) {
  x_bar = mean(x)
  x = (x - x_bar) ^ 3
  return(sum(x) / length(x))
}

m4 = function(x) {
  x_bar = mean(x)
  x = (x - x_bar) ^ 4
  return(sum(x) / length(x))
}

